package tt.zgx.controller.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tt.zgx.service.MonitorService;


@RestController
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @RequestMapping("hello")
    public String hello(){
        return monitorService.hello();
    }
}
