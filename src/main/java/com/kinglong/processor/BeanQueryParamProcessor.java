package com.kinglong.processor;

import com.kinglong.config.Config;
import com.kinglong.processor.auto.common.BaseProcessor;

import java.io.*;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public class BeanQueryParamProcessor extends BaseProcessor {
    public static void buildBeanQueryParam(List<String> columns, List<String> types, List<String> comments, String tableComment)
            throws IOException {
        File folder = new File(Config.BEAN_PARAM_PATH);
        if ( !folder.exists() ) {
            folder.mkdirs();
        }

        File beanFile = new File(Config.BEAN_PARAM_PATH, BEAN_QUERY_NAME + ".java");
        if(!beanFile.exists()) {
            beanFile.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + Config.BEAN_PARAM_PACKAGE + ";");
        bw.newLine();
        bw.write("import lombok.Data;");
        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        bw.newLine();
        bw.write("@Data");
        bw.newLine();
        bw.write("public class " + BEAN_QUERY_NAME + " extends BaseQueryParam {");
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
        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
