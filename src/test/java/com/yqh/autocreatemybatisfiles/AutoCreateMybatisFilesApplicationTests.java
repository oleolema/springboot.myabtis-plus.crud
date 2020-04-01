package com.yqh.autocreatemybatisfiles;

import com.yqh.autocreatemybatisfiles.bean.ProjectProperties;
import com.yqh.autocreatemybatisfiles.bean.TableDesc;
import com.yqh.autocreatemybatisfiles.service.AutoCreateFiles;
import com.yqh.autocreatemybatisfiles.service.InitDatabase;
import com.yqh.autocreatemybatisfiles.service.ParseBeanTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoCreateMybatisFilesApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProjectProperties projectProperties;

    @Autowired
    InitDatabase database;

    @Autowired
    ParseBeanTemplate parseBeanTemplate;

    @Autowired
    InitDatabase initDatabase;

    @Autowired
    AutoCreateFiles autoCreateFiles;

    @Test
    public void test9() throws IOException {
        autoCreateFiles.creteAll();
    }

//    @Test
//    public void test8() {
//        Map<String, TableDesc> tableDescMap = initDatabase.getTableDescMap();
//        String s = parseBeanTemplate.parseTemplate(tableDescMap.get("department"));
//        System.out.println(s);
//    }


    @Test
    public void test3() {
        System.out.println(database.getTables());
        System.out.println(database.getTableDescMap());
    }

//    @Test
//    public void test1() {
//        System.out.println(jdbcTemplate);
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from testDb1.department");
//        System.out.println(maps);
//
//    }


    @Test
    public void contextLoads() throws IOException {

        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        //yaml.setResources(projectProperties.getProjectPath());
        yaml.setResources(new FileSystemResource("E:\\CODE\\Java\\intelij IDEA workspace\\springboot-high\\mybaits-plus\\src\\main\\resources\\application.yml"));
        Properties properties = yaml.getObject();

        assert properties != null;
        projectProperties.setUsername(properties.getProperty("spring.datasource.username"));
        projectProperties.setPassword(properties.getProperty("spring.datasource.password"));
        projectProperties.setDriver(properties.getProperty("spring.datasource.driver-class-name"));
        projectProperties.setUrl(properties.getProperty("spring.datasource.url"));

    }

    public String getProperty(Properties properties, String fields) {
        return properties.getProperty(ProjectProperties.PREFIX + "." + fields);
    }

    @Test
    public void test7() throws IOException {

        BufferedInputStream in = new BufferedInputStream(ParseBeanTemplate.templateResource.getInputStream());
        StringBuilder sb = new StringBuilder();
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = in.read(b)) != -1) {
            sb.append(new String(b, 0, len));
        }

        System.out.println(sb.toString());

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("className", "Test");
        Expression expression = parser.parseExpression(sb.toString(), new TemplateParserContext());
        String message = expression.getValue(context, String.class);
        System.out.println(message);
    }


}
