package com.example.apigenerator.model;

import lombok.Data;
import java.util.List;

/**
 * 技术栈套餐模型
 */
@Data
public class Combo {
    private String id;
    private String name;
    private String description;
    private List<TechnologyStack> technologyStacks;

}