package com.kinglong.processor.sql;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class BaseConditionSqlProcessor extends AbstractSqlProcessor {
    public void buildSQL(BufferedWriter bw,
                         List<String> columns,
                         List<String> types,
                         List<String> comments) throws IOException {
        Integer size = columns.size();
        // 添加batchInsert方法
        bw.write("\t<!-- 动态条件 -->");
        bw.newLine();
        bw.write("\t<sql id=\"BaseCondition\">");
        bw.newLine();
        bw.write("\t\t<where>");
        bw.newLine();
        for ( int i = 0 ; i < size; i++ ) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.newLine();
            bw.write("\t\t\t\t AND " + columns.get(i) + "=#{"+tempField+
                    generatorJdbcType(types.get(i))+"}");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }
        bw.write("\t\t</where>");
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();

    }
}
