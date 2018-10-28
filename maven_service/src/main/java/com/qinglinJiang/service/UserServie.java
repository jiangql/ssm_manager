package com.qinglinJiang.service;

import com.qinglinJiang.domain.Role;
import com.qinglinJiang.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserServie extends UserDetailsService{

    List<UserInfo> findAll(Integer currentPage, Integer pageSize);

    void saveUserInfo(UserInfo userInfo);

    UserInfo findUserById(String id);

    List<Role> findUserOtherRoles(String id);

    void addRoleToUser(String id, String[] ids);
}

