# AI接口项目生成系统

## 项目简介

AI接口项目生成系统是一个用于辅助AI生成接口项目的工具，它允许用户选择技术栈套餐、输入接口文档，并生成AI可读懂的Markdown文档，从而快速搭建接口项目。

## 核心功能

- **技术栈套餐选择**：提供多种预设技术栈组合，如Spring Boot + MySQL + Vue等
- **接口文档输入**：支持文本输入、文件上传和数据库表结构输入
- **Markdown文档生成**：根据选择的技术栈和输入的接口文档生成AI可读懂的Markdown文档
- **项目生成指导**：提供详细的项目搭建步骤和部署指南

## 技术架构

- **前端**：Vue 3.x + Vite + Element Plus
- **后端**：Spring Boot 2.7.18 + Spring Web + Springdoc OpenAPI
- **数据处理**：Jackson + Hutool
- **文档生成**：自定义Markdown生成器

## 快速开始

### 前端启动

1. 进入前端目录：
   ```bash
   cd frontend
   ```

2. 安装依赖：
   ```bash
   npm install
   ```

3. 启动开发服务器：
   ```bash
   npm run dev
   ```

4. 访问前端应用：
   打开浏览器，访问 http://localhost:5173

### 后端启动

1. 进入后端目录：
   ```bash
   cd backend
   ```

2. 构建项目：
   ```bash
   mvn clean package
   ```

3. 启动应用：
   ```bash
   java -jar target/api-generator.jar
   ```

4. 访问后端API：
   后端服务默认运行在 http://localhost:8080/api-generator

## 操作流程

1. **技术栈选择**：选择一个预设套餐或自定义技术栈组合
2. **接口文档输入**：输入接口文档或上传文档文件
3. **文档生成**：系统自动生成Markdown格式的项目构建文档
4. **项目指导**：获取详细的项目搭建步骤和部署指南

## 项目结构

### 前端项目结构

```
frontend/
├── src/
│   ├── components/              # 组件目录
│   │   ├── TechnologyStackSelector.vue    # 技术栈选择组件
│   │   ├── ApiDocInput.vue               # 接口文档输入组件
│   │   ├── MarkdownGenerator.vue          # Markdown文档生成组件
│   │   └── ProjectGuide.vue               # 项目生成指导组件
│   ├── App.vue                 # 应用主组件
│   └── main.js                 # 应用入口
├── package.json                # npm配置文件
└── vite.config.js              # Vite配置文件
```

### 后端项目结构

```
backend/
├── src/main/java/com/example/apigenerator/
│   ├── controller/             # 控制器层
│   │   ├── TechnologyController.java        # 技术栈控制器
│   │   ├── ApiDocController.java            # 接口文档控制器
│   │   ├── MarkdownGeneratorController.java # Markdown生成控制器
│   │   └── ProjectGeneratorController.java   # 项目生成控制器
│   ├── model/                  # 模型层
│   │   ├── TechnologyStack.java             # 技术栈模型
│   │   ├── Combo.java                       # 套餐模型
│   │   ├── ApiDoc.java                      # 接口文档模型
│   │   ├── Api.java                         # API模型
│   │   ├── ApiParameter.java                # API参数模型
│   │   ├── ApiResponse.java                 # API响应模型
│   │   ├── ApiResponseProperty.java         # API响应属性模型
│   │   ├── DatabaseTable.java               # 数据库表模型
│   │   └── DatabaseColumn.java              # 数据库列模型
│   ├── service/                 # 服务层
│   │   ├── TechnologyService.java           # 技术栈服务
│   │   ├── ApiDocService.java               # 接口文档服务
│   │   ├── MarkdownGeneratorService.java    # Markdown生成服务
│   │   └── ProjectGeneratorService.java     # 项目生成服务
│   └── ApiGeneratorApplication.java         # 应用启动类
├── src/main/resources/
│   └── application.yml        # 应用配置文件
└── pom.xml                    # Maven配置文件
```

## API文档

### 技术栈相关API

- **GET /api/technology/stacks**：获取所有技术栈
- **GET /api/technology/combos**：获取预设套餐
- **POST /api/technology/validate**：验证技术栈兼容性

### 接口文档相关API

- **POST /api/api-doc/parse/text**：解析文本格式的接口文档
- **POST /api/api-doc/parse/swagger**：解析Swagger JSON格式的接口文档
- **POST /api/api-doc/parse/postman**：解析POSTMAN集合格式的接口文档
- **POST /api/api-doc/parse/database**：解析数据库表结构
- **POST /api/api-doc/upload**：上传并解析接口文档文件
- **POST /api/api-doc/validate**：验证接口文档的完整性

### Markdown生成相关API

- **POST /api/markdown/generate**：生成Markdown文档
- **POST /api/markdown/generate/json**：生成Markdown文档（使用请求体）

### 项目生成相关API

- **POST /api/project/generate-guide**：生成项目搭建步骤

## 常见问题及解决方案

### 前端构建失败

**问题**：前端构建时出现编译错误

**解决方案**：
- 检查Vue组件语法是否正确
- 确保所有依赖已正确安装
- 检查Element Plus组件使用是否正确

### 后端启动失败

**问题**：后端启动时出现依赖下载失败

**解决方案**：
- 检查网络连接
- 配置Maven镜像源
- 手动下载依赖到本地仓库

### API调用失败

**问题**：前端调用后端API时出现跨域错误

**解决方案**：
- 后端已配置CORS（跨域资源共享）
- 前端使用代理服务器

### 文档生成失败

**问题**：生成的Markdown文档格式不正确

**解决方案**：
- 检查接口文档格式是否正确
- 确保技术栈选择完整

## 扩展指南

### 添加新的技术栈

1. 在 `TechnologyService.java` 中添加新的技术栈定义
2. 在前端 `TechnologyStackSelector.vue` 中添加对应的选项

### 添加新的预设套餐

1. 在 `TechnologyService.java` 中的 `getPresetCombos()` 方法中添加新的套餐定义

### 扩展接口文档解析能力

1. 在 `ApiDocService.java` 中添加新的解析方法
2. 在 `ApiDocController.java` 中添加对应的API端点

### 自定义Markdown模板

1. 修改 `MarkdownGeneratorService.java` 中的文档生成逻辑
2. 调整Markdown文档的结构和格式

## 总结

AI接口项目生成系统为用户提供了一个便捷的工具，帮助他们快速选择技术栈、输入接口文档，并生成AI可读懂的Markdown文档。通过这个系统，用户可以大大减少接口项目的搭建时间，提高开发效率。

系统具有以下特点：
- 友好的用户界面，操作流程清晰
- 支持多种技术栈组合和预设套餐
- 灵活的接口文档输入方式
- 高质量的Markdown文档生成
- 详细的项目搭建指导

未来，系统可以进一步扩展，支持更多技术栈、更复杂的接口文档解析，以及直接生成项目代码的能力。
