package com.dity.tag.dao;

import com.dity.tag.entity.SyDictItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/5/21
 */
public interface DictItemDao extends JpaRepository<SyDictItem,Integer> {
    List<SyDictItem> findByKey(String key);
}
