package com.sdw.soft.dao;

import com.sdw.soft.meta.User;

import java.util.List;

/**
 * Created by shangyd on 2018/4/22.
 */
public interface UserDao {

    public int addUser(User user);

    public List<User> getAll();
}
