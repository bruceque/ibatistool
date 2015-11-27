package com.kinglong.processor.auto;

import com.kinglong.config.Config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class BaseResultMapAutoProcessor extends AbstractAutoProcessor {

    @Override
    public void buildSQL(BufferedWriter bw, List<String> columns, List<String> types, List<String> comments) throws IOException {
        bw.write("\t<!--实体映射-->");
        bw.newLine();
        bw.write("\t<resultMap id=\"BaseResultMap\" type=\"" + Config.BEAN_PACKAGE + "." + BEAN_NAME + "\">");
        bw.newLine();
        bw.write("\t\t<!--" + comments.get(0) + "-->");
        bw.newLine();
        bw.write("\t\t<id column=\"" + columns.get(0) + "\" property=\"" + processField(columns.get(0)) + "\"" +
                generatorJdbcType(types.get(0))+"/>");
        bw.newLine();
        int size = columns.size();
        for ( int i = 1 ; i < size ; i++ ) {
            bw.write("\t\t<!--" + comments.get(i) + "-->");
            bw.newLine();
            bw.write("\t\t<result column=\""
                    + columns.get(i) + "\" property=\"" + processField(columns.get(i)) + "\" " + generatorJdbcType(types.get(i))+"/>");
            bw.newLine();
        }
        bw.write("\t</resultMap>");

        bw.newLine();
        bw.newLine();
        bw.newLine();
    }

    @Override
    public void buildMethod(BufferedWriter bw) throws IOException {
        return;
    }

    protected static String generatorJdbcType(String type) {
        String str = processJdbcType(type);
        if (str==null) {
            return "";
        }
        return " jdbcType=\""+str+"\" ";
    }
}
