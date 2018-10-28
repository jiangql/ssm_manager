package com.qinglinJiang.dao;

import com.qinglinJiang.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissionDao {

    List<Permission> findAllByUserId(String id);

    List<Permission> findAll();

    void save(Permission permission);
}
