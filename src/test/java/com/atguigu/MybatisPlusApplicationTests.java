package com.atguigu;

import com.atguigu.entity.Product;
import com.atguigu.entity.User;
import com.atguigu.mapper.ProductMapper;
import com.atguigu.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //查询所有
    @Test
    void selectTest() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //条件查询 id>5and带有张的数据
    @Test
    void selectTest1() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id", 5).likeRight("name", "张");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    //条件查询 id>1or带有6的email数据
    @Test
    void selectTest2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id", 1).or().like("email", "6");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    void selectPage() {
        Page<User> page = new Page<>(2, 3);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("id", 5);
        queryWrapper.orderByDesc("age", "id");//按照指定策略排序 先按照age排，再按照ID排
        Page<User> page1 = userMapper.selectPage(page, queryWrapper);
        System.out.println(page1.getRecords());
        System.out.println(page1.getOrders());
        System.out.println(page1.getCurrent());
        System.out.println(page1.getPages());
        System.out.println(page1.getSize());
        System.out.println(page1.getTotal());
        System.out.println(page1.hasNext());
        System.out.println(page1.hasPrevious());
    }

    //带有条件的分页查询
    //查询指定列的数据集合
    @Test
    void columns() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "age", "name"); //指定要查的字段
        queryWrapper.gt("id", 2);
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    //插入
    @Test
    void insert() {
        User user = new User(null, "张si", 19, "san@333.com", null, null);
        int i = userMapper.insert(user);
        System.out.println(i > 0 ? "加入成功" : "添加失败");
    }

    @Test
    void insert1() {
        User user = new User();
        //主键ID未设置自增 mybatis-plus默认主键策略为雪花算法，生成一个ID
        user.setName("li7");
        user.setAge(50);
        user.setEmail("666@111.com");
        int i = userMapper.insert(user);
        System.out.println(i > 0 ? "加入成功" : "添加失败");
    }

    //更新
    @Test
    void update() {
        userMapper.updateById(new User(3l, "tomcat", null, null, null, null));
    }

    @Test
    void update1() {
        userMapper.updateById(new User(3l, "tomcat", null, null, null, null));
    }

    //测试乐观锁
    @Autowired
    private ProductMapper productMapper;

    @Test
    void leGuanTest() {
        //解决方案，共享数据被更新是，多个请求获取的版本号一致，只有一个可以跟新成功
        //小李和小明先读取数据中的数据
        Product aProduct = productMapper.selectById(1);
        Product bProduct = productMapper.selectById(1);

        //小李先涨价50
        aProduct.setPrice(aProduct.getPrice() + 50);
        productMapper.updateById(aProduct);

        //小明在降价30
        bProduct.setPrice(bProduct.getPrice() - 30);
        int i = productMapper.updateById(bProduct);
        if (i == 0) {
            //更新失败
            bProduct = productMapper.selectById(1);
            bProduct.setPrice(bProduct.getPrice() - 30);
            productMapper.updateById(bProduct);
        }

        System.out.println(productMapper.selectById(1));
    }
    //删除

}
