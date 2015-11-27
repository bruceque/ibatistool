package com.kinglong.processor;

import com.kinglong.config.Config;
import com.kinglong.processor.auto.common.BaseProcessor;
import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.util.List;

/**
 * Created by chenjinlong on 15/9/7.
 */
public class EntityProcessor4Python extends BaseProcessor {
    /**
     *  生成实体类
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    public static void buildEntityBean(List<String> columns, List<String> types, List<String> comments, String tableComment)
            throws IOException {
        File folder = new File(Config.BEAN_PATH);
        if ( !folder.exists() ) {
            folder.mkdirs();
        }

        File beanFile = new File(Config.BEAN_PATH, BEAN_NAME + ".py");
        if(!beanFile.exists()) {
            beanFile.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("# -*- coding: utf-8 -*-");
        bw.newLine();
        bw.write("__author__ = 'chenjinlong'");
        bw.newLine();
        List<String> sqlAlchemyType = getImportType4Python(types);
        bw.write(String.format("from sqlalchemy import Column,%s", StringUtils.join(sqlAlchemyType,",")));
        bw.newLine();
        bw.write("from sqlalchemy.ext.declarative import declarative_base");
        bw.newLine();
        bw.write("# 创建对象的基类:");
        bw.newLine();
        bw.write("Base = declarative_base()");
        bw.newLine();
        bw.write("class " + BEAN_NAME + "(Base):");
        bw.newLine();
        bw.newLine();
        bw.write("\t# 表的名字:");
        bw.newLine();
        bw.write("\t__tablename__ = '" + TABLE_NAME + "'");
        bw.newLine();
        bw.newLine();
        int size = columns.size();
        bw.write("\t#" + comments.get(1) + "");
        bw.newLine();
        bw.write("\t" + processField(columns.get(0)) + "=Column('" + columns.get(0) +
                "'," + processType4Python(types.get(0)) + ",primary_key=True)");
        bw.newLine();
        for ( int i = 1 ; i < size ; i++) {
            bw.write("\t#" + comments.get(i) + "");
            bw.newLine();
            bw.write("\t" + processField(columns.get(i)) + "=Column('"+columns.get(i)+"',"+processType4Python(types.get(i))+")");
            bw.newLine();
            bw.newLine();
        }
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
