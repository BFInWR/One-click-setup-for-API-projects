此次合并新增了完整的API生成系统，包括后端Spring Boot项目和前端Vue 3项目，实现了技术栈选择、接口文档解析、Markdown文档生成和项目搭建指导等功能。系统采用模块化设计，提供了友好的用户界面和完整的后端API支持。
| 文件 | 变更 |
|------|---------|
| backend/pom.xml | - 新增Maven项目配置文件，定义了Spring Boot 2.7.18依赖和相关库 |
| backend/src/main/java/com/example/apigenerator/ApiGeneratorApplication.java | - 新增Spring Boot应用启动类 |
| backend/src/main/java/com/example/apigenerator/controller/ApiDocController.java | - 新增接口文档控制器，提供文本、Swagger、Postman格式文档解析和数据库表结构解析功能 |
| backend/src/main/java/com/example/apigenerator/controller/MarkdownGeneratorController.java | - 新增Markdown文档生成控制器，支持根据技术栈和接口文档生成Markdown文档 |
| backend/src/main/java/com/example/apigenerator/controller/ProjectGeneratorController.java | - 新增项目生成指导控制器，根据技术栈生成项目搭建步骤 |
| backend/src/main/java/com/example/apigenerator/controller/TechnologyController.java | - 新增技术栈控制器，提供技术栈列表、预设套餐和兼容性验证功能 |
| backend/src/main/java/com/example/apigenerator/model/Api.java | - 新增API模型，包含路径、方法、参数和响应等属性 |
| backend/src/main/java/com/example/apigenerator/model/ApiDoc.java | - 新增接口文档模型，包含API列表和数据库表结构 |
| backend/src/main/java/com/example/apigenerator/model/ApiParameter.java | - 新增API参数模型，包含名称、类型、位置、是否必填等属性 |
| backend/src/main/java/com/example/apigenerator/model/ApiResponse.java | - 新增API响应模型，包含状态码、描述和响应属性 |
| backend/src/main/java/com/example/apigenerator/model/ApiResponseProperty.java | - 新增API响应属性模型，包含名称、类型和描述 |
| backend/src/main/java/com/example/apigenerator/model/Combo.java | - 新增技术栈套餐模型，包含套餐名称、描述和技术栈列表 |
| backend/src/main/java/com/example/apigenerator/model/DatabaseColumn.java | - 新增数据库列模型，包含名称、类型、长度、是否可空、是否主键等属性 |
| backend/src/main/java/com/example/apigenerator/model/DatabaseTable.java | - 新增数据库表模型，包含表名、描述和列列表 |
| backend/src/main/java/com/example/apigenerator/model/TechnologyStack.java | - 新增技术栈模型，包含名称、版本、描述和类别 |
| backend/src/main/java/com/example/apigenerator/service/ApiDocService.java | - 新增接口文档服务，实现文本、Swagger、Postman格式文档解析和数据库表结构解析 |
| backend/src/main/java/com/example/apigenerator/service/MarkdownGeneratorService.java | - 新增Markdown文档生成服务，根据技术栈和接口文档生成结构化Markdown文档 |
| backend/src/main/java/com/example/apigenerator/service/ProjectGeneratorService.java | - 新增项目生成指导服务，根据技术栈生成详细的项目搭建步骤 |
| backend/src/main/java/com/example/apigenerator/service/TechnologyService.java | - 新增技术栈服务，提供技术栈列表、预设套餐和兼容性验证功能 |
| backend/src/main/resources/application.yml | - 新增Spring Boot应用配置文件 |
| frontend/.gitignore | - 新增前端项目Git忽略文件 |
| frontend/package.json | - 新增前端项目依赖配置，包含Vue 3、Element Plus、Axios等依赖 |
| frontend/src/App.vue | - 新增前端主应用，实现四步式流程：技术栈选择、接口文档输入、文档生成、项目指导 |
| frontend/src/components/ApiDocInput.vue | - 新增接口文档输入组件，支持文本、Swagger、Postman格式文档输入 |
| frontend/src/components/MarkdownGenerator.vue | - 新增Markdown文档生成组件，展示生成的Markdown文档 |
| frontend/src/components/ProjectGuide.vue | - 新增项目指导组件，展示项目搭建步骤 |
| frontend/src/components/TechnologyStackSelector.vue | - 新增技术栈选择组件，支持预设套餐和自定义选择 |
| frontend/src/main.js | - 新增前端应用入口文件 |
| frontend/src/style.css | - 新增前端全局样式文件 |
| frontend/vite.config.js | - 新增Vite构建配置文件