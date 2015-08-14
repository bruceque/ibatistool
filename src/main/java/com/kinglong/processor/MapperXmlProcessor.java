package com.kinglong.processor;

import com.kinglong.config.Config;
import com.kinglong.processor.sql.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenjinlong on 15/6/5.
 */
public class MapperXmlProcessor extends BaseProcessor{
    public static List<BaseSqlProcessor> baseSqlProcessorList = new ArrayList<BaseSqlProcessor>();

    static {
        baseSqlProcessorList.add(new BaseResultMapSqlProcessor());
        baseSqlProcessorList.add(new BaseColumnListProcessor());
        baseSqlProcessorList.add(new BaseConditionSqlProcessor());
        baseSqlProcessorList.add(new SelectByBaseConditionSqlProcessor());
        baseSqlProcessorList.add(new DeleteByPrimaryKeyProcessor());
        baseSqlProcessorList.add(new InsertSelectiveProcessor());
//        baseSqlProcessorList.add(new InsertProcessor());
        baseSqlProcessorList.add(new SelectByPrimaryKeyProcessor());
//        baseSqlProcessorList.add(new UpdateByPrimaryKeyProcessor());
        baseSqlProcessorList.add(new UpdateByPrimaryKeySelectiveProcessor());
        baseSqlProcessorList.add(new BatchInsertProcessor());
        baseSqlProcessorList.add(new CountByBaseConditionProcessor());
        baseSqlProcessorList.add(new SelectByBaseConditionProcessor());
        baseSqlProcessorList.add(new BatchUpdateByIdProcessor());
    }
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

        // 下面开始写SqlMapper中的方法
        for (BaseSqlProcessor baseSqlProcessor : baseSqlProcessorList) {
            baseSqlProcessor.buildSQL(bw,columns,types,comments);
        }

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }
}
