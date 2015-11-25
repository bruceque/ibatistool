package com.kinglong.processor;

import com.kinglong.config.Config;

import java.io.*;

/**
 * Created by chenjinlong on 15/6/5.
 */
public class MapperProcessor extends BaseProcessor{
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

        File mapperFile = new File(Config.MAPPER_PATH, MAPPER_NAME + ".java");
        if(!mapperFile.exists()) {
            mapperFile.createNewFile();
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
        bw = buildMethodComment(bw, "查询（根据主键ID查询）");
        bw.newLine();
        bw.write("\t" + BEAN_NAME + "  selectByPrimaryKey ( @Param(\"id\") Integer id );");
        bw.newLine();
        bw = buildMethodComment(bw, "删除（根据主键ID删除）");
        bw.newLine();
        bw.write("\t" + "Integer deleteByPrimaryKey ( @Param(\"id\") Integer id );");
//        bw.newLine();
//        bw = buildMethodComment(bw, "添加");
//        bw.newLine();
//        bw.write("\t" + "Integer insert( " + BEAN_NAME + " record );");
        bw.newLine();
        bw = buildMethodComment(bw, "添加 （匹配有值的字段）");
        bw.newLine();
        bw.write("\t" + "Integer insertSelective( " + BEAN_NAME + " record );");
        bw.newLine();
        bw = buildMethodComment(bw, "修改 （匹配有值的字段）");
        bw.newLine();
        bw.write("\t" + "Integer updateByPrimaryKeySelective( " + BEAN_NAME + " record );");
//        bw.newLine();
//        bw = buildMethodComment(bw, "修改（根据主键ID修改）");
//        bw.newLine();
//        bw.write("\t" + "Integer updateByPrimaryKey ( " + BEAN_NAME + " record );");
        bw.newLine();
        bw = buildMethodComment(bw, "批量添加");
        bw.newLine();
        bw.write("\t" + "Integer batchInsert ( @Param(\"" + BEAN_PARAM_LIST_NAME
                + "\") List<" + BEAN_NAME + "> " + BEAN_PARAM_LIST_NAME + " );");
        bw.newLine();
        bw = buildMethodComment(bw, "动态条件查询（支持分页）");
        bw.newLine();
        bw.write("\t" + "List<"+BEAN_NAME+"> selectByBaseConditionPageable ( Map<String,Object> map );");
        bw.newLine();
        bw = buildMethodComment(bw, "动态条件查询总数目");
        bw.newLine();
        bw.write("\t" + "Integer countByBaseCondition ( Map<String,Object> map );");
        bw.newLine();
        bw = buildMethodComment(bw, "批量更新");
        bw.newLine();
        bw.write("\t" + "Integer batchUpdateById ( @Param(\""+BEAN_PARAM_LIST_NAME+"\") List<"+BEAN_NAME+"> "+BEAN_PARAM_LIST_NAME+" );");
        bw.newLine();

        // ----------定义Mapper中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }



}
