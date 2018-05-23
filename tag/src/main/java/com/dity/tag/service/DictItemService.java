package com.dity.tag.service;

import com.dity.tag.entity.SyDictItem;

import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/5/21
 */
public interface DictItemService {

    //缓存数据字典到redis
    void cacheDict();

    /**
     * 根据数据字典的key获取数据字典列表
     * 优先从缓存取  查询不到从数据库取
     * @param key
     * @return
     */
    List<SyDictItem> getDictItem(String key);
}
