package com.hs.springboot.study;

import com.hs.springboot.study.Application;
import com.hs.springboot.study.configuration.DatabaseConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Test
    public void contextLoads() {

    }
    @Test
    public void testSqlSession() throws Exception {
        System.out.println(sqlSession.toString());
    }
}
