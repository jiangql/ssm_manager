package com.qinglinJiang.dao;

import com.qinglinJiang.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
    Member findMemBerById(String id);
}
