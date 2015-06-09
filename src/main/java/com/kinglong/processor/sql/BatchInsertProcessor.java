package com.kinglong.processor.sql;

import com.kinglong.config.Config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class BatchInsertProcessor extends AbstractSqlProcessor {
    public void buildSQL(BufferedWriter bw,
                         List<String> columns,
                         List<String> types,
                         List<String> comments) throws IOException {
        int size = columns.size();
        // 添加batchInsert方法
        bw.write("\t<!-- 批量添加 -->");
        bw.newLine();
        bw.write("\t<insert id=\"batchInsert\" parameterType=\"" + Config.BEAN_PACKAGE + "." + BEAN_NAME + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + TABLE_NAME);
        bw.newLine();
        bw.write(" \t\t(");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t) ");
        bw.newLine();
        bw.write("\t\t VALUES ");
        bw.newLine();
        bw.write("\t\t <foreach collection=\"" + BEAN_PARAM_LIST_NAME + "\" item=\"item\" index=\"index\" separator=\",\">");
        bw.newLine();
        bw.write(" \t\t\t(");
        bw.newLine();
        for (int i = 0 ; i < size; i++ ) {
            bw.write("\t\t\t");
            bw.write("#{item." + processField(columns.get(i))+generatorJdbcType(types.get(i)) + "}");
            if ( i != size - 1 ) {
                bw.write(",");
            }
            bw.newLine();
        }
        bw.write("\t\t\t)");
        bw.newLine();

        bw.write("\t\t</foreach>");
        bw.newLine();

        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        // 添加batchInsert完
    }
}
