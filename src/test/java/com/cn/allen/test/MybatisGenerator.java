package com.cn.allen.test;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/4/30
 * @Description:
 */
@SpringBootTest
public class MybatisGenerator {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void generator() {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = null;
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDataFromRedis() {
        Object obj = redisTemplate.opsForValue().get("key2");
        System.out.println(JSON.toJSONString(obj));
    }

    @Test
    public void getData() {
        HashMap<String,String> params = new HashMap<>();
        params.put("1","1");
        String str = params.putIfAbsent("1","2");
        if (Objects.equals(str,null)) {
            System.out.println("aaaa");
        } else {
            System.out.println("bbbb");
        }
        System.out.println(params.get("1"));
    }

    public static void main(String[] args) {
        HashMap<String,String> params = new HashMap<>();
//        params.put("1","1");
        String str = params.putIfAbsent("1","2"); //存在这个key返回旧值，否则返回null
        System.out.println(str);
        if (Objects.equals(str,null)) {
            System.out.println("aaaa");
        } else {
            System.out.println("bbbb");
        }
        System.out.println(params.get("1"));
    }
}
