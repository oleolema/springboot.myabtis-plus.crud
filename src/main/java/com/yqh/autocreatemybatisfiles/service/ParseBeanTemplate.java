/**
 * FileName:   ParseBeanTemplate
 * Author:     O了吗
 * Date:       2019/9/27 16:38
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.service;

import com.yqh.autocreatemybatisfiles.bean.TableDesc;
import com.yqh.autocreatemybatisfiles.util.MyUtil;
import com.yqh.autocreatemybatisfiles.util.TypeUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/27
 * @since 1.0.0
 */
@Component
public class ParseBeanTemplate extends ParseTemplate {

    public static final String PACKAGE_NAME = "bean";

    public static final String CLASS_SUFFIX = "";

    public static Resource templateResource = new ClassPathResource("bean.java");


    /**
     * bean属性的权限
     */
    private static final String PERMISSION = "private";


    @Override
    protected Resource getResource() {
        return templateResource;
    }


    private String addAnnotation(TableDesc.Desc desc) {
        String annotation = "";
        //主键
        if (desc.getKey().contains("PRI")) {
            annotation += "@TableId";
            imports.add("com.baomidou.mybatisplus.annotation.TableId");
            //自增
            if (desc.getExtra().contains("auto_increment")) {
                annotation += "(type = IdType.AUTO)";
                imports.add("com.baomidou.mybatisplus.annotation.IdType");
            }
        }
        return annotation.isEmpty() ? "" : "\t" + annotation + "\n";
    }

    private String parseBeanField(TableDesc.Desc desc) {
        String fieldName = MyUtil.toCamel(desc.getField());
        String type = TypeUtil.toType(desc.getType(), imports);
        String annotation = addAnnotation(desc);
        return annotation + "\t" + PERMISSION + " " + type + " " + fieldName + ";\n\n";
    }

    private String parseBeanAllField(TableDesc tableDesc) {
        StringBuilder allField = new StringBuilder();
        for (TableDesc.Desc desc : tableDesc.getTableDesc()) {
            allField.append(parseBeanField(desc));
        }
        return allField.toString();
    }


    @Override
    public void addVariables(TableDesc tableDesc, Map<String, Object> map) {
        String className = MyUtil.toClassName(tableDesc.getTableName());
        String fields = parseBeanAllField(tableDesc);
        map.put("className", className);
        map.put("fields", fields);
    }


}