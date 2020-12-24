package com.yanghui.jackson;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JacksonTestYh {

//    private ObjectMapper objectMapper = new ObjectMapper();

//    public ObjectMapper getObjectMapper() {
//        if (objectMapper == null) {
//            synchronized (JacksonTestYh.class) {
//                if (objectMapper == null) {
//                    objectMapper = new ObjectMapper();
//                }
//            }
//        }
//        return objectMapper;

    private MyObjectMapper objectMapper = new MyObjectMapper();

    public ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            synchronized (JacksonTestYh.class) {
                if (objectMapper == null) {
                    objectMapper = new MyObjectMapper();
                }
            }
        }
        return objectMapper;

    }

    //自定义解析配置
    class MyObjectMapper extends ObjectMapper {
        public MyObjectMapper() {
            //设置是否美化 即换行 /n 这种
         //   this.configure(SerializationFeature.INDENT_OUTPUT, true);
            this.configure(SerializationFeature.INDENT_OUTPUT, false);
        }
    }



}
