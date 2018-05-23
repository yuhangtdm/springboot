package com.dity.tag.jedis;

import com.dity.tag.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author:yuhang
 * @Date:2018/5/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JedisUtilTest {
    @Test
    public void getJedisPool() throws Exception {
        System.out.println(JedisUtil.getJedisPool());
    }

    @Test
    public void getJedisClient() throws Exception {

        List<String> valueFromMap = JedisUtil.getValueFromMap(Constants.DIC_KEY + "_marriage");
        System.out.println(valueFromMap);

    }

}