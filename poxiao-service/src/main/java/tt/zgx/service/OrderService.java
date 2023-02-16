package tt.zgx.service;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

//    @Autowired
//    private OrderMapper orderMapper;
    @Autowired
    private RedissonClient redissonClient;

    public void insert(){
        RBucket<Object> rBucket = this.redissonClient.getBucket("order");
        if(!rBucket.isExists()){
            rBucket.set(666);
        }
    }





}
