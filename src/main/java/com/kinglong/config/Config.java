package com.kinglong.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chenjinlong on 15/6/5.
 */
public class Config {
    public final static String TYPE_CHAR = "char";

    public final static String TYPE_DATE = "date";

    public final static String TYPE_TIMESTAMP = "timestamp";

    public final static String TYPE_INT = "int";

    public final static String TYPE_BIGINT = "bigint";

    public final static String TYPE_TEXT = "text";

    public final static String TYPE_BIT = "bit";

    public final static String TYPE_DECIMAL = "decimal";

    public final static String TYPE_BLOB = "blob";

    public static String TABLE;

    public static String BASE_PATH;

    public static String BASE_PACKAGE;

    public static String  MODULE_NAME = "auto"; // 对应模块名称（根据自己模块做相应调整!!!务必修改^_^）

    public static Integer RTN_TYPE;

    public static String DB_PROPERTIES;

    public static String IBATISTOOL_PROPERTIES;

    public static String APPLICATION_PROPERTIES = "application.properties";


    static {

        Properties appProperties = new Properties();
        InputStream appIn = ClassLoader.getSystemResourceAsStream(APPLICATION_PROPERTIES);
        try {
            appProperties.load(appIn);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        IBATISTOOL_PROPERTIES = appProperties.getProperty("ibatistool.properties");
        DB_PROPERTIES = appProperties.getProperty("jdbc.properties");

        Properties itProperties = new Properties();
        InputStream itIn = ClassLoader.getSystemResourceAsStream(IBATISTOOL_PROPERTIES);

        try {
            itProperties.load(itIn);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        //普通参数的初始化
        RTN_TYPE = Integer.parseInt(itProperties.getProperty("xml_ret_type"));
        BASE_PACKAGE = itProperties.getProperty("base_package");
        BASE_PATH = itProperties.getProperty("base_path");
        TABLE = itProperties.getProperty("table");
    }

    public static String BEAN_PATH = BASE_PATH+MODULE_NAME+"/entity";

    public static String MAPPER_PATH = BASE_PATH+MODULE_NAME+"/mapper";

    public static String XML_PATH = BASE_PATH+MODULE_NAME+"/xml";

    public static String BEAN_PARAM_PATH = BASE_PATH+MODULE_NAME+"/param";

    public static String BEAN_PACKAGE = BASE_PACKAGE+"." + MODULE_NAME + ".entity";

    public static String BEAN_PARAM_PACKAGE = BASE_PACKAGE+"."+MODULE_NAME+".param";

    public static String MAPPER_PACKAGE = BASE_PACKAGE+"." + MODULE_NAME + ".mapper";

}
