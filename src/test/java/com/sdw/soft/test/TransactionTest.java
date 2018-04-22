package com.sdw.soft.test;

import com.sdw.soft.meta.User;
import com.sdw.soft.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by shangyd on 2018/4/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath*:spring-context.xml","classpath*:spring-mvc.xml"} )
public class TransactionTest {


    @Autowired
    private UserService userService;

    /**
     * 测试直接调用无@transactional注解的方法  此方法内部调用带@transactional注解的方法 注解不生效
     */
    @Test
    public void testNoAnnotationMethod() {
        User user = new User();
        user.setUserName("NoAnnotation");
        user.setAge(28);
        userService.noAnnotationMethod(user);
    }
    @Test
    public void testInsert() {
//        System.out.println(JSONObject.toJSON(userService.getAll()));
        User user = new User();
        user.setUserName("Jack");
        user.setAge(28);
        int count = userService.addUser(user);
//        int count = userService.insert(user);
        System.out.println(count);

    }


    @Test
    public void testManualTransaction() {

    }

}
