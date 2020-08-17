package com.yanghui.moudle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yanghui.jackson.SerializeUtil;
import lombok.Data;
import org.junit.Test;

import java.util.List;

@Data
public class JsonPropertiesTest {

    // @JsonProperty  因为 ****设计 导致server 过来的参数有变化 （一坨便便）

    @JsonProperty("dataSourceId")
    private String dataSource;

    private String tenantId;

    @JsonProperty("scoureId")
    private String resource;
    /**
     * 1：悬浮 保留单位和小数点 2：渲染
     */
    private int dataProcess;
    private List<String> codeList;


    @Test
    public void test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataSourceId", "1122");
        jsonObject.put("tenantId", "e10");
        JsonPropertiesTest jsonPropertiesTest = JSON.parseObject(JSON.toJSONString(jsonObject), JsonPropertiesTest.class);
        JsonPropertiesTest jsonPropertiesTest1 = SerializeUtil.decodeJson(SerializeUtil.encodeJson(jsonObject), JsonPropertiesTest.class);
        JsonPropertiesTest jsonPropertiesTest2 = SerializeUtil.decodeJson(JSON.toJSONString(jsonObject), JsonPropertiesTest.class);
        System.out.println(jsonPropertiesTest.toString());

        System.out.println(jsonPropertiesTest1.toString());

        System.out.println(jsonPropertiesTest2.toString());
    }

}
