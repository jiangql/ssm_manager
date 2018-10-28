package com.qinglinJiang.controller;

import com.qinglinJiang.domain.Permission;
import com.qinglinJiang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    private ModelAndView mv = new ModelAndView();

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<Permission> list=permissionService.findAll();
        mv.addObject("permissionList",list);
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("save.do")
    public ModelAndView savePermission(Permission permission){
        permissionService.save(permission);
        mv.setViewName("redirect:findAll.do");
        return mv;
    }


}
