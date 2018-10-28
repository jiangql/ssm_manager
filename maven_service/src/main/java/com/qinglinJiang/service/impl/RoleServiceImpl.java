package com.qinglinJiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.qinglinJiang.dao.RoleDao;
import com.qinglinJiang.domain.Permission;
import com.qinglinJiang.domain.Role;
import com.qinglinJiang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public List<Role> findAll(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return roleDao.findAll();
    }

    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String id) {
        return roleDao.findRoleByIdAndAllPermission(id);
    }

    @Override
    public Role findRoleById(String id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }
}
