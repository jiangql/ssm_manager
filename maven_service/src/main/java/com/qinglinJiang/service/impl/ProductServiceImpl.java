package com.qinglinJiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.qinglinJiang.dao.ProductDao;
import com.qinglinJiang.domain.Product;
import com.qinglinJiang.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> findAll(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Product> productList = productDao.findAll();
        return productList;
    }

    public Product findProductById(String id) {
        return productDao.findProductById(id);
    }

    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    public void deleteProduct(String id) {
        productDao.deleteProduct(id);
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }
}
