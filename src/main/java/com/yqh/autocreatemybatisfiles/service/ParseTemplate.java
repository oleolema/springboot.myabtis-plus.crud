/**
 * FileName:   ParseTemplate
 * Author:     O了吗
 * Date:       2019/9/27 15:56
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.service;

import com.yqh.autocreatemybatisfiles.bean.ProjectProperties;
import com.yqh.autocreatemybatisfiles.bean.TableDesc;
import com.yqh.autocreatemybatisfiles.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/27
 * @since 1.0.0
 */
@Component
@Slf4j
public abstract class ParseTemplate {

    @Autowired
    protected ProjectProperties projectProperties;



    protected Expression expression;

    public Expression getExpression(Resource resource) {
        String s = MyUtil.readFile(resource);
        log.debug("读取文件模板\n" + s);
        ExpressionParser parser = new SpelExpressionParser();
        return parser.parseExpression(s, new TemplateParserContext());

    }

    protected abstract Resource getResource();

    protected String parseTemplate(TableDesc tableDesc, Map<String, Object> variable) {
        if (this.expression == null) {
            this.expression = getExpression(getResource());
        }
        return parseTemplate(expression, variable);
    }

    public String parseTemplate(Expression expression, Map<String, Object> variableMap) {
        EvaluationContext context = new StandardEvaluationContext();
        variableMap.forEach(context::setVariable);
        String message = expression.getValue(context, String.class);
        log.debug("输出文件" + message);
        return message;
    }

    public String parseTemplate(TableDesc tableDesc) {
        Map<String, Object> map = new HashMap<>(12);
        String className = MyUtil.toClassName(tableDesc.getTableName());
        map.put("beanName", className);
        map.put("mainPackage", projectProperties.getPackageName());
        map.put("beanNameCamel", MyUtil.toCamel(tableDesc.getTableName()));
        addVariables(tableDesc, map);
        return parseTemplate(tableDesc, map);
    }


    public abstract void addVariables(TableDesc tableDesc, Map<String, Object> map);


}