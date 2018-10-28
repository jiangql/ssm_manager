package com.qinglinJiang.dao;

import com.qinglinJiang.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品持久层
 */
@Repository
public interface ProductDao {
    /**
     * 查询所有
     *
     * @return
     */
    List<Product> findAll();

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Product findProductById(String id);


    /**
     * 添加产品
     *
     * @param product
     */
    void saveProduct(Product product);

    /**
     * 删除产品
     *
     * @param id
     */
    void deleteProduct(String id);


    /**
     * 更新产品
     *
     * @param product
     */
    void updateProduct(Product product);
}
