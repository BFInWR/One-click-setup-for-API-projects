package com.example.apigenerator.model;

import lombok.Data;

/**
 * API响应属性模型
 */
@Data
public class ApiResponseProperty {
    private String name;
    private String type;
    private String description;

}