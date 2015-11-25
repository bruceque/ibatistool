package com.kinglong.processor;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by chenjinlong on 15/9/7.
 */
public class BaseProcessorTest {

    @Test
    public void testGetTypeLen() throws Exception {
        assertTrue(BaseProcessor.getTypeLen("abc(20)").equals("20"));
    }
}