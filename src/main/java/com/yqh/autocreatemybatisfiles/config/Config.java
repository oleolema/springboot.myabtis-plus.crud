/**
 * FileName:   Config
 * Author:     O了吗
 * Date:       2019/9/26 16:35
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.config;

import com.yqh.autocreatemybatisfiles.bean.ProjectProperties;
import com.yqh.autocreatemybatisfiles.service.InitDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/26
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties({ProjectProperties.class})
public class Config {

    @Autowired
    private InitDataSource initDataSource;


    @Bean
    public DataSource dataSource() {
        ProjectProperties projectProperties = initDataSource.getProjectProperties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(projectProperties.getDriver());
        dataSource.setUrl(projectProperties.getUrl());
        dataSource.setUsername(projectProperties.getUsername());
        dataSource.setPassword(projectProperties.getPassword());
        return dataSource;
    }


}

