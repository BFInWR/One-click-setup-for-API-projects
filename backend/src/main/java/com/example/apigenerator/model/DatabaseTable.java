package com.example.apigenerator.model;

import lombok.Data;
import java.util.List;

/**
 * 数据库表模型
 */
@Data
public class DatabaseTable {
    private String name;
    private String description;
    private List<DatabaseColumn> columns;

}