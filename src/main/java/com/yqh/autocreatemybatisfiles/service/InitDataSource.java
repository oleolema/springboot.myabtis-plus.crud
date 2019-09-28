/**
 * FileName:   FindDataSource
 * Author:     O了吗
 * Date:       2019/9/26 16:07
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.service;

import com.yqh.autocreatemybatisfiles.bean.ProjectProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/26
 * @since 1.0.0
 */

@Component
@Data
public class InitDataSource {

    @Autowired
    private ProjectProperties projectProperties;

    @PostConstruct
    private void init() {
        initProjectProperties();
    }

    private void initProjectProperties() {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(projectProperties.getYamlPath());
        Properties properties = yaml.getObject();
        assert properties != null;
        if (projectProperties.getUsername() == null) {
            projectProperties.setUsername(properties.getProperty("spring.datasource.username"));
        }
        if (projectProperties.getPassword() == null) {
            projectProperties.setPassword(properties.getProperty("spring.datasource.password"));
        }
        if (projectProperties.getDriver() == null) {
            projectProperties.setDriver(properties.getProperty("spring.datasource.driver-class-name"));
        }
        if (projectProperties.getUrl() == null) {
            projectProperties.setUrl(properties.getProperty("spring.datasource.url"));
        }
    }


}