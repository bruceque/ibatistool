package com.kinglong.processor.auto.common;

import com.kinglong.config.Config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenjinlong on 15/6/5.
 */
public class BaseProcessor {


    public static String TABLE_NAME = null;

    public static String BEAN_NAME = null;

    public static String MAPPER_NAME = null;

    public static String MAPPER_EXT_NAME = null;

    public static String BEAN_PARAM_NAME = null;

    public static String BEAN_FULL_NAME = null;

    public static String BEAN_PARAM_LIST_NAME = null;

    public static String BEAN_QUERY_NAME = null;

    public static String BEAN_QUERY_PARAM_NAME = null;


    public static void processTable( String table ) {
        StringBuilder sb = new StringBuilder(table.length());
        String tableNew = table.toLowerCase();
        String[] tables = tableNew.split("_");
        String temp = null;
        for ( int i = 1 ; i < tables.length ; i++ ) {
            temp = tables[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        BEAN_NAME = sb.toString()+"DO";
        MAPPER_NAME = sb.toString() + "Mapper";
        MAPPER_EXT_NAME = sb.toString() + "MapperExt";
        BEAN_PARAM_NAME = processResultMapId(BEAN_NAME);
        BEAN_FULL_NAME = Config.BEAN_PACKAGE+"."+BEAN_NAME;
        BEAN_PARAM_LIST_NAME = BEAN_PARAM_NAME+"List";
        BEAN_QUERY_NAME = sb.toString()+"QueryParam";
        BEAN_QUERY_PARAM_NAME = processResultMapId(BEAN_PARAM_NAME);
    }


    public static String processType( String type ) {
        if (type.contains(Config.TYPE_CHAR)) {
            return "String";
        } else if (type.contains(Config.TYPE_BIGINT)) {
            return "Long";
        } else if (type.contains(Config.TYPE_INT)) {
            return "Integer";
        } else if (type.contains(Config.TYPE_DATE)) {
            return "java.util.Date";
        } else if (type.contains(Config.TYPE_TEXT)) {
            return "String";
        } else if (type.contains(Config.TYPE_TIMESTAMP)) {
            return "java.util.Date";
        } else if (type.contains(Config.TYPE_BIT)) {
            return "Boolean";
        } else if (type.contains(Config.TYPE_DECIMAL)) {
            return "java.math.BigDecimal";
        } else if (type.contains(Config.TYPE_BLOB)) {
            return "byte[]";
        }
        return null;
    }

    public static List<String> getImportType4Python(List<String> types) {
        List<String> importTypes = new ArrayList<String>();
        int i = 0;
        for(String type:types) {
            String sqlAlchemyType = null;
            if ( type.indexOf(Config.TYPE_CHAR) > -1 ) {
                sqlAlchemyType = "String";
            } else if ( type.indexOf(Config.TYPE_BIGINT) > -1 ) {
                sqlAlchemyType = "BigInteger";
            } else if ( type.indexOf(Config.TYPE_INT) > -1 ) {
                sqlAlchemyType = "SmallInteger";
            } else if ( type.indexOf(Config.TYPE_DATE) > -1 ) {
                sqlAlchemyType = "DateTime";
            } else if ( type.indexOf(Config.TYPE_TEXT) > -1 ) {
                sqlAlchemyType = "String";
            } else if ( type.indexOf(Config.TYPE_TIMESTAMP) > -1 ) {
                sqlAlchemyType = "DateTime";
            } else if ( type.indexOf(Config.TYPE_BIT) > -1 ) {
                sqlAlchemyType = "Boolean";
            } else if ( type.indexOf(Config.TYPE_DECIMAL) > -1 ) {
                sqlAlchemyType = "Float";
            } else if ( type.indexOf(Config.TYPE_BLOB) > -1 ) {
                sqlAlchemyType = "LargeBinary";
            }
            if (sqlAlchemyType!=null) {
                importTypes.add(sqlAlchemyType);
            }
        }
        return importTypes;
    }
    public static String processType4Python(String type) {
        String lenStr = getTypeLen(type);
        lenStr = lenStr.isEmpty()?"":String.format("(%s)",lenStr);
        if ( type.indexOf(Config.TYPE_CHAR) > -1 ) {
            return String.format("String%s", lenStr);
        } else if ( type.indexOf(Config.TYPE_BIGINT) > -1 ) {
            return "BigInteger";
        } else if ( type.indexOf(Config.TYPE_INT) > -1 ) {
            return "SmallInteger";
        } else if ( type.indexOf(Config.TYPE_DATE) > -1 ) {
            return "DateTime";
        } else if ( type.indexOf(Config.TYPE_TEXT) > -1 ) {
            return String.format("String%s", lenStr);
        } else if ( type.indexOf(Config.TYPE_TIMESTAMP) > -1 ) {
            return "DateTime";
        } else if ( type.indexOf(Config.TYPE_BIT) > -1 ) {
            return "Boolean";
        } else if ( type.indexOf(Config.TYPE_DECIMAL) > -1 ) {
            return "Float";
        } else if ( type.indexOf(Config.TYPE_BLOB) > -1 ) {
            return "LargeBinary";
        }
        return null;
    }

    public static Pattern pattern = Pattern.compile("\\((\\d+)\\)");
    public static String getTypeLen(String type) {
        Matcher matcher = pattern.matcher(type);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static String processJdbcType( String type) {
        if ( type.indexOf(Config.TYPE_CHAR) > -1 ) {
            return "VARCHAR";
        } else if ( type.indexOf(Config.TYPE_BIGINT) > -1 ) {
            return "Long";
        } else if ( type.indexOf(Config.TYPE_INT) > -1 ) {
            return "INTEGER";
        } else if ( type.indexOf(Config.TYPE_DATE) > -1 ) {
            return "TIMESTAMP";
        } else if ( type.indexOf(Config.TYPE_TEXT) > -1 ) {
            return "VARCHAR";
        } else if ( type.indexOf(Config.TYPE_TIMESTAMP) > -1 ) {
            return "TIMESTAMP";
        } else if ( type.indexOf(Config.TYPE_BIT) > -1 ) {
            return "TINYINT";
        } else if ( type.indexOf(Config.TYPE_DECIMAL) > -1 ) {
            return "DECIMAL";
        }
        return null;
    }


    public static String processField( String field ) {
        StringBuffer sb = new StringBuffer(field.length());
        //field = field.toLowerCase();
        String[] fields = field.split("_");
        String temp = null;
        sb.append(fields[0]);
        for ( int i = 1 ; i < fields.length ; i++ ) {
            temp = fields[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        return sb.toString();
    }


    /**
     *  将实体类名首字母改为小写
     *
     * @param beanName
     * @return
     */
    public static String processResultMapId( String beanName ) {
        return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
    }


    /**
     *  构建类上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    public static BufferedWriter buildClassComment( BufferedWriter bw, String text ) throws IOException {
        bw.newLine();
        bw.newLine();
        bw.write("/**");
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" * " + text);
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" **/");
        return bw;
    }


    /**
     *  构建方法上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    public static BufferedWriter buildMethodComment( BufferedWriter bw, String text ) throws IOException {
        bw.newLine();
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * ");
        bw.newLine();
        bw.write("\t * " + text);
        bw.newLine();
        bw.write("\t * ");
        bw.newLine();
        bw.write("\t **/");
        return bw;
    }

    public static BufferedWriter buildMethodComment4SqlAlchemy( BufferedWriter bw, String text ) throws IOException {
        return buildMethodComment4SqlAlchemy(bw,text,"\t");
    }

    public static BufferedWriter buildMethodComment4SqlAlchemy( BufferedWriter bw, String text,String indent ) throws IOException {
        bw.newLine();
        bw.write(indent+"\"\"\"");
        bw.newLine();
        bw.write(indent + text);
        bw.newLine();
        bw.write(indent+"\"\"\"");
        return bw;
    }
}
