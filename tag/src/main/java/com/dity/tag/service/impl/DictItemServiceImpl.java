package com.dity.tag.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dity.tag.Constants;
import com.dity.tag.dao.DictItemDao;
import com.dity.tag.entity.SyDictItem;
import com.dity.tag.jedis.JedisUtil;
import com.dity.tag.service.DictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/5/21
 */
@Service
public class DictItemServiceImpl implements DictItemService {

    @Autowired
    private DictItemDao dictItemDao;

    @Override
    public void cacheDict() {
        List<SyDictItem> all = dictItemDao.findAll();
        for (SyDictItem syDictItem : all) {
            JedisUtil.setMap(Constants.DIC_KEY+syDictItem.getKey(),syDictItem.getCode(), JSON.toJSONString(syDictItem));
        }

    }

    @Override
    public List<SyDictItem> getDictItem(String key) {
        List<SyDictItem> result=new ArrayList<>();
        List<String> valueFromMap = JedisUtil.getValueFromMap(Constants.DIC_KEY+key);
        if(valueFromMap!=null){
            for (String s : valueFromMap) {
                SyDictItem item = JSON.parseObject(s,SyDictItem.class);
                result.add(item);
            }
        }else {
            result=dictItemDao.findByKey(key);
        }


        return result;
    }
}
