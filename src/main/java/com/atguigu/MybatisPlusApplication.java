package com.atguigu;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan(value = "com.atguigu.mapper") //扫描指定的mapper接口包 必须写到包名
@SpringBootApplication //自动配置：根据当前的依赖的环境判断创建那些配置类的对象，根据配置类对象初始化需要使用的模板对象
public class MybatisPlusApplication {
    /**
     * 创建项目大概需要创建的实体类
     * 1、与数据表映射的类 entity
     * 2、页面中显示的数据 vo view object
     * 3、两个服务传递的数据 dto data transfer object
     * 4、请求提交的数据 formObject
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

    //乐观锁插件，OptimisticLockerInterceptor mp的拦截器，可以在sql语句提交之前进行修改拼接
    @Bean
    public OptimisticLockerInterceptor getOptimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    //分页插件 拼接limit关键字
    @Bean
    public PaginationInterceptor getPaginationInterceptor(){
        return new PaginationInterceptor();
    }
}
