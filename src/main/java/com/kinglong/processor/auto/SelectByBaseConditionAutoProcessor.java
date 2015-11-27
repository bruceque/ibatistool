package com.kinglong.processor.auto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class SelectByBaseConditionAutoProcessor extends AbstractAutoProcessor {

    @Override
    public void buildSQL(BufferedWriter bw,
                         List<String> columns,
                         List<String> types,
                         List<String> comments) throws IOException {
        // 根据动态条件查询语句（根据BaseCondition查询语句）
        bw.write("\t<!-- 根据动态条件查询语句（根据BaseCondition查询语句） -->");
        bw.newLine();
        bw.write("\t<sql id=\"SelectByBaseConditionSql\" >");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + TABLE_NAME);
        bw.newLine();
        bw.write("\t\t <include refid=\"BaseCondition\"/>");
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();
        // 查询完

    }

    @Override
    public void buildMethod(BufferedWriter bw) throws IOException {
        return;
    }
}
