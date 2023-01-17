package tt.zgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tt.zgx.common.data.Order;
import tt.zgx.dao.mapper.OrderMapper;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public void insert(){
//        Order order = new Order();
//
//        orderMapper.insert()
    }

}
