package tt.zgx.controller.monitor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorController {

    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
