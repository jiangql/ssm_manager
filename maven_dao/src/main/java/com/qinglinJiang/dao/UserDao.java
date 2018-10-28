package com.qinglinJiang.dao;

import com.qinglinJiang.domain.Role;
import com.qinglinJiang.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    UserInfo findUserByname(String username);

    List<UserInfo> findAll();

    void saveUser(UserInfo userInfo);

    UserInfo findUserById(String id);

    List<Role> findUserOtherRoles(String id);

    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
