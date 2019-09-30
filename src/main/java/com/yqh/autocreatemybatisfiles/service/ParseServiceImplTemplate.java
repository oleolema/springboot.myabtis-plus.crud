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
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
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
public class ParseServiceImplTemplate extends ParseTemplate {

    public static final String PACKAGE_NAME = "service.impl";

    public static final String CLASS_SUFFIX = "ServiceImpl";

    public static Resource templateResource = new ClassPathResource("serviceImpl.java");
    @Override
    protected Resource getResource() {
        return templateResource;
    }

    @Override
    public void addVariables(TableDesc tableDesc, Map<String, Object> map) {
        String className = MyUtil.toClassName(tableDesc.getTableName());
        map.put("className", className + CLASS_SUFFIX);
    }


}