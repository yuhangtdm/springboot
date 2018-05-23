package com.dity.tag.util;

import com.dity.tag.entity.SyDictItem;
import com.dity.tag.service.DictItemService;

import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/5/21
 */
public class CacheUtil {
    public static DictItemService dictItemService;


    public static List<SyDictItem> getDictItem(String key){
       return dictItemService.getDictItem(key);
    }

    public static DictItemService getDictItemService() {
        return dictItemService;
    }

    public static void setDictItemService(DictItemService dictItemService) {
        CacheUtil.dictItemService = dictItemService;
    }
}
