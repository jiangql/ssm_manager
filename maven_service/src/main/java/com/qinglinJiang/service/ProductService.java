package com.qinglinJiang.service;

import com.qinglinJiang.domain.Product;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有
     * @return
     */
    List<Product> findAll(Integer currentPage,Integer pageSize);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Product findProductById(String id);


    /**
     * 添加产品
     * @param product
     */
    void saveProduct(Product product);

    /**
     * 删除产品
     * @param id
     */
    void deleteProduct(String id);


    /**
     * 更新产品
     * @param product
     */
    void updateProduct(Product product);
}
