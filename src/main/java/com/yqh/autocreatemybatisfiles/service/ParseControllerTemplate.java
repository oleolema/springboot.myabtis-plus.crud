/**
 * FileName:   ParseMapperTemplate
 * Author:     O了吗
 * Date:       2019/9/27 19:59
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.service;

import com.yqh.autocreatemybatisfiles.bean.TableDesc;
import com.yqh.autocreatemybatisfiles.util.MyUtil;
import org.springframework.core.io.Resource;
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
public class ParseControllerTemplate extends ParseTemplate {


    @Override
    protected Resource getResource() {
        return projectProperties.getControllerTemplate();
    }

    public String parseTemplate(TableDesc tableDesc) {
        String className = MyUtil.toClassName(tableDesc.getTableName());
        Map<String, Object> map = new HashMap<>(6);
        map.put("packageName", projectProperties.getPackageName() + "." + packageName.controller);
        map.put("className", className + "Controller");
        map.put("beanName", className);
        map.put("beanNameCamel", MyUtil.toCamel(tableDesc.getTableName()));
        return parseTemplate(map);
    }


}