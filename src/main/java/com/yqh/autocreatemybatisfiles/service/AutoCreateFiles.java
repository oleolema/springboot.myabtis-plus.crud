/**
 * FileName:   AutoCreateFiles
 * Author:     O了吗
 * Date:       2019/9/26 21:44
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
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/26
 * @since 1.0.0
 */
@Component
@Slf4j
public class AutoCreateFiles {

    @Autowired
    private ProjectProperties projectProperties;

    @Autowired
    private InitDatabase initDatabase;

    @Autowired
    private ParseBeanTemplate parseBeanTemplate;

    @Autowired
    private ParseMapperTemplate parseMapperTemplate;

    @Autowired
    private ParseServiceTemplate parseServiceTemplate;

    @Autowired
    private ParseServiceImplTemplate parseServiceImplTemplate;

    @Autowired
    private ParseControllerTemplate parseControllerTemplate;


    public void creteAll() throws IOException {
        createBeans();
        createMapper();
        createService();
        createServiceImpl();
        createController();
    }

    public void createBeans() throws IOException {
        createClasses(ParseBeanTemplate.PACKAGE_NAME);
    }

    public void createService() throws IOException {
        createClasses(ParseServiceTemplate.PACKAGE_NAME);
    }

    public void createMapper() throws IOException {
        createClasses(ParseMapperTemplate.PACKAGE_NAME);
    }

    public void createServiceImpl() throws IOException {
        createClasses(ParseServiceImplTemplate.PACKAGE_NAME);
    }

    public void createController() throws IOException {
        createClasses(ParseControllerTemplate.PACKAGE_NAME);
    }

    public void createClasses(String packageName) throws IOException {
        File pack = mkPackage(MyUtil.packageToPath(packageName));
        for (Map.Entry<String, TableDesc> entry : initDatabase.getTableDescMap().entrySet()) {
            createClass(pack, entry.getValue());
        }
    }

    private void createClass(File pack, TableDesc tableDesc) throws IOException {
        String className = MyUtil.toClassName(tableDesc.getTableName());
        if (ParseBeanTemplate.PACKAGE_NAME.equals(pack.getName())) {
            writeFile(pack, className + ".java", parseBeanTemplate.parseTemplate(tableDesc));
        } else if (ParseMapperTemplate.PACKAGE_NAME.equals(pack.getName())) {
            writeFile(pack, className + "Mapper.java", parseMapperTemplate.parseTemplate(tableDesc));
        } else if (ParseServiceTemplate.PACKAGE_NAME.equals(pack.getName())) {
            writeFile(pack, className + "Service.java", parseServiceTemplate.parseTemplate(tableDesc));
        } else if (ParseServiceImplTemplate.PACKAGE_NAME.equals(pack.getParentFile().getName() + "." + pack.getName())) {
            writeFile(pack, className + "ServiceImpl.java", parseServiceImplTemplate.parseTemplate(tableDesc));
        } else if (ParseControllerTemplate.PACKAGE_NAME.equals(pack.getName())) {
            writeFile(pack, className + "Controller.java", parseControllerTemplate.parseTemplate(tableDesc));
        } else {
            throw new RuntimeException("不支持的类型" + pack.getName());
        }
    }

    private void writeFile(File pack, String name, String content) throws IOException {
        File file = mkClass(pack, name);
        if (file == null) {
            log.warn(pack.getName() + "." + name + "已存在");
            return;
        }
        MyUtil.writeFile(file, content);
        log.info(pack.getName() + "." + name + "已创建");
    }

    /**
     * 在当前工程路径下创建包
     *
     * @param pack
     * @return
     * @throws IOException
     */
    public File mkPackage(String pack) throws IOException {
        File bootPath = projectProperties.getBootRootPath().getFile();
        File packPath = new File(bootPath, pack);
        if (!packPath.exists() || projectProperties.isForceCreate()) {
            packPath.mkdir();
            return packPath;
        }
        return packPath;
    }

    /**
     * 在指定路径下创建类
     *
     * @param parent
     * @param cla
     * @return
     * @throws IOException
     */
    public File mkClass(File parent, String cla) throws IOException {
        File claPath = new File(parent, cla);
        if (!claPath.exists() || projectProperties.isForceCreate()) {
            claPath.createNewFile();
            return claPath;
        }
        return null;
    }

}