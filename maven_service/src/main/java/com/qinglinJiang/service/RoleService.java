package com.qinglinJiang.service;

import com.qinglinJiang.domain.Permission;
import com.qinglinJiang.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll(Integer currentPage, Integer pageSize);

    void saveRole(Role role);

    List<Permission> findRoleByIdAndAllPermission(String id);

    Role findRoleById(String id);

    void addPermissionToRole(String roleId, String[] ids);
}
