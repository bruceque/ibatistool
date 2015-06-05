package com.kinglong.processor;

import com.kinglong.config.Config;

import java.io.*;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/5.
 */
public class EntityProcessor extends BaseProcessor{
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

        File beanFile = new File(Config.BEAN_PATH, BEAN_NAME + ".java");
        if(!beanFile.exists()) {
            beanFile.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + Config.BEAN_PACKAGE + ";");
        bw.newLine();
        bw.write("import java.io.Serializable;");
        bw.newLine();
        bw.write("import lombok.Data;");
        //      bw.write("import javax.persistence.Entity;");
        bw = buildClassComment(bw, tableComment);
        bw.newLine();
//        bw.write("@SuppressWarnings(\"serial\")");
        bw.newLine();
        //      bw.write("@Entity");
        bw.write("@Data");
        bw.newLine();
        bw.write("public class " + BEAN_NAME + " implements Serializable {");
        bw.newLine();
        bw.newLine();
        int size = columns.size();
        for ( int i = 0 ; i < size ; i++ ) {
            bw.write("\t/**" + comments.get(i) + "**/");
            bw.newLine();
            bw.write("\tprivate " + processType(types.get(i)) + " " + processField(columns.get(i)) + ";");
            bw.newLine();
            bw.newLine();
        }
        bw.newLine();
//        // 生成get 和 set方法
//        String tempField = null;
//        String _tempField = null;
//        String tempType = null;
//        for ( int i = 0 ; i < size ; i++ ) {
//            tempType = processType(types.get(i));
//            _tempField = processField(columns.get(i));
//            tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
//            bw.newLine();
//            //          bw.write("\tpublic void set" + tempField + "(" + tempType + " _" + _tempField + "){");
//            bw.write("\tpublic void set" + tempField + "(" + tempType + " " + _tempField + "){");
//            bw.newLine();
//            //          bw.write("\t\tthis." + _tempField + "=_" + _tempField + ";");
//            bw.write("\t\tthis." + _tempField + " = " + _tempField + ";");
//            bw.newLine();
//            bw.write("\t}");
//            bw.newLine();
//            bw.newLine();
//            bw.write("\tpublic " + tempType + " get" + tempField + "(){");
//            bw.newLine();
//            bw.write("\t\treturn this." + _tempField + ";");
//            bw.newLine();
//            bw.write("\t}");
//            bw.newLine();
//        }
//        bw.newLine();
        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
