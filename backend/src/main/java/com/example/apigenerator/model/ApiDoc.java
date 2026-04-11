package com.example.apigenerator.model;

import lombok.Data;
import java.util.List;

/**
 * 接口文档模型
 */
@Data
public class ApiDoc {
    private String id;
    private String name;
    private String description;
    private List<Api> apis;
    private List<DatabaseTable> databaseTables;

}