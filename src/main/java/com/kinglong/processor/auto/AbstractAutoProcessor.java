package com.kinglong.processor.auto;

import com.kinglong.config.Config;
import com.kinglong.processor.auto.common.BaseProcessor;

/**
 * Created by chenjinlong on 15/6/9.
 */
public abstract class AbstractAutoProcessor extends BaseProcessor implements BaseAutoProcessor {
    String tempField = null;
    String tempColumn = null;

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
