package com.example.apigenerator.model;

import lombok.Data;
import java.util.List;

/**
 * API模型
 */
@Data
public class Api {
    private String id;
    private String path;
    private String method;
    private String name;
    private String description;
    private List<ApiParameter> parameters;
    private ApiResponse response;

}