package com.kinglong.processor.auto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenjinlong on 15/6/9.
 */
public interface BaseAutoProcessor {
    void buildSQL( BufferedWriter bw,
                          List<String> columns,
                          List<String> types,
                          List<String> comments ) throws IOException;

    void buildMethod(BufferedWriter bw) throws IOException;
}
