package com.example.apigenerator.controller;

import com.example.apigenerator.model.Combo;
import com.example.apigenerator.model.TechnologyStack;
import com.example.apigenerator.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 技术栈控制器
 */
@RestController
@RequestMapping("/api/technology")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    /**
     * 获取所有技术栈
     */
    @GetMapping("/stacks")
    public List<TechnologyStack> getAllTechnologyStacks() {
        return technologyService.getAllTechnologyStacks();
    }

    /**
     * 获取预设套餐
     */
    @GetMapping("/combos")
    public List<Combo> getPresetCombos() {
        return technologyService.getPresetCombos();
    }

    /**
     * 验证技术栈兼容性
     */
    @PostMapping("/validate")
    public boolean validateCompatibility(@RequestBody List<TechnologyStack> stacks) {
        return technologyService.validateCompatibility(stacks);
    }

}