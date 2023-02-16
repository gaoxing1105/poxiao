package tt.zgx;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("tt.zgx.dao.mapper")
public class PoXiaoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(PoXiaoApplication.class , args);
    }


    @Autowired
    private MyDelayList myDelayList;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        myDelayList.doCheck();
    }
}
