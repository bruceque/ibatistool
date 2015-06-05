package com.kinglong.processor;

import com.kinglong.config.Config;
import com.kinglong.processor.sql.BaseSqlProcessor;

import java.io.*;
import java.util.List;


/**
 * Created by chenjinlong on 15/6/5.
 */
public class MapperXmlProcessor extends BaseSqlProcessor{
    /**
     *  构建实体类映射XML文件
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    public static void buildMapperXml(List<String> columns, List<String> types, List<String> comments) throws IOException {
        File folder = new File(Config.XML_PATH);
        if ( !folder.exists() ) {
            folder.mkdirs();
        }

        File mapperXmlFile = new File(Config.XML_PATH, MAPPER_NAME + ".xml");
        if(!mapperXmlFile.exists()) {
            mapperXmlFile.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        bw.newLine();
        bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + Config.MAPPER_PACKAGE + "." + MAPPER_NAME + "\">");
        bw.newLine();
        bw.newLine();

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

        // 下面开始写SqlMapper中的方法
        // this.outputSqlMapperMethod(bw, columns, types);
        BaseSqlProcessor.buildSQL(bw, columns, types);

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }

    private static String generatorJdbcType(String type) {
        String str = BaseProcessor.processJdbcType(type);
        if (str==null) {
            return "";
        }
        return " jdbcType=\""+str+"\" ";
    }
}
