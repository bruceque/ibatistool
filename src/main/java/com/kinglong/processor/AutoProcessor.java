package com.kinglong.processor;

import com.kinglong.config.Config;
import com.kinglong.processor.auto.*;
import com.kinglong.processor.auto.common.BaseProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenjinlong on 15/6/5.
 */
public class AutoProcessor extends BaseProcessor {
    public static List<BaseAutoProcessor> baseAutoProcessorList = new ArrayList<BaseAutoProcessor>();

    static {
        baseAutoProcessorList.add(new BaseResultMapAutoProcessor());
        baseAutoProcessorList.add(new BaseColumnListProcessor());
        baseAutoProcessorList.add(new BaseConditionAutoProcessor());
        baseAutoProcessorList.add(new SelectByBaseConditionAutoProcessor());
        baseAutoProcessorList.add(new DeleteByPrimaryKeyProcessor());
        baseAutoProcessorList.add(new InsertSelectiveProcessor());
//        baseSqlProcessorList.add(new InsertProcessor());
        baseAutoProcessorList.add(new SelectByPrimaryKeyProcessor());
//        baseSqlProcessorList.add(new UpdateByPrimaryKeyProcessor());
        baseAutoProcessorList.add(new UpdateByPrimaryKeySelectiveProcessor());
        baseAutoProcessorList.add(new BatchInsertProcessor());
        baseAutoProcessorList.add(new CountByBaseConditionProcessor());
        baseAutoProcessorList.add(new SelectByBaseConditionProcessor());
//        baseAutoProcessorList.add(new BatchUpdateByIdProcessor());
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
            Boolean xmlFolderCrateRs = folder.mkdirs();
            if (!xmlFolderCrateRs) {
                throw new RuntimeException("创建XML的目录失败");
            }
        }

        File mapperXmlFile = new File(Config.XML_PATH, MAPPER_NAME + ".xml");
        if(!mapperXmlFile.exists()) {
            Boolean mapperXmalFileCreateRs = mapperXmlFile.createNewFile();
            if (!mapperXmalFileCreateRs) {
                throw new RuntimeException("创建XML文件失败");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        bw.newLine();
        bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + Config.MAPPER_PACKAGE + "." + MAPPER_EXT_NAME + "\">");
        bw.newLine();
        bw.newLine();

        // 下面开始写SqlMapper中的方法
        for (BaseAutoProcessor baseAutoProcessor : baseAutoProcessorList) {
            baseAutoProcessor.buildSQL(bw,columns,types,comments);
        }

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }

    /**
     *  构建实体类映射XML 扩展文件
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    public static void buildMapperExtXml(List<String> columns, List<String> types, List<String> comments) throws IOException {

        File folder = new File(Config.XML_PATH);
        if ( !folder.exists() ) {
            Boolean xmlFolderCrateRs = folder.mkdirs();
            if (!xmlFolderCrateRs) {
                throw new RuntimeException("创建XML的目录失败");
            }
        }

        File mapperXmlFile = new File(Config.XML_PATH, MAPPER_EXT_NAME + ".xml");
        if(!mapperXmlFile.exists()) {
            Boolean mapperXmalFileCreateRs = mapperXmlFile.createNewFile();
            if (!mapperXmalFileCreateRs) {
                throw new RuntimeException("创建XML文件失败");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        bw.newLine();
        bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + Config.MAPPER_PACKAGE + "." + MAPPER_EXT_NAME + "\">");
        bw.newLine();
        bw.newLine();

        // 下面开始写SqlMapper中的方法
//        for (BaseAutoProcessor baseAutoProcessor : baseAutoProcessorList) {
//            baseAutoProcessor.buildSQL(bw,columns,types,comments);
//        }

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }

    public static void buildMapper() throws IOException {
        File folder = new File(Config.MAPPER_PATH);
        if ( !folder.exists() ) {
            Boolean mapperFolderCreateRs = folder.mkdirs();
            if (!mapperFolderCreateRs) {
                throw new RuntimeException("创建Mapper的目录失败");
            }
        }

        File mapperFile = new File(Config.MAPPER_PATH, MAPPER_NAME + ".java");
        if(!mapperFile.exists()) {
            Boolean mapperFileCreateRs = mapperFile.createNewFile();
            if (!mapperFileCreateRs) {
                throw new RuntimeException("创建Mapper文件失败");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + Config.MAPPER_PACKAGE + ";");
        bw.newLine();
        bw.newLine();
        bw.write("import " + Config.BEAN_PACKAGE + "." + BEAN_NAME + ";");
        bw.newLine();
        bw.write("import org.apache.ibatis.annotations.Param;");
        bw.newLine();
        bw.write("import java.util.List;");
        bw.newLine();
        bw.write("import java.util.Map;");
        bw = buildClassComment(bw, MAPPER_NAME + "数据库操作接口类");
        bw.newLine();
        bw.newLine();
        //      bw.write("public interface " + MAPPER_NAME + " extends " + mapper_extends + "<" + BEAN_NAME + "> {");
        bw.write("public interface " + MAPPER_NAME + "{");
        bw.newLine();
        bw.newLine();
        // ----------定义Mapper中的方法Begin----------
        // 下面开始写SqlMapper中的方法
        for (BaseAutoProcessor baseAutoProcessor : baseAutoProcessorList) {
            baseAutoProcessor.buildMethod(bw);
        }
        // ----------定义Mapper中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }

    public static void buildMapperExt() throws IOException {
        File folder = new File(Config.MAPPER_PATH);
        if ( !folder.exists() ) {
            Boolean mapperFolderCreateRs = folder.mkdirs();
            if (!mapperFolderCreateRs) {
                throw new RuntimeException("创建Mapper的目录失败");
            }
        }

        File mapperFile = new File(Config.MAPPER_PATH, MAPPER_EXT_NAME + ".java");
        if(!mapperFile.exists()) {
            Boolean mapperFileCreateRs = mapperFile.createNewFile();
            if (!mapperFileCreateRs) {
                throw new RuntimeException("创建Mapper文件失败");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + Config.MAPPER_PACKAGE + ";");
        bw.newLine();
        bw.newLine();
        bw.write("import " + Config.BEAN_PACKAGE + "." + BEAN_NAME + ";");
        bw.newLine();
        bw.write("import org.apache.ibatis.annotations.Param;");
        bw.newLine();
        bw.write("import java.util.List;");
        bw.newLine();
        bw.write("import java.util.Map;");
        bw = buildClassComment(bw, MAPPER_EXT_NAME + "数据库操作接口扩展类");
        bw.newLine();
        bw.newLine();
        //      bw.write("public interface " + MAPPER_NAME + " extends " + mapper_extends + "<" + BEAN_NAME + "> {");
        bw.write("public interface " + MAPPER_EXT_NAME + " extends "+MAPPER_NAME+"{");
        bw.newLine();
        bw.newLine();
        // ----------定义Mapper中的方法Begin----------
        // 下面开始写SqlMapper中的方法
//        for (BaseAutoProcessor baseAutoProcessor : baseAutoProcessorList) {
//            baseAutoProcessor.buildMethod(bw);
//        }
        // ----------定义Mapper中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }
}
