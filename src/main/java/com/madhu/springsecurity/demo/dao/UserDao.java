package com.madhu.springsecurity.demo.dao;

import com.madhu.springsecurity.demo.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    
}
