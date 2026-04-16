package com.example.apigenerator.controller;

import com.example.apigenerator.model.TechnologyStack;
import com.example.apigenerator.service.ProjectGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 项目生成指导控制器
 */
@RestController
@RequestMapping("/api/project")
public class ProjectGeneratorController {

    @Autowired
    private ProjectGeneratorService projectGeneratorService;

    /**
     * 生成项目搭建步骤
     */
    @PostMapping("/generate-guide")
    public String generateProjectGuide(@RequestBody List<TechnologyStack> technologyStacks) {
        return projectGeneratorService.generateProjectGuide(technologyStacks);
    }

}