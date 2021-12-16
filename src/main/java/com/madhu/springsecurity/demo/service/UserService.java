package com.madhu.springsecurity.demo.service;

import com.madhu.springsecurity.demo.entity.User;
import com.madhu.springsecurity.demo.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);
}
