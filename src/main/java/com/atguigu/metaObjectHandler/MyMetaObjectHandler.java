package com.atguigu.metaObjectHandler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author hehao
 * @create 2020-12-14 19:54
 */
@Component //将该类扫描到容器中
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("gmtCreate",new Date());
        //第一个参数为要自动填充的属性名（一定要有set方法），第二个参数是要填充的值
        metaObject.setValue("gmtModified",new Date());
    }

    //更新时填充
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("gmtModified",new Date());
    }
}
