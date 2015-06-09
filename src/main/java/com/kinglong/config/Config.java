package com.kinglong.config;

import java.sql.Connection;

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





    public final static String TABLE = "db_appoint";

    public final static String
            BASE_PATH = "/Users/chenjinlong/IdeaProjects/tqmall/shop/dal/src/main/java/com/tqmall/shop/";

    public final static String BASE_PACKAGE = "com.tqmall.shop";

    public final static String  MODULE_NAME = "qa_autoparts"; // 对应模块名称（根据自己模块做相应调整!!!务必修改^_^）

    public final static String BEAN_PATH = BASE_PATH+MODULE_NAME+"/entity";

    public final static String MAPPER_PATH = BASE_PATH+MODULE_NAME+"/mapper";

    public final static String XML_PATH = BASE_PATH+MODULE_NAME+"/xml";

    public final static String BEAN_PARAM_PATH = BASE_PATH+MODULE_NAME+"/param";

    public final static String BEAN_PACKAGE = BASE_PACKAGE+"." + MODULE_NAME + ".entity";

    public final static String BEAN_PARAM_PACKAGE = BASE_PACKAGE+"."+MODULE_NAME+".param";

    public final static String MAPPER_PACKAGE = BASE_PACKAGE+"." + MODULE_NAME + ".mapper";

    public final static String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public final static String USER = "shop_T_manager";

    public final static String PASSWORD = "AI*k#$xv!Kmw";

    public final static String URL = "jdbc:mysql://121.199.28.244:3306/" + MODULE_NAME + "?characterEncoding=UTF-8" ;

    public static final Integer RTN_TYPE = 0;
    //+ "&amp;zeroDateTimeBehavior=convertToNull";


//    public final static String URL = "jdbc:mysql://192.168.1.253:3306/" + MODULE_NAME + "?characterEncoding=utf8";
}
