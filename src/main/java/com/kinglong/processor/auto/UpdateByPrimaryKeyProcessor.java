package com.kinglong.processor.auto;

import com.kinglong.config.Config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class UpdateByPrimaryKeyProcessor extends AbstractAutoProcessor {
    @Override
    public void buildSQL(BufferedWriter bw, List<String> columns, List<String> types,List<String> comments ) throws IOException {
        int size = columns.size();
        // ----- 修改（匹配有值的字段）
        bw.write("\t<!-- 修 改-->");
        bw.newLine();
        bw.write("\t<update id=\"updateByPrimaryKey\" parameterType=\"" + Config.BEAN_PACKAGE+"."+BEAN_NAME + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + TABLE_NAME);
        bw.newLine();
        bw.write("\t\t SET ");

        bw.newLine();
        tempField = null;
        for ( int i = 1 ; i < size ; i++ ) {
            tempField = processField(columns.get(i)) ;
            bw.write("\t\t\t " + columns.get(i) + " = #{" + tempField+generatorJdbcType(types.get(i)) + "}");
            if ( i != size - 1 ) {
                bw.write(",");
            }
            bw.newLine();
        }

        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) +generatorJdbcType(types.get(0))+"}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();
    }

    @Override
    public void buildMethod(BufferedWriter bw) throws IOException {
        bw = buildMethodComment(bw, "修改（根据主键ID修改）");
        bw.newLine();
        bw.write("\t" + "Integer updateByPrimaryKey ( " + BEAN_NAME + " record );");
        bw.newLine();
    }
}
