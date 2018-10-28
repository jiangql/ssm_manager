package com.qinglinJiang.test;

import com.qinglinJiang.dao.MemberDao;
import com.qinglinJiang.dao.PermissionDao;
import com.qinglinJiang.dao.RoleDao;
import com.qinglinJiang.dao.UserDao;
import com.qinglinJiang.domain.Order;
import com.qinglinJiang.domain.Permission;
import com.qinglinJiang.domain.UserInfo;
import com.qinglinJiang.service.OrderService;
import com.qinglinJiang.service.UserServie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml", "classpath*:spring-cecurity.xml" })
public class Testlll {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    public void testff() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserInfo user = userDao.findUserById("D00B5415364742DBAD38DF291537DC41");
        System.out.println(user);
        List<Permission> all = permissionDao.findAll();
        System.out.println(all);


    }
}
