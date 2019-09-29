/**
 * FileName:   ProjectProperties
 * Author:     O了吗
 * Date:       2019/9/26 16:01
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.bean;

import com.yqh.autocreatemybatisfiles.util.MyUtil;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/26
 * @since 1.0.0
 */
@Data
@FieldNameConstants
@ConfigurationProperties(prefix = ProjectProperties.PREFIX)
public class ProjectProperties {
    public static final String PREFIX = "project";


    /**
     * 数据库配置
     */
    private String username;
    private String password;
    private String driver;
    private String url;

    /**
     * 数据库配置文件路径
     */
    private Resource yamlPath;

    /**
     * boot启动类根路径
     */
    private Resource bootRootPath;

    /**
     * 强制创建
     */
    private boolean forceCreate;

    private String packageName;

    @PostConstruct
    public void init() throws IOException {
        pathInit();
        packageNameInit();
    }


    private void packageNameInit() throws IOException {
        StringBuilder sb = new StringBuilder();
        if (packageName == null) {
            MyUtil.findInParentFiles(bootRootPath.getFile(), "java", file -> {
                sb.insert(0, file.getName() + ".");
            });
            sb.setLength(sb.length() - 1);
            packageName = sb.toString().substring(5);
        }
    }


    private void pathInit() throws IOException {
        if (bootRootPath != null) {
            if (yamlPath == null) {
                File resourcePath = MyUtil.findInParentFiles(bootRootPath.getFile(),
                        "resources");
                File yamlFile = MyUtil.findInChildrenFiles(resourcePath, "application.yml");
                Assert.notNull(yamlFile, "文件 application.yml 未找到");
                yamlPath = new FileSystemResource(yamlFile);
            }

        }
    }

}