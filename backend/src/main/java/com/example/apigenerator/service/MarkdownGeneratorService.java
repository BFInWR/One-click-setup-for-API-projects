package com.example.apigenerator.service;

import com.example.apigenerator.model.ApiDoc;
import com.example.apigenerator.model.TechnologyStack;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Markdown文档生成服务
 */
@Service
public class MarkdownGeneratorService {

    /**
     * 生成AI可读懂的Markdown文档
     */
    public String generateMarkdown(List<TechnologyStack> technologyStacks, ApiDoc apiDoc) {
        StringBuilder markdown = new StringBuilder();

        // 文档头部
        markdown.append("# 接口项目构建流程文档\n\n");
        markdown.append("## 1. 概述\n\n");
        markdown.append("本流程用于快速构建基于所选技术栈的接口项目。只需按照以下步骤即可生成完整可落地的项目。\n\n");

        // 技术栈选型
        markdown.append("### 1.1 技术栈选型\n\n");
        markdown.append("| 技术 | 版本 | 说明 |\n");
        markdown.append("|------|------|------|\n");

        // 按类别分组技术栈
        List<TechnologyStack> backendTechs = technologyStacks.stream()
                .filter(tech -> "backend".equals(tech.getCategory()))
                .collect(Collectors.toList());

        List<TechnologyStack> databaseTechs = technologyStacks.stream()
                .filter(tech -> "database".equals(tech.getCategory()))
                .collect(Collectors.toList());

        List<TechnologyStack> frontendTechs = technologyStacks.stream()
                .filter(tech -> "frontend".equals(tech.getCategory()))
                .collect(Collectors.toList());

        List<TechnologyStack> serverTechs = technologyStacks.stream()
                .filter(tech -> "server".equals(tech.getCategory()))
                .collect(Collectors.toList());

        // 后端框架
        for (TechnologyStack tech : backendTechs) {
            markdown.append(String.format("| **%s** | %s | %s |\n", tech.getName(), tech.getVersion(), tech.getDescription()));
        }

        // 数据库
        for (TechnologyStack tech : databaseTechs) {
            markdown.append(String.format("| **%s** | %s | %s |\n", tech.getName(), tech.getVersion(), tech.getDescription()));
        }

        // 前端框架
        for (TechnologyStack tech : frontendTechs) {
            markdown.append(String.format("| **%s** | %s | %s |\n", tech.getName(), tech.getVersion(), tech.getDescription()));
        }

        // 应用服务器
        for (TechnologyStack tech : serverTechs) {
            markdown.append(String.format("| **%s** | %s | %s |\n", tech.getName(), tech.getVersion(), tech.getDescription()));
        }

        markdown.append("\n");

        // 项目初始化
        markdown.append("## 2. 项目初始化\n\n");
        markdown.append("### 2.1 创建项目\n\n");

        // 后端项目初始化
        if (!backendTechs.isEmpty()) {
            TechnologyStack backendTech = backendTechs.get(0);
            if (backendTech.getName().contains("Spring Boot")) {
                markdown.append("#### 2.1.1 创建 Spring Boot 项目\n\n");
                markdown.append("推荐使用 Spring Initializr 创建项目：\n");
                markdown.append("- 访问 https://start.spring.io/\n");
                markdown.append("- 选择 Maven 项目，JDK 8+\n");
                markdown.append("- 添加依赖：Spring Web、Validation、相应数据库驱动、Lombok\n\n");
            }
        }

        // 前端项目初始化
        if (!frontendTechs.isEmpty()) {
            TechnologyStack frontendTech = frontendTechs.get(0);
            markdown.append("#### 2.1.2 创建前端项目\n\n");
            if (frontendTech.getName().equals("Vue")) {
                markdown.append("使用 Vite 创建 Vue 项目：\n");
                markdown.append("```bash\nnpm create vite@latest frontend -- --template vue\ncd frontend\nnpm install\n```\n\n");
            } else if (frontendTech.getName().equals("React")) {
                markdown.append("使用 Vite 创建 React 项目：\n");
                markdown.append("```bash\nnpm create vite@latest frontend -- --template react\ncd frontend\nnpm install\n```\n\n");
            } else if (frontendTech.getName().equals("Angular")) {
                markdown.append("使用 Angular CLI 创建 Angular 项目：\n");
                markdown.append("```bash\nnpm install -g @angular/cli\nng new frontend\ncd frontend\n```\n\n");
            }
        }

        // 项目结构
        markdown.append("### 2.2 项目目录结构\n\n");
        markdown.append("```\napi-project/\n├── backend/                 # 后端项目\n│   ├── pom.xml              # Maven 配置文件\n│   ├── src/main/java/       # Java 源码目录\n│   │   └── com/example/xxx/  # 包目录\n│   │       ├── controller/   # 控制器层\n│   │       ├── service/      # 业务逻辑层\n│   │       ├── model/        # 模型层\n│   │       ├── mapper/       # 数据访问层\n│   │       ├── config/       # 配置类\n│   │       └── common/       # 公共模块\n│   └── src/main/resources/   # 资源文件目录\n└── frontend/                # 前端项目\n    ├── package.json          # npm 配置文件\n    ├── src/                  # 源代码目录\n    │   ├── components/       # 组件目录\n    │   ├── views/            # 页面目录\n    │   ├── router/           # 路由配置\n    │   └── api/              # API 调用\n    └── public/               # 静态资源目录\n```\n\n");

        // 接口信息
        if (apiDoc != null && apiDoc.getApis() != null && !apiDoc.getApis().isEmpty()) {
            markdown.append("## 3. 接口信息\n\n");
            for (com.example.apigenerator.model.Api api : apiDoc.getApis()) {
                markdown.append(String.format("### 3.1 %s\n\n", api.getName()));
                markdown.append(String.format("**路径：** %s\n\n", api.getPath()));
                markdown.append(String.format("**方法：** %s\n\n", api.getMethod()));
                markdown.append(String.format("**描述：** %s\n\n", api.getDescription()));

                if (api.getParameters() != null && !api.getParameters().isEmpty()) {
                    markdown.append("**参数：**\n\n");
                    markdown.append("| 参数名 | 类型 | 位置 | 必填 | 描述 |\n");
                    markdown.append("|--------|------|------|------|------|\n");
                    for (com.example.apigenerator.model.ApiParameter param : api.getParameters()) {
                        markdown.append(String.format("| %s | %s | %s | %s | %s |\n",
                                param.getName(),
                                param.getType(),
                                param.getIn(),
                                param.isRequired() ? "是" : "否",
                                param.getDescription() != null ? param.getDescription() : ""
                        ));
                    }
                    markdown.append("\n");
                }

                if (api.getResponse() != null) {
                    markdown.append("**响应：**\n\n");
                    markdown.append(String.format("**状态码：** %d\n\n", api.getResponse().getStatusCode()));
                    markdown.append(String.format("**描述：** %s\n\n", api.getResponse().getDescription()));

                    if (api.getResponse().getProperties() != null && !api.getResponse().getProperties().isEmpty()) {
                        markdown.append("**响应属性：**\n\n");
                        markdown.append("| 属性名 | 类型 | 描述 |\n");
                        markdown.append("|--------|------|------|\n");
                        for (com.example.apigenerator.model.ApiResponseProperty prop : api.getResponse().getProperties()) {
                            markdown.append(String.format("| %s | %s | %s |\n",
                                    prop.getName(),
                                    prop.getType(),
                                    prop.getDescription() != null ? prop.getDescription() : ""
                            ));
                        }
                        markdown.append("\n");
                    }
                }
            }
        }

        // 数据库设计
        if (apiDoc != null && apiDoc.getDatabaseTables() != null && !apiDoc.getDatabaseTables().isEmpty()) {
            markdown.append("## 4. 数据库设计\n\n");
            for (com.example.apigenerator.model.DatabaseTable table : apiDoc.getDatabaseTables()) {
                markdown.append(String.format("### 4.1 %s\n\n", table.getName()));
                markdown.append(String.format("**描述：** %s\n\n", table.getDescription() != null ? table.getDescription() : ""));
                markdown.append("**表结构：**\n\n");
                markdown.append("| 字段名 | 类型 | 可空 | 主键 | 默认值 | 描述 |\n");
                markdown.append("|--------|------|------|------|--------|------|\n");
                if (table.getColumns() != null) {
                    for (com.example.apigenerator.model.DatabaseColumn column : table.getColumns()) {
                        markdown.append(String.format("| %s | %s | %s | %s | %s | %s |\n",
                                column.getName(),
                                column.getType(),
                                column.isNullable() ? "是" : "否",
                                column.isPrimaryKey() ? "是" : "否",
                                column.getDefaultValue() != null ? column.getDefaultValue() : "",
                                column.getDescription() != null ? column.getDescription() : ""
                        ));
                    }
                }
                markdown.append("\n");
            }
        }

        // 项目构建和部署
        markdown.append("## 5. 项目构建和部署\n\n");
        markdown.append("### 5.1 后端构建\n\n");
        markdown.append("```bash\ncd backend\nmvn clean package\n```\n\n");

        markdown.append("### 5.2 前端构建\n\n");
        markdown.append("```bash\ncd frontend\nnpm run build\n```\n\n");

        markdown.append("### 5.3 部署\n\n");
        markdown.append("1. 将后端构建产物（target/*.jar）部署到服务器\n");
        markdown.append("2. 将前端构建产物（dist目录）部署到Web服务器\n");
        markdown.append("3. 配置数据库连接\n");
        markdown.append("4. 启动应用\n\n");

        // 注意事项
        markdown.append("## 6. 注意事项\n\n");
        markdown.append("1. 确保技术栈版本兼容性\n");
        markdown.append("2. 按照接口文档实现API\n");
        markdown.append("3. 遵循数据库设计规范\n");
        markdown.append("4. 实现适当的错误处理和日志记录\n");
        markdown.append("5. 进行充分的测试\n\n");

        return markdown.toString();
    }

}