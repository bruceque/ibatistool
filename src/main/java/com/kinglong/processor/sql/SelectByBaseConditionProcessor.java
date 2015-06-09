package com.kinglong.processor.sql;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class SelectByBaseConditionProcessor extends AbstractSqlProcessor {
    public void buildSQL(BufferedWriter bw, List<String> columns, List<String> types, List<String> comments) throws IOException {
        // 根据动态条件查询（根据BaseCondition查询）
        bw.write("\t<!-- 根据动态条件查询（根据BaseCondition查询） -->");
        bw.newLine();
        bw.write("\t<select id=\"selectByBaseConditionPageable\" " + generatorRetStr()
                + " parameterType=\"java.util.Map\">");
        bw.newLine();
        bw.write("\t\t<include refid=\"SelectByBaseConditionSql\"/>");
        bw.newLine();
        bw.write("\t\t<if test=\"orderStr != null\">");
        bw.newLine();
        bw.write("\t\t\torder by #{orderStr}");
        bw.newLine();
        bw.write("\t\t\t\t<if test=\"orderStyle!=null\">");
        bw.newLine();
        bw.write("\t\t\t\t\t#{orderStyle}");
        bw.newLine();
        bw.write("\t\t\t\t</if>");

        bw.newLine();
        bw.write("\t\t</if>");
        bw.newLine();
        bw.write("\t\t<if test=\"start != null and limit != null\">");
        bw.newLine();
        bw.write("\t\t\tlimit #{start},#{limit}");
        bw.newLine();
        bw.write("\t\t</if>");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完
    }
}
