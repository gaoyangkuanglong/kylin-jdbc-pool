package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.KylinRowMapper;
import com.example.demo.models.Demo;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KylinJdbcPoolApplicationTests {

  @Resource(name = "kylinJdbcTemplate")
  JdbcTemplate jdbcTemplate;

  @Test
  public void test() {
    int countResult = jdbcTemplate
        .queryForObject("select count(*) from SCHEMA.table",
            (resultSet, i) -> {
              return resultSet.getInt(1);
            });

    System.out.println("testResult: " + countResult);
  }

  @Test
  public void testRowMapper() {
    List<Demo> demoList = jdbcTemplate
        .query("select * from from SCHEMA.table limit 10",
            KylinRowMapper.getDefault(
                Demo.class));

    System.out.println(JSON.toJSONString(demoList));
  }

}
