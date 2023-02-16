package tt.zgx.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tt.zgx.service.TestService;

import java.util.List;

@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("setdelaylist/{time}/{id}")
    public String setDelayList(@PathVariable int time,@PathVariable long id){
        testService.setDelayList(time,id);
        return "succ";
    }

    @RequestMapping("getDelayList")
    public List<Long> getDelayList(){
        return testService.getDelayList();

    }
}
