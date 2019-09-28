/**
 * FileName:   TableDesc
 * Author:     O了吗
 * Date:       2019/9/27 11:02
 * Description:
 * History:
 * author:     oleolema
 */
package com.yqh.autocreatemybatisfiles.bean;

import lombok.Data;
import lombok.experimental.Delegate;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈〉
 *
 * @author O了吗
 * @create 2019/9/27
 * @since 1.0.0
 */
@Data
@FieldNameConstants
public class TableDesc {
    @Delegate
    private List<Desc> tableDesc = new ArrayList<>();
    private String tableName;

    @Data
    public static class Desc {
        private String field;
        private String type;
        private String nullable;
        private String key;
        private String def;
        private String extra;
    }

}