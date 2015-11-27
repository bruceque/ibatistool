package com.kinglong.processor.auto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class BaseColumnListProcessor extends AbstractAutoProcessor {
    @Override
    public void buildSQL(BufferedWriter bw, List<String> columns, List<String> types,List<String> comments ) throws IOException {
        int size = columns.size();
        // 通用结果列
        bw.write("\t<!-- 通用查询结果列-->");
        bw.newLine();
        bw.write("\t<sql id=\"Base_Column_List\">");
        bw.newLine();

        bw.write("\t\t id,");
        for ( int i = 1 ; i < size ; i++ ) {
            bw.write(columns.get(i));
            if ( i != size - 1 ) {
                bw.write(",");
            }
            if ((i+1)%5==0) {
                bw.newLine();
                bw.write("\t\t");
            }
        }

        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();
    }

    @Override
    public void buildMethod(BufferedWriter bw) throws IOException {
        return;
    }
}
