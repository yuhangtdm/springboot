package com.dity.web.dao;

import com.dity.web.pojo.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author:yuhang
 * @Date:2018/4/21
 */
public interface LoggerDao extends JpaRepository<LoggerEntity,Long> {
}
