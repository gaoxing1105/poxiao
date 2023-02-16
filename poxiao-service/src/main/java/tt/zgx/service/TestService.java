package tt.zgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tt.zgx.MyDelayList;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private MyDelayList myDelayList;

    public String setDelayList(int time, long id){
        System.out.println("--------delaylist 新增:id"+id+", 时间:"+time+" 秒");
        myDelayList.setDelayedSet(id,time);
        return "succ";
    }

    public List<Long> getDelayList(){
        return myDelayList.getDelayList();
    }
}
