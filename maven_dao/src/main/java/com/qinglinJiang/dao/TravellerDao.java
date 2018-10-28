package com.qinglinJiang.dao;

import com.qinglinJiang.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDao {


    List<Traveller> findAllById(String id);
}
