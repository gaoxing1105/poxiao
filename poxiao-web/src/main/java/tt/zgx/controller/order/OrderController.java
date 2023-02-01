package tt.zgx.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tt.zgx.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("create")
    public String create(){
        orderService.insert();
        return "suc";
    }


}
