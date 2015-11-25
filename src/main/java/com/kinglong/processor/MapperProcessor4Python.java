package com.kinglong.processor;

import com.kinglong.config.Config;

import java.io.*;

/**
 * Created by chenjinlong on 15/6/5.
 */
public class MapperProcessor4Python extends BaseProcessor{
    /**
     *  构建Mapper文件
     *
     * @throws IOException
     */
    public static void buildMapper() throws IOException {
        File folder = new File(Config.MAPPER_PATH);
        if ( !folder.exists() ) {
            folder.mkdirs();
        }

        File mapperFile = new File(Config.MAPPER_PATH, MAPPER_NAME + ".py");
        if(!mapperFile.exists()) {
            mapperFile.createNewFile();
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("# -*- coding: utf-8 -*-");
        bw.newLine();
        bw.write("__author__ = 'chenjinlong'");
        bw.newLine();
        bw.newLine();
        bw.write("from dal.domain.do." +BEAN_NAME+ " import " + BEAN_NAME + "");
        bw.newLine();
        bw = buildMethodComment4SqlAlchemy(bw, MAPPER_NAME + "数据库操作接口类","");
        bw.newLine();
        bw.newLine();
        //      bw.write("public interface " + MAPPER_NAME + " extends " + mapper_extends + "<" + BEAN_NAME + "> {");
        bw.write("class " + MAPPER_NAME + "(object):");
        bw.newLine();
        bw.newLine();
        // ----------定义Mapper中的方法Begin----------
        bw = buildMethodComment4SqlAlchemy(bw, "查询（根据主键ID查询）");
        bw.newLine();
        bw.write("\tdef selectByPrimaryKey(self,id):");
        bw.newLine();
        bw.write("\t\treturn self.session.query("+BEAN_NAME+").filter("+BEAN_NAME+".id==id).one()");
        bw.newLine();
//        bw = buildMethodComment(bw, "删除（根据主键ID删除）");
//        bw.newLine();
//        bw.write("\t" + "Integer deleteByPrimaryKey ( @Param(\"id\") Integer id );");
//        bw.newLine();
//        bw = buildMethodComment(bw, "添加");
//        bw.newLine();
//        bw.write("\t" + "Integer insert( " + BEAN_NAME + " record );");
//        bw.newLine();
//        bw = buildMethodComment(bw, "添加 （匹配有值的字段）");
//        bw.newLine();
//        bw.write("\t" + "Integer insertSelective( " + BEAN_NAME + " record );");
//        bw.newLine();
//        bw = buildMethodComment(bw, "修改 （匹配有值的字段）");
//        bw.newLine();
//        bw.write("\t" + "Integer updateByPrimaryKeySelective( " + BEAN_NAME + " record );");
//        bw.newLine();
//        bw = buildMethodComment(bw, "修改（根据主键ID修改）");
//        bw.newLine();
//        bw.write("\t" + "Integer updateByPrimaryKey ( " + BEAN_NAME + " record );");
//        bw.newLine();
//        bw = buildMethodComment(bw, "批量添加");
//        bw.newLine();
//        bw.write("\t" + "Integer batchInsert ( @Param(\"" + BEAN_PARAM_LIST_NAME
//                + "\") List<" + BEAN_NAME + "> " + BEAN_PARAM_LIST_NAME + " );");
//        bw.newLine();
//        bw = buildMethodComment(bw, "动态条件查询（支持分页）");
//        bw.newLine();
//        bw.write("\t" + "List<"+BEAN_NAME+"> selectByBaseConditionPageable ( Map<String,Object> map );");
//        bw.newLine();
//        bw = buildMethodComment(bw, "动态条件查询总数目");
//        bw.newLine();
//        bw.write("\t" + "Integer countByBaseCondition ( Map<String,Object> map );");
//        bw.newLine();
//        bw = buildMethodComment(bw, "批量更新");
//        bw.newLine();
//        bw.write("\t" + "Integer batchUpdateById ( @Param(\""+BEAN_PARAM_LIST_NAME+"\") List<"+BEAN_NAME+"> "+BEAN_PARAM_LIST_NAME+" );");
//        bw.newLine();
//
//        // ----------定义Mapper中的方法End----------
//        bw.newLine();
//        bw.write("}");
        bw.flush();
        bw.close();
    }



}
