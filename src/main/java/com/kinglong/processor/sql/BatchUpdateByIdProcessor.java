package com.kinglong.processor.sql;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class BatchUpdateByIdProcessor extends AbstractSqlProcessor {
    public void buildSQL(BufferedWriter bw, List<String> columns, List<String> types, List<String> comments) throws IOException {
        int size = columns.size();
        // 添加batchInsert方法
        bw.write("\t<!-- 批量更新 -->");
        bw.newLine();
        bw.write("\t<update id=\"batchUpdateById\" parameterType=\"" + BEAN_FULL_NAME + "\">");
        bw.newLine();
        bw.write("\t\tupdate " + TABLE_NAME);
        bw.newLine();
        bw.write("\t\t<trim prefix=\"set\" suffixOverrides=\",\">");
        bw.newLine();
        for (int i = 0;i<size;i++) {
            tempColumn = columns.get(i);
            tempField = processField(tempColumn);
            bw.write("\t\t\t");
            bw.write("<trim prefix=\""+tempColumn+" =case id\" suffix=\"end,\">");
            bw.newLine();
            bw.write("\t\t\t\t");
            bw.write("<foreach collection=\"" + BEAN_PARAM_LIST_NAME + "\" item=\"item\" index=\"index\">");
            bw.newLine();
            bw.write("\t\t\t\t\t");
            bw.write("<if test=\"item."+tempField+"!=null\">");
            bw.newLine();
            bw.write("\t\t\t\t\t\t");
            bw.write("when #{item.id,jdbcType=INTEGER} then #{item."+tempField+generatorJdbcType(types.get(i))+"}");
            bw.newLine();
            bw.write("\t\t\t\t\t");
            bw.write("</if>");
            bw.newLine();
            bw.write("\t\t\t\t\t");
            bw.write("<if test=\"item."+tempField+"==null\">");
            bw.newLine();
            bw.write("\t\t\t\t\t\t");
            bw.write("when #{item.id,jdbcType=INTEGER} then "+tempColumn);
            bw.newLine();
            bw.write("\t\t\t\t\t");
            bw.write("</if>");
            bw.newLine();
            bw.write("\t\t\t\t");
            bw.write("</foreach>");
            bw.newLine();
            bw.write("\t\t\t");
            bw.write("</trim>");
            bw.newLine();
        }
        bw.newLine();
        bw.write("\t\t</trim>");
        bw.newLine();
        bw.write("\t\twhere");
        bw.newLine();
        bw.write("\t\tid IN");
        bw.newLine();
        bw.write("\t\t<foreach collection=\""+BEAN_PARAM_LIST_NAME+"\" separator=\",\" item=\"item\" index=\"index\" " +
                "open=\"(\" close=\")\">");
        bw.newLine();
        bw.write("\t\t\t#{item.id,jdbcType=INTEGER}");
        bw.newLine();
        bw.write("\t\t</foreach>");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
    }
}
