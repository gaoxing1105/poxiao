package tt.zgx.common.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private String goodsName;
    private String orderStatus;
    private LocalDateTime createTime;

    private void test(){

    }
}
