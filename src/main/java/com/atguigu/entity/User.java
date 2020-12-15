package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.util.Date;

/**
 * @author hehao
 * @create 2020-12-14 18:25
 */
@Data //使用lombok来通过注解的方式来简化Javabean的开发 @data自动生成get、set方法
@ToString //代替父类的toString
//@Setter 只生成set方法
//@Getter 只生成get方法
@AllArgsConstructor //添加全参构造器
@NoArgsConstructor //添加空参构造器
public class User {
    //可以在配置文件中配置mp的全局主键策略
//    @TableId(type = IdType.ASSIGN_ID) //mybatis-plus默认主键策略
//    @TableId(type = IdType.ASSIGN_UUID) //使用UUID生成数据 （是字符串类型的）
//    @TableId(type = IdType.AUTO) //数据库维护ID自增 Java中不提供ID
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT) //mp的自动填充策略，当添加的时候自动填充
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE) //当添加和更新的的时候自动填充
    private Date gmtModified;

    @TableLogic //标识数据库的该字段为逻辑删除 0为未删除 1为已删除
    private Boolean deleted;
}
