package com.qinglinJiang.controller;

import com.github.pagehelper.PageInfo;
import com.qinglinJiang.domain.Permission;
import com.qinglinJiang.domain.Role;
import com.qinglinJiang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    private ModelAndView mv = new ModelAndView();

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="currentPage",defaultValue = "1",required = true) Integer currentPage,
                                @RequestParam(name = "pageSize",defaultValue = "5",required = true) Integer pageSize){
      List<Role> list= roleService.findAll(currentPage,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        Role role = roleService.findRoleById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/save.do")
    public ModelAndView saveRole(Role role){
        roleService.saveRole(role);
        mv.setViewName("redirect:findAll.do");
        return mv;
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id ){
        Role role = roleService.findRoleById(id);
        List<Permission> list=roleService.findRoleByIdAndAllPermission(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",list);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/addPermissionToRole.do")
    public ModelAndView addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,
                                            @RequestParam(name = "ids",required = true)String[] ids){
        roleService.addPermissionToRole(roleId,ids);
        mv.setViewName("redirect:findAll.do");
        return mv;
    }

}
