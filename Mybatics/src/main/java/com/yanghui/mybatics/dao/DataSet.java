package com.yanghui.mybatics.dao;

import lombok.Data;

@Data
public class DataSet {
    private String id;
    private String dataSourceId;
    private String tenantId;
    private String name;
    private String url;
    private String apiKey;
    private String recentTime;
    private String refreshTime;
    private String authInfo;
    private String description;
    private String method;
    private String body;
    private String userName;
    private String password;
//    private String resultField;
    private String tableName;
    private String startTime;
    private String endTime;
    private boolean supportFilter;
    private boolean supportTime;
    private boolean basicAuth;
    private int allNum; //存放redis总共有多少条数据
    private int isDelete;
    private int isConnect;
    private String reqTime;
    private String createdTime;
}
