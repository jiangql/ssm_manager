package com.qinglinJiang.controller;


import com.github.pagehelper.PageInfo;
import com.qinglinJiang.domain.Product;
import com.qinglinJiang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="currentPage",defaultValue = "1",required = true) Integer currentPage,
                                @RequestParam(name = "pageSize",defaultValue = "5",required = true) Integer pageSize){
        ModelAndView md = new ModelAndView();
        List<Product> productList = productService.findAll(currentPage,pageSize);
        PageInfo pageInfo = new PageInfo(productList);
        int startPage;
        int endPage;
        if (pageInfo.getPages()<=5){
            startPage=1;
            endPage=pageInfo.getPages();
        }else {
            startPage=pageInfo.getPageNum()-2;
            endPage=pageInfo.getPageNum()+2;
            if (startPage<1){
                startPage=1;
                endPage=5;
            }
            if (endPage>pageInfo.getPages()){
                endPage=pageInfo.getPages();
                startPage=endPage-4;
            }
        }
        pageInfo.setFirstPage(startPage);
        pageInfo.setLastPage(endPage);
        md.addObject("pageInfo",pageInfo);
        md.setViewName("product-list");
        return md;
    }


    @RequestMapping("/findone.do")
    public ModelAndView findOne(String id){
        ModelAndView md = new ModelAndView();
        Product product = productService.findProductById(id);
        md.addObject("",product);
        md.setViewName("");
        return md;
    }

    @RequestMapping("/save.do")
    public ModelAndView save(Product product){
        ModelAndView md = new ModelAndView();
        productService.saveProduct(product);
        md.setViewName("redirect:findAll.do");
        return md;
    }

    @RequestMapping("/delete.do")
    public ModelAndView delete(String id){
        ModelAndView md = new ModelAndView();
        productService.deleteProduct(id);
        md.setViewName("redirect:findAll.do");
        return md;
    }

    @RequestMapping("/update.do")
    public ModelAndView update(Product product){
        ModelAndView md = new ModelAndView();
        productService.updateProduct(product);
        md.setViewName("");
        return md;
    }

}
