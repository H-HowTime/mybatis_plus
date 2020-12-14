package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author hehao
 * @create 2020-12-14 20:17
 */
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version //mp的版本号注解 表示当前字段号为乐观锁版本号
    private Integer version;
}
