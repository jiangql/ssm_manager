package com.qinglinJiang.controller;

import com.github.pagehelper.PageInfo;
import com.qinglinJiang.domain.Role;
import com.qinglinJiang.domain.UserInfo;
import com.qinglinJiang.service.UserServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServie userServie;

    private ModelAndView mv = new ModelAndView();
    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="currentPage",defaultValue = "1",required = true) Integer currentPage,
                                @RequestParam(name = "pageSize",defaultValue = "5",required = true) Integer pageSize) {
        List<UserInfo> list = userServie.findAll(currentPage,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public ModelAndView saveUser(UserInfo userInfo){
        userServie.saveUserInfo(userInfo);
        mv.setViewName("redirect:findAll.do");
        return mv;
    }

    @RequestMapping("/findById.do")
    @RolesAllowed("ROLE_ADMIN")
    public ModelAndView findUserById(String id){
        UserInfo userInfo=userServie.findUserById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping("/findUserByIdAndAllRole.do")
    public  ModelAndView findUserByIdAndAllRole(String id){
        UserInfo user = userServie.findUserById(id);
        List<Role> list=userServie.findUserOtherRoles(id);
        mv.addObject("user",user);
        mv.addObject("roleList",list);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public ModelAndView addRoleToUser(@RequestParam(name = "userId",required = true) String id,
                                      @RequestParam(name = "ids",required = true)String[] ids ){
        userServie.addRoleToUser(id,ids);
        mv.setViewName("user-list");
        return mv;
    }
}
