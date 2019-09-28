/**
 * FileName:   MyUtil
 * Author:     O了吗
 * Date:       2019/9/26 21:31
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/26
 * @since 1.0.0
 */
public class MyUtil {

    /**
     * package转路径
     * @param pack
     * @return
     */
    public static String packageToPath(String pack) {
        return pack.replaceAll("\\.", "/");
    }


    /**
     * string写入file
     * @param file
     * @param s
     */
    public static void writeFile(File file, String s) {
        try (
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        ) {
            out.write(s.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取file为字符串
     * @param file
     * @return
     */
    public static String readFile(File file) {
        return readFile(new FileSystemResource(file));
    }

    /**
     * 读取file为字符串
     * @param resource
     * @return
     */
    public static String readFile(Resource resource) {
        try (
                BufferedInputStream in = new BufferedInputStream(resource.getInputStream());
        ) {
            StringBuilder sb = new StringBuilder();
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = in.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 在指定路径中深度搜索某个文件
     * @param file
     * @param name
     * @return
     */
    public static File findInChildrenFiles(File file, String name) {
        File[] targetFiles = file.listFiles();
        if (targetFiles != null) {
            for (File f : targetFiles) {
                if (f.getName().equals(name)) {
                    return f;
                }
                if (f.isDirectory()) {
                    findInChildrenFiles(f, name);
                }
            }
        }
        return null;
    }

    /**
     * 在指定路径中向父路径查询某个文件
     * @param file
     * @param name
     * @param consumer 每级路径得到的文件
     * @return
     */
    public static File findInParentFiles(File file, String name, Consumer<File> consumer) {
        if (consumer != null) {
            consumer.accept(file);
        }
        file = file.getParentFile();
        File[] targetFiles = file.listFiles(pathname -> pathname.getName().equals(name));
        if (targetFiles != null && targetFiles.length == 1) {
            return targetFiles[0];
        }
        return findInParentFiles(file, name, consumer);
    }

    /**
     * 在指定路径中向父路径查询某个文件
     * @param file
     * @param name
     * @return
     */
    public static File findInParentFiles(File file, String name) {
        return findInParentFiles(file, name, null);
    }


    /**
     * 数据库名转java Class名
     * @param s
     * @return
     */
    public static String toClassName(String s) {
        return firstCharUpperCase(toCamel(s));
    }

    public static String firstCharUpperCase(String s) {
        char[] cs = s.toCharArray();
        cs[0] = Character.toUpperCase(cs[0]);
        return new String(cs);
    }

    /**
     * 数据库名转驼峰式命名
     *
     * @param s
     * @return
     */
    public static String toCamel(String s) {
        char[] cs = s.toCharArray();
        char[] cs1 = new char[cs.length];
        int i = 0;
        for (int j = 0; j < cs.length; j++) {
            if (i > 0 && (cs[j] == '_' || cs[j] == '-')) {
                cs1[i++] = Character.toUpperCase(cs[++j]);
            } else {
                cs1[i++] = cs[j];
            }
        }
        return new String(cs1).trim();
    }

}