package com.atguigu.mapper;

import com.atguigu.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author hehao
 * @create 2020-12-14 18:30
 */
@Repository //接口扫描到容器中(主启动类上已经添加了@mapperscan注解，此处是为了解决自动装配该接口是误报问题)
// 继承mybatis-plus组件中的BaseMapper，即可实现增删改查
//只要Javabean和数据库中表名 + 字段类型字段名 完全匹配映射(表中字段的下划线会自动映射为Java中的驼峰命名法)
//
public interface UserMapper extends BaseMapper<User> {
}
