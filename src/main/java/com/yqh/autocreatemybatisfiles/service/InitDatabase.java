/**
 * FileName:   InitDatabase
 * Author:     O了吗
 * Date:       2019/9/26 20:27
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.service;

import com.yqh.autocreatemybatisfiles.bean.TableDesc;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/26
 * @since 1.0.0
 */
@Component
@Data
public class InitDatabase {
    private List<String> tables;

    private Map<String, TableDesc> tableDescMap;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        showTables();
        tableColumns();
    }


    private void showTables() {
        tables = new ArrayList<>();
        jdbcTemplate.query("show tables", (rs, rowNum) -> {
            tables.add(rs.getString(1));
            return tables;
        });
    }

    private void tableColumns() {
        tableDescMap = new HashMap<>();
        for (String t : tables) {
            TableDesc td = new TableDesc();
            td.setTableName(t);
            jdbcTemplate.query("desc " + t, (rs, rowNum) -> {
                //获取所有字段
                TableDesc.Desc desc = new TableDesc.Desc();
                desc.setField(rs.getString(rs.findColumn("Field")));
                desc.setType(rs.getString(rs.findColumn("Type")));
                desc.setNullable(rs.getString(rs.findColumn("Null")));
                desc.setKey(rs.getString(rs.findColumn("Key")));
                desc.setDef(rs.getString(rs.findColumn("Default")));
                desc.setExtra(rs.getString(rs.findColumn("Extra")));
                td.add(desc);
                return td;
            });
            tableDescMap.put(t, td);
        }
    }

}