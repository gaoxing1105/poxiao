package tt.zgx;

import org.redisson.api.RList;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1、使用当前时间+延时秒数为score，存到zset中
 * 2、使用lua脚本执行：
 *  1）扫 zrangebyscore 0到当前秒数的元素
 *  2）元素添加到list
 *  3）在zset中删除添加到list的元素
 *  4）给程序返回扫到的元素
 * 3、程序每秒执行lua脚本，返回到时间的元素，执行业务操作并记录数据库。
 * 4、删除list中记录完数据库的元素
 * 5、补偿：在第3步出现异常的时候，有程序定时扫list中的元素，补偿业务。
 */
@Service
public class MyDelayList {

    public static final String DELAY_ZSET_KEY = "delay_zset";
    public static final String DELAY_LIST_KEY = "delay_list";


    @Autowired
    private RedissonClient redissonClient;

    public void setDelayedSet(Long value, double delayTime){
        RScoredSortedSet<Long> set = this.redissonClient.getScoredSortedSet(MyDelayList.DELAY_ZSET_KEY);
        set.add((System.currentTimeMillis()-1577808000000L)/1000+delayTime ,value);
    }

    public List<Long> getDelayList(){
        RScoredSortedSet<Long> set = this.redissonClient.getScoredSortedSet(MyDelayList.DELAY_ZSET_KEY);
        return set.readAll().stream().parallel().collect(Collectors.toList());
    }

    public void doCheck(){
        RScript rScript = this.redissonClient.getScript();
//        String script = "return redis.call('zrangebyscore','delay_zset',0 ,ARGV[1])";
        String script = "local set = redis.call('zrangebyscore', 'delay_zset', 0, ARGV[1]) " +
                "for i,v in ipairs(set) " +
                "do " +
                "    redis.call('lpush', 'delay_list',v) " +
                "    redis.call('zrem', 'delay_zset',1, v)" +
                "end " +
                "return set";
        String evalSha = rScript.scriptLoad(script);
        while(true){
            try {
                Thread.sleep(1000);
                System.out.println("checking delay_zset.............");
                int time = (int)((System.currentTimeMillis()-1577808000000L)/1000);
                ArrayList<Long> res = rScript.evalSha(RScript.Mode.READ_ONLY,evalSha,RScript.ReturnType.MAPVALUELIST, Collections.emptyList(),time);
                if (null != res && !res.isEmpty()){
                    res.forEach(r->System.out.print(r+","));
                    System.out.println("对数据执行业务操作！");
                    RList rlist = this.redissonClient.getList(MyDelayList.DELAY_LIST_KEY);
                    rlist.removeAll(res);
                    System.out.println("在list中删除执行完业务的数据.............");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
