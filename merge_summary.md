此次合并从 trae/solo-agent-BIxF4v 分支引入了完整的 AI 接口项目生成系统，包括后端 Spring Boot 应用和前端 Vue 3 应用，实现了从技术栈选择到项目生成的全流程功能。系统提供了 API 文档解析、Markdown 生成、项目指导等核心功能，支持多种接口文档格式和技术栈组合。
| 文件 | 变更 |
|------|---------|
| backend/pom.xml | - 新增 Maven 项目配置文件，使用 Spring Boot 2.7.18 作为父项目<br>- 添加了 Spring Web、Validation、Springdoc OpenAPI、Hutool、Apache Commons IO、Thymeleaf 等依赖 |
| backend/src/main/java/com/example/apigenerator/ApiGeneratorApplication.java | - 新增 Spring Boot 应用主类，启动 API 生成器系统 |
| backend/src/main/java/com/example/apigenerator/controller/ApiDocController.java | - 新增 API 文档控制器，提供文本、Swagger、Postman 格式的接口文档解析功能<br>- 支持数据库表结构解析和接口文档验证 |
| backend/src/main/java/com/example/apigenerator/controller/MarkdownGeneratorController.java | - 新增 Markdown 生成控制器，提供接口文档转换为 Markdown 格式的功能 |
| backend/src/main/java/com/example/apigenerator/controller/ProjectGeneratorController.java | - 新增项目生成控制器，提供基于技术栈生成项目的功能 |
| backend/src/main/java/com/example/apigenerator/controller/TechnologyController.java | - 新增技术栈控制器，提供技术栈管理和选择功能 |
| backend/src/main/java/com/example/apigenerator/model/Api.java | - 新增 API 模型类，定义接口结构 |
| backend/src/main/java/com/example/apigenerator/model/ApiDoc.java | - 新增 API 文档模型类，定义接口文档结构 |
| backend/src/main/java/com/example/apigenerator/model/ApiParameter.java | - 新增 API 参数模型类，定义接口参数结构 |
| backend/src/main/java/com/example/apigenerator/model/ApiResponse.java | - 新增 API 响应模型类，定义接口响应结构 |
| backend/src/main/java/com/example/apigenerator/model/ApiResponseProperty.java | - 新增 API 响应属性模型类，定义接口响应属性结构 |
| backend/src/main/java/com/example/apigenerator/model/Combo.java | - 新增技术栈组合模型类，定义技术栈套餐结构 |
| backend/src/main/java/com/example/apigenerator/model/DatabaseColumn.java | - 新增数据库列模型类，定义数据库列结构 |
| backend/src/main/java/com/example/apigenerator/model/DatabaseTable.java | - 新增数据库表模型类，定义数据库表结构 |
| backend/src/main/java/com/example/apigenerator/model/TechnologyStack.java | - 新增技术栈模型类，定义技术栈结构 |
| backend/src/main/java/com/example/apigenerator/service/ApiDocService.java | - 新增 API 文档服务，实现接口文档解析和验证功能 |
| backend/src/main/java/com/example/apigenerator/service/MarkdownGeneratorService.java | - 新增 Markdown 生成服务，实现接口文档转换为 Markdown 格式的功能 |
| backend/src/main/java/com/example/apigenerator/service/ProjectGeneratorService.java | - 新增项目生成服务，实现基于技术栈生成项目的功能 |
| backend/src/main/java/com/example/apigenerator/service/TechnologyService.java | - 新增技术栈服务，实现技术栈管理和选择功能 |
| backend/src/main/resources/application.yml | - 新增 Spring Boot 应用配置文件 |
| frontend/.gitignore | - 新增前端项目 Git 忽略文件 |
| frontend/.vscode/extensions.json | - 新增 VS Code 扩展配置文件 |
| frontend/README.md | - 新增前端项目 README 文件 |
| frontend/index.html | - 新增前端项目 HTML 入口文件 |
| frontend/package.json | - 新增前端项目配置文件，使用 Vue 3 + Vite 构建，添加 Element Plus、Axios、Marked、Highlight.js、Pinia 等依赖 |
| frontend/public/favicon.svg | - 新增前端项目 favicon 图标 |
| frontend/public/icons.svg | - 新增前端项目图标文件 |
| frontend/src/App.vue | - 新增前端应用主组件，实现四步流程：技术栈选择 → 接口文档输入 → 文档生成 → 项目指导 |
| frontend/src/assets/hero.png | - 新增前端项目英雄图片 |
| frontend/src/assets/vite.svg | - 新增前端项目 Vite 图标 |
| frontend/src/assets/vue.svg | - 新增前端项目 Vue 图标 |
| frontend/src/components/ApiDocInput.vue | - 新增 API 文档输入组件，支持多种格式的接口文档输入 |
| frontend/src/components/HelloWorld.vue | - 新增示例组件 |
| frontend/src/components/MarkdownGenerator.vue | - 新增 Markdown 生成组件，实现接口文档转换为 Markdown 格式的功能 |
| frontend/src/components/ProjectGuide.vue | - 新增项目指导组件，提供项目搭建步骤指导 |
| frontend/src/components/TechnologyStackSelector.vue | - 新增技术栈选择组件，支持多种技术栈组合选择 |
| frontend/src/main.js | - 新增前端应用入口文件，初始化 Vue 应用 |
| frontend/src/style.css | - 新增前端项目样式文件 |
| frontend/vite.config.js | - 新增 Vite 配置文件