package com.kinglong.processor.auto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class DeleteByPrimaryKeyProcessor extends AbstractAutoProcessor {
    @Override
    public void buildSQL(BufferedWriter bw, List<String> columns, List<String> types,List<String> comments ) throws IOException {
        // 删除（根据主键ID删除）
        bw.write("\t<!--删除：根据主键ID删除-->");
        bw.newLine();
        bw.write("\t<delete id=\"deleteByPrimaryKey\" parameterType=\"java.lang." + processType(types.get(0)) + "\">");
        bw.newLine();
        bw.write("\t\t DELETE FROM " + TABLE_NAME);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0))+generatorJdbcType(types.get(0)) + "}");
        bw.newLine();
        bw.write("\t</delete>");
        bw.newLine();
        bw.newLine();
        // 删除完
    }

    @Override
    public void buildMethod(BufferedWriter bw) throws IOException {
        bw = buildMethodComment(bw, "删除（根据主键ID删除）");
        bw.newLine();
        bw.write("\t" + "Integer deleteByPrimaryKey ( @Param(\"id\") Integer id );");
        bw.newLine();
    }
}
