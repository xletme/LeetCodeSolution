package poi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author maoxin
 * @version 1.0
 * @description:
 * @date 2023/10/13 16:47
 */
@Data
public class Goods {

    private String name;

    private BigDecimal price;
}
