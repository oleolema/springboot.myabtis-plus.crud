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
public class ParseBeanTemplate extends ParseTemplate {


    /**
     * bean属性的权限
     */
    private static final String PERMISSION = "private";


    @Override
    protected Resource getResource() {
        return projectProperties.getBeanTemplate();
    }

    private String parseBeanField(TableDesc.Desc desc) {
        String fieldName = MyUtil.toCamel(desc.getField());
        String type = TypeUtil.toType(desc.getType());
        String tableId = "";
        if (desc.getKey().contains("PRI")) {
            tableId = "\t@TableId\n";
        }
        return tableId + "\t" + PERMISSION + " " + type + " " + fieldName + ";\n\n";
    }

    private String parseBeanAllField(TableDesc tableDesc) {
        StringBuilder allField = new StringBuilder();
        for (TableDesc.Desc desc : tableDesc.getTableDesc()) {
            allField.append(parseBeanField(desc));
        }
        return allField.toString();
    }

    public String parseTemplate(TableDesc tableDesc) {
        String className = MyUtil.toClassName(tableDesc.getTableName());
        String fields = parseBeanAllField(tableDesc);
        Map<String, Object> map = new HashMap<>(6);
        map.put("packageName", projectProperties.getPackageName() + "." + packageName.bean);
        map.put("className", className);
        map.put("field", fields);
        return parseTemplate(map);
    }


}