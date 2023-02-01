package tt.zgx.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Service
public class MonitorService {


    public String hello(){
        String result = "hello "+ LocalDateTime.now().toString();
        System.out.println(result);
        return result;
    }
}
