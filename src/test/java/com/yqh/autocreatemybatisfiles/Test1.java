/**
 * FileName:   Test1
 * Author:     O了吗
 * Date:       2019/9/26 18:01
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles;

import com.yqh.autocreatemybatisfiles.bean.ProjectProperties;
import com.yqh.autocreatemybatisfiles.util.MyUtil;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.*;
import java.util.function.Function;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/26
 * @since 1.0.0
 */
public class Test1 {

    @Test
    public void test1() {
        or(new Function<ProjectProperties, Object>() {
            @Override
            public Object apply(ProjectProperties projectProperties) {
                return projectProperties.getPassword();
            }
        });
        or(ProjectProperties::getPassword);
    }

    @Test
    public void test3() {
        Function<ProjectProperties, String> getPassword = ProjectProperties::getPassword;
        Function<ProjectProperties, String> getPassword1 = ProjectProperties::getPassword;

        System.out.println(getPassword.equals(getPassword1));

    }

    public void or(Function<ProjectProperties, Object> propertiesObjectFunction) {
        ProjectProperties projectProperties = new ProjectProperties();
        projectProperties.setUsername("root");
        Class propertiesObjectFunctionClass = propertiesObjectFunction.getClass();
        Class propertiesObjectClass = projectProperties.getClass();

    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        String a = "1";
        String b = "1";

        String obj1 = (String) copy(a);

        String obj2 = (String) copy(b);

        System.out.println(a == obj1);

        System.out.println(b == obj2);

        System.out.println(a == b);

        System.out.println(a == obj2);

    }

    public Object copy(Object a) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(a);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Object obj1 = objectInputStream.readObject();
        return obj1;
    }


    @Test
    public void test5() {
        System.out.println(MyUtil.toCamel("aaa_b_c_d"));

    }

    @Test
    public void test6() {
        File resourcePath = MyUtil.findInParentFiles(new File("E:\\CODE\\Java\\intelij IDEA workspace\\springboot-high\\auto-create-mybatis-files\\src\\main\\java\\com\\yqh\\autocreatemybatisfiles"),
                "resources");
        File yamlFile = MyUtil.findInChildrenFiles(resourcePath, "application.yml");
        System.out.println(yamlFile);

    }

    @Test
    public void test7() {
        String greetingExp = "Hello, #{#user}";
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("user", "Gangyou");
        Expression expression = parser.parseExpression(greetingExp,
                new TemplateParserContext());
        System.out.println(expression.getValue(context, String.class));
    }


}