package com.example.apigenerator.model;

import lombok.Data;

/**
 * 技术栈模型
 */
@Data
public class TechnologyStack {
    private String id;
    private String name;
    private String version;
    private String description;
    private String category; // backend, database, frontend, server

}