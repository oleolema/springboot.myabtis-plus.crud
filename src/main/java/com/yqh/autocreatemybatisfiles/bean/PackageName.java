/**
 * FileName:   PackageName
 * Author:     O了吗
 * Date:       2019/9/27 19:44
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/27
 * @since 1.0.0
 */

@Data
@ConfigurationProperties(prefix = "package-name")
public class PackageName {

    public String bean = "bean";
    public String service = "service";
    public String mapper = "mapper";
    public String controller = "controller";
    public String serviceImpl = "service.impl";

}