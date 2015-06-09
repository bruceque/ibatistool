package com.kinglong.processor.sql;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class CountByBaseConditionProcessor extends AbstractSqlProcessor {
    public void buildSQL(BufferedWriter bw, List<String> columns, List<String> types, List<String> comments) throws IOException {
        // 根据动态条件查询总数目（根据BaseCondition查询总数目）
        bw.write("\t<!-- 根据动态条件查询总数目（根据BaseCondition查询总数目） -->");
        bw.newLine();
        bw.write("\t<select id=\"countByBaseCondition\" parameterType=\"java.util.Map\" resultType=\"java.lang.Integer\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t count(*)");
        bw.newLine();
        bw.write("\t\t FROM " + TABLE_NAME);
        bw.newLine();
        bw.write("\t\t <include refid=\"BaseCondition\"/>");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完
    }
}
