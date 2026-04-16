package com.example.apigenerator.model;

import lombok.Data;

/**
 * API参数模型
 */
@Data
public class ApiParameter {
    private String name;
    private String type;
    private String in; // query, path, body, header
    private boolean required;
    private String description;

}