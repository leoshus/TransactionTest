package com.sdw.soft.service;

import com.sdw.soft.dao.UserDao;
import com.sdw.soft.meta.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shangyd on 2018/4/22.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }


    public int noAnnotationMethod(User user) {
        return addUser(user);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public int addUser(User user) {
        String message = null;
        int count = userDao.addUser(user);
        message.length();
        return count;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int insert(User user) {
        String insert = null;
        int count = this.addUser(user);
        User user1 = new User();
        user1.setUserName("Leo");
        user1.setAge(27);
        userDao.addUser(user1);
//        insert.length();
        return count;
    }

    private final void test() {

    }
}
