package com.qinglinJiang.service;

import com.qinglinJiang.domain.SysLog;

import java.util.List;

public interface SysLogService {
    void saveLog(SysLog sysLog);

    List<SysLog> findAll(Integer currentPage, Integer pageSize);

}
