package com.kinglong.processor.sql;

import com.kinglong.config.Config;
import com.kinglong.processor.BaseProcessor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/5.
 */
public class BaseSqlProcessor extends BaseProcessor{
    public static void buildSQL( BufferedWriter bw, List<String> columns, List<String> types ) throws IOException {
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


        // 添加insert方法
        bw.write("\t<!-- 添加 -->");
        bw.newLine();
        bw.write("\t<insert id=\"insert\" parameterType=\"" + Config.BEAN_PACKAGE+"."+BEAN_NAME + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + TABLE_NAME);
        bw.newLine();
        bw.write(" \t\t(");
        for ( int i = 0 ; i < size ; i++ ) {
            bw.write(columns.get(i));
            if ( i != size - 1 ) {
                bw.write(",");
            }
            if ((i+1)%5==0) {
                bw.newLine();
                bw.write("\t\t");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t\t VALUES ");
        bw.newLine();
        bw.write(" \t\t(");
        for ( int i = 0 ; i < size ; i++ ) {
            bw.write("#{" + processField(columns.get(i))+generatorJdbcType(types.get(i)) + "}");
            if ( i != size - 1 ) {
                bw.write(",");
            }
            if ((i+1)%5==0) {
                bw.newLine();
                bw.write("\t\t");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        // 添加insert完


        //---------------  insert方法（匹配有值的字段）
        bw.write("\t<!-- 添加 （匹配有值的字段）-->");
        bw.newLine();
        bw.write("\t<insert id=\"insertSelective\" parameterType=\"" + Config.BEAN_PACKAGE+"."+BEAN_NAME + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + TABLE_NAME);
        bw.newLine();
        bw.write("\t\t <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        String tempField = null;
        for ( int i = 0 ; i < size ; i++ ) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.newLine();
            bw.write("\t\t\t\t " + columns.get(i) + ",");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.newLine();
        bw.write("\t\t </trim>");
        bw.newLine();

        bw.write("\t\t <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        tempField = null;
        for ( int i = 0 ; i < size ; i++ ) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + "!=null\">");
            bw.newLine();
            bw.write("\t\t\t\t #{" + tempField+generatorJdbcType(types.get(i)) + "},");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.write("\t\t </trim>");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        //---------------  完毕


        // 修改update方法
        bw.write("\t<!-- 修 改-->");
        bw.newLine();
        bw.write("\t<update id=\"updateByPrimaryKeySelective\" parameterType=\"" + Config.BEAN_PACKAGE+"."+BEAN_NAME + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + TABLE_NAME);
        bw.newLine();
        bw.write(" \t\t <set> ");
        bw.newLine();

        tempField = null;
        for ( int i = 1 ; i < size ; i++ ) {
            tempField = processField(columns.get(i)) ;
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.newLine();
            bw.write("\t\t\t\t " + columns.get(i) + " = #{" + tempField+ generatorJdbcType(types.get(i)) + "},");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.newLine();
        bw.write(" \t\t </set>");
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0))+generatorJdbcType(types.get(0)) + "}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();
        // update方法完毕

        // ----- 修改（匹配有值的字段）
        bw.write("\t<!-- 修 改-->");
        bw.newLine();
        bw.write("\t<update id=\"updateByPrimaryKey\" parameterType=\"" + Config.BEAN_PACKAGE+"."+BEAN_NAME + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + TABLE_NAME);
        bw.newLine();
        bw.write("\t\t SET ");

        bw.newLine();
        tempField = null;
        for ( int i = 1 ; i < size ; i++ ) {
            tempField = processField(columns.get(i)) ;
            bw.write("\t\t\t " + columns.get(i) + " = #{" + tempField+generatorJdbcType(types.get(i)) + "}");
            if ( i != size - 1 ) {
                bw.write(",");
            }
            bw.newLine();
        }

        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) +generatorJdbcType(types.get(0))+"}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();
    }

    private static String generatorRetStr() {
        if (Config.RTN_TYPE==1) {
            return  "resultType=\"" +Config.BEAN_PACKAGE+"."+BEAN_NAME + "\"";
        } else {
            return "resultMap=\"BaseResultMap\"";
        }

    }

    private static String generatorJdbcType(String type) {
        String str = BaseProcessor.processJdbcType(type);
        if (str==null) {
            return "";
        }
        return ",jdbcType="+str+" ";
    }
}
