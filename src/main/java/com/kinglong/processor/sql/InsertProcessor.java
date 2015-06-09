package com.kinglong.processor.sql;

import com.kinglong.config.Config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class InsertProcessor extends AbstractSqlProcessor {
    public void buildSQL(BufferedWriter bw, List<String> columns, List<String> types,List<String> comments ) throws IOException {
        int size = columns.size();
        // 添加insert方法
        bw.write("\t<!-- 添加 -->");
        bw.newLine();
        bw.write("\t<insert id=\"insert\" parameterType=\"" + Config.BEAN_PACKAGE+"."+BEAN_NAME + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + TABLE_NAME);
        bw.newLine();
        bw.write(" \t\t(");
        for ( int i = 0 ; i < size ; i++ ) {
            bw.write(columns.get(i));
            if ( i != size - 1 ) {
                bw.write(",");
            }
            if ((i+1)%5==0) {
                bw.newLine();
                bw.write("\t\t");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t\t VALUES ");
        bw.newLine();
        bw.write(" \t\t(");
        for ( int i = 0 ; i < size ; i++ ) {
            bw.write("#{" + processField(columns.get(i))+generatorJdbcType(types.get(i)) + "}");
            if ( i != size - 1 ) {
                bw.write(",");
            }
            if ((i+1)%5==0) {
                bw.newLine();
                bw.write("\t\t");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        // 添加insert完
    }
}
