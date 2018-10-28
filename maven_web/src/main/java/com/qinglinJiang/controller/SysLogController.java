package com.qinglinJiang.controller;

import com.github.pagehelper.PageInfo;
import com.qinglinJiang.domain.SysLog;
import com.qinglinJiang.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;
    private ModelAndView mv =new ModelAndView();
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="currentPage",defaultValue = "1",required = true) Integer currentPage,
                                @RequestParam(name = "pageSize",defaultValue = "5",required = true) Integer pageSize){
        List<SysLog> list = sysLogService.findAll(currentPage,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setNavigatePages(3);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("log-list");
        return mv;
    }
}
