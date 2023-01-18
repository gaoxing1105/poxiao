package tt.zgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tt.zgx.common.data.Order;
import tt.zgx.dao.mapper.OrderMapper;

import java.time.LocalDateTime;

@Service
public class OrderService {

//    @Autowired
//    private OrderMapper orderMapper;

    public String hello(){
       return "hello"+ LocalDateTime.now().toString();
    }

    public void insert(){
//        Order order = new Order();
//
//        orderMapper.insert()
    }

}
