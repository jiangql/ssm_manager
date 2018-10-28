package com.qinglinJiang.dao;


import com.qinglinJiang.domain.Permission;
import com.qinglinJiang.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    List<Role> findAllRoleByUserId(String id);

    List<Role> findAll();

    void saveRole(Role role);

    List<Permission> findRoleByIdAndAllPermission(String id);

    Role findRoleById(String id);

    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
