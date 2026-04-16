package com.example.apigenerator.controller;

import com.example.apigenerator.model.ApiDoc;
import com.example.apigenerator.model.TechnologyStack;
import com.example.apigenerator.service.MarkdownGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Markdown文档生成控制器
 */
@RestController
@RequestMapping("/api/markdown")
public class MarkdownGeneratorController {

    @Autowired
    private MarkdownGeneratorService markdownGeneratorService;

    /**
     * 生成Markdown文档
     */
    @PostMapping("/generate")
    public String generateMarkdown(@RequestParam("techStacks") List<TechnologyStack> technologyStacks, @RequestParam("apiDoc") ApiDoc apiDoc) {
        return markdownGeneratorService.generateMarkdown(technologyStacks, apiDoc);
    }

    /**
     * 生成Markdown文档（使用请求体）
     */
    @PostMapping("/generate/json")
    public String generateMarkdownJson(@RequestBody MarkdownRequest request) {
        return markdownGeneratorService.generateMarkdown(request.getTechnologyStacks(), request.getApiDoc());
    }

    /**
     * Markdown生成请求模型
     */
    public static class MarkdownRequest {
        private List<TechnologyStack> technologyStacks;
        private ApiDoc apiDoc;

        public List<TechnologyStack> getTechnologyStacks() {
            return technologyStacks;
        }

        public void setTechnologyStacks(List<TechnologyStack> technologyStacks) {
            this.technologyStacks = technologyStacks;
        }

        public ApiDoc getApiDoc() {
            return apiDoc;
        }

        public void setApiDoc(ApiDoc apiDoc) {
            this.apiDoc = apiDoc;
        }
    }

}