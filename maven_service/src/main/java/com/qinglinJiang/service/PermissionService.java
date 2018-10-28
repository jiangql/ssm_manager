package com.qinglinJiang.service;

import com.qinglinJiang.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionService {
    List<Permission> findAll();

    void save(Permission permission);
}
