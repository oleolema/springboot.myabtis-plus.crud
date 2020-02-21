/**
 * FileName:   TypeUtil
 * Author:     O了吗
 * Date:       2019/9/27 16:53
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.util;

import java.util.*;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/27
 * @since 1.0.0
 */
public class TypeUtil {
    private static Map<String, List<String>> types = new HashMap<>();

    private static Map<String, String> typeImports = new HashMap<>();

    static {
        types.put("Integer", Arrays.asList("int"));
        types.put("Double", Arrays.asList("double", "float", "real", "decimal"));
        types.put("String", Arrays.asList("char", "text", "varchar"));
        types.put("LocalDateTime", Arrays.asList("date", "time", "year"));
        types.put("byte[]", Arrays.asList("blob"));
        types.put("Boolean", Arrays.asList("bool", "bit(1)"));
    }

    static {
        typeImports.put("LocalDateTime", "java.time.LocalDateTime");
    }

    /**
     * 将数据库类型转换成java类型
     *
     * @param type
     * @return
     */
    public static String toType(String type, HashSet<String> imports) {
        for (Map.Entry<String, List<String>> entry : types.entrySet()) {
            List<String> list = entry.getValue();
            for (String s : list) {
                if (contains(type, s)) {
                    String key = entry.getKey();
                    if (typeImports.containsKey(key)) {
                        imports.add(typeImports.get(key));
                    }
                    return key;
                }
            }
        }
        return null;
    }

    private static boolean isStartOrEnd(String s, String s1) {
        return s.startsWith(s1) || s.endsWith(s1);
    }

    private static boolean contains(String s, String s1) {
        return s.contains(s1);
    }

}