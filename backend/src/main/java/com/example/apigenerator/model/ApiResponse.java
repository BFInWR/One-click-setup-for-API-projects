package com.example.apigenerator.model;

import lombok.Data;
import java.util.List;

/**
 * API响应模型
 */
@Data
public class ApiResponse {
    private int statusCode;
    private String description;
    private List<ApiResponseProperty> properties;

}