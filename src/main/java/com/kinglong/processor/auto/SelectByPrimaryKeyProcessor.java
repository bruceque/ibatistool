package com.kinglong.processor.auto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class SelectByPrimaryKeyProcessor extends AbstractAutoProcessor {
    @Override
    public void buildSQL(BufferedWriter bw, List<String> columns, List<String> types,List<String> comments ) throws IOException {
        // 查询（根据主键ID查询）
        bw.write("\t<!-- 查询（根据主键ID查询） -->");
        bw.newLine();
        bw.write("\t<select id=\"selectByPrimaryKey\" "+generatorRetStr()+" parameterType=\"java.lang." + processType(types.get(0)) + "\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + TABLE_NAME);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0))+generatorJdbcType(types.get(0)) + "}");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完
    }

    @Override
    public void buildMethod(BufferedWriter bw) throws IOException {
        bw = buildMethodComment(bw, "查询（根据主键ID查询）");
        bw.newLine();
        bw.write("\t" + BEAN_NAME + "  selectByPrimaryKey ( @Param(\"id\") Integer id );");
        bw.newLine();
    }
}
