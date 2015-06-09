package com.kinglong.processor.sql;

import com.kinglong.config.Config;
import com.kinglong.processor.BaseProcessor;

/**
 * Created by chenjinlong on 15/6/9.
 */
public abstract class AbstractSqlProcessor extends BaseProcessor implements BaseSqlProcessor {
    String tempField = null;

    protected static String generatorRetStr() {
        if (Config.RTN_TYPE==1) {
            return  "resultType=\"" +Config.BEAN_PACKAGE+"."+BEAN_NAME + "\"";
        } else {
            return "resultMap=\"BaseResultMap\"";
        }

    }

    protected static String generatorJdbcType(String type) {
        String str = BaseProcessor.processJdbcType(type);
        if (str==null) {
            return "";
        }
        return ",jdbcType="+str+" ";
    }
}
