package com.example.apigenerator.controller;

import com.example.apigenerator.model.ApiDoc;
import com.example.apigenerator.model.DatabaseTable;
import com.example.apigenerator.service.ApiDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

/**
 * 接口文档控制器
 */
@RestController
@RequestMapping("/api/api-doc")
public class ApiDocController {

    @Autowired
    private ApiDocService apiDocService;

    /**
     * 解析文本格式的接口文档
     */
    @PostMapping("/parse/text")
    public ApiDoc parseTextApiDoc(@RequestBody String text) {
        return apiDocService.parseTextApiDoc(text);
    }

    /**
     * 解析Swagger JSON格式的接口文档
     */
    @PostMapping("/parse/swagger")
    public ApiDoc parseSwaggerJson(@RequestBody String json) {
        return apiDocService.parseSwaggerJson(json);
    }

    /**
     * 解析POSTMAN集合格式的接口文档
     */
    @PostMapping("/parse/postman")
    public ApiDoc parsePostmanCollection(@RequestBody String json) {
        return apiDocService.parsePostmanCollection(json);
    }

    /**
     * 解析数据库表结构
     */
    @PostMapping("/parse/database")
    public List<DatabaseTable> parseDatabaseSchema(@RequestBody String schema) {
        return apiDocService.parseDatabaseSchema(schema);
    }

    /**
     * 上传并解析接口文档文件
     */
    @PostMapping("/upload")
    public ApiDoc uploadApiDoc(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) throws IOException {
        String content = new String(file.getBytes());
        switch (type) {
            case "text":
                return apiDocService.parseTextApiDoc(content);
            case "swagger":
                return apiDocService.parseSwaggerJson(content);
            case "postman":
                return apiDocService.parsePostmanCollection(content);
            default:
                throw new IllegalArgumentException("Invalid file type");
        }
    }

    /**
     * 验证接口文档的完整性
     */
    @PostMapping("/validate")
    public boolean validateApiDoc(@RequestBody ApiDoc apiDoc) {
        return apiDocService.validateApiDoc(apiDoc);
    }

}