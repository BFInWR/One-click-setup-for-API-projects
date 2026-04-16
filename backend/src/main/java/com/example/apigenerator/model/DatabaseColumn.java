package com.example.apigenerator.model;

import lombok.Data;

/**
 * 数据库列模型
 */
@Data
public class DatabaseColumn {
    private String name;
    private String type;
    private int length;
    private boolean nullable;
    private boolean primaryKey;
    private String defaultValue;
    private String description;

}