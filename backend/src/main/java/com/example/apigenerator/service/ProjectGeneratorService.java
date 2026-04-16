package com.example.apigenerator.service;

import com.example.apigenerator.model.TechnologyStack;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目生成指导服务
 */
@Service
public class ProjectGeneratorService {

    /**
     * 生成项目搭建步骤
     */
    public String generateProjectGuide(List<TechnologyStack> technologyStacks) {
        StringBuilder guide = new StringBuilder();

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

        // 项目搭建步骤
        guide.append("# 项目搭建指导\n\n");

        // 1. 环境准备
        guide.append("## 1. 环境准备\n\n");
        guide.append("### 1.1 安装必要的工具\n\n");
        guide.append("- JDK 8+（推荐 JDK 11）\n");
        guide.append("- Maven 3.6+\n");
        guide.append("- Node.js 16+\n");
        guide.append("- npm 7+\n");
        if (!databaseTechs.isEmpty()) {
            TechnologyStack databaseTech = databaseTechs.get(0);
            guide.append(String.format("- %s %s\n", databaseTech.getName(), databaseTech.getVersion()));
        }
        guide.append("\n");

        // 2. 后端项目搭建
        if (!backendTechs.isEmpty()) {
            guide.append("## 2. 后端项目搭建\n\n");
            TechnologyStack backendTech = backendTechs.get(0);
            
            if (backendTech.getName().contains("Spring Boot")) {
                guide.append("### 2.1 创建 Spring Boot 项目\n\n");
                guide.append("使用 Spring Initializr 创建项目：\n");
                guide.append("1. 访问 https://start.spring.io/\n");
                guide.append("2. 选择以下选项：\n");
                guide.append("   - Project: Maven Project\n");
                guide.append("   - Language: Java\n");
                guide.append(String.format("   - Spring Boot: %s\n", backendTech.getVersion()));
                guide.append("   - Packaging: Jar\n");
                guide.append("   - Java: 8\n");
                guide.append("3. 添加依赖：\n");
                guide.append("   - Spring Web\n");
                guide.append("   - Spring Boot DevTools\n");
                guide.append("   - Lombok\n");
                if (!databaseTechs.isEmpty()) {
                    TechnologyStack databaseTech = databaseTechs.get(0);
                    if (databaseTech.getName().equals("MySQL")) {
                        guide.append("   - MySQL Driver\n");
                    } else if (databaseTech.getName().equals("PostgreSQL")) {
                        guide.append("   - PostgreSQL Driver\n");
                    } else if (databaseTech.getName().equals("Oracle")) {
                        guide.append("   - Oracle Driver\n");
                    }
                }
                guide.append("4. 点击 Generate 按钮下载项目\n");
                guide.append("5. 解压下载的项目文件\n\n");

                guide.append("### 2.2 配置后端项目\n\n");
                guide.append("1. 打开项目目录\n");
                guide.append("2. 修改 application.yml 文件，配置数据库连接：\n");
                guide.append("\n");
                guide.append("```yaml\n");
                guide.append("spring:\n");
                guide.append("  datasource:\n");
                if (!databaseTechs.isEmpty()) {
                    TechnologyStack databaseTech = databaseTechs.get(0);
                    if (databaseTech.getName().equals("MySQL")) {
                        guide.append("    url: jdbc:mysql://localhost:3306/your_database?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC\n");
                        guide.append("    username: root\n");
                        guide.append("    password: your_password\n");
                        guide.append("    driver-class-name: com.mysql.cj.jdbc.Driver\n");
                    } else if (databaseTech.getName().equals("PostgreSQL")) {
                        guide.append("    url: jdbc:postgresql://localhost:5432/your_database\n");
                        guide.append("    username: postgres\n");
                        guide.append("    password: your_password\n");
                        guide.append("    driver-class-name: org.postgresql.Driver\n");
                    } else if (databaseTech.getName().equals("Oracle")) {
                        guide.append("    url: jdbc:oracle:thin:@localhost:1521:ORCL\n");
                        guide.append("    username: system\n");
                        guide.append("    password: your_password\n");
                        guide.append("    driver-class-name: oracle.jdbc.OracleDriver\n");
                    }
                }
                guide.append("```\n\n");

                guide.append("### 2.3 启动后端项目\n\n");
                guide.append("```bash\n");
                guide.append("cd backend\n");
                guide.append("mvn spring-boot:run\n");
                guide.append("```\n\n");
            }
        }

        // 3. 前端项目搭建
        if (!frontendTechs.isEmpty()) {
            guide.append("## 3. 前端项目搭建\n\n");
            TechnologyStack frontendTech = frontendTechs.get(0);
            
            if (frontendTech.getName().equals("Vue")) {
                guide.append("### 3.1 创建 Vue 项目\n\n");
                guide.append("使用 Vite 创建 Vue 项目：\n");
                guide.append("```bash\n");
                guide.append("npm create vite@latest frontend -- --template vue\n");
                guide.append("cd frontend\n");
                guide.append("npm install\n");
                guide.append("```\n\n");

                guide.append("### 3.2 安装必要的依赖\n\n");
                guide.append("```bash\n");
                guide.append("npm install axios element-plus\n");
                guide.append("```\n\n");

                guide.append("### 3.3 启动前端项目\n\n");
                guide.append("```bash\n");
                guide.append("npm run dev\n");
                guide.append("```\n\n");
            } else if (frontendTech.getName().equals("React")) {
                guide.append("### 3.1 创建 React 项目\n\n");
                guide.append("使用 Vite 创建 React 项目：\n");
                guide.append("```bash\n");
                guide.append("npm create vite@latest frontend -- --template react\n");
                guide.append("cd frontend\n");
                guide.append("npm install\n");
                guide.append("```\n\n");

                guide.append("### 3.2 安装必要的依赖\n\n");
                guide.append("```bash\n");
                guide.append("npm install axios antd\n");
                guide.append("```\n\n");

                guide.append("### 3.3 启动前端项目\n\n");
                guide.append("```bash\n");
                guide.append("npm run dev\n");
                guide.append("```\n\n");
            } else if (frontendTech.getName().equals("Angular")) {
                guide.append("### 3.1 创建 Angular 项目\n\n");
                guide.append("使用 Angular CLI 创建 Angular 项目：\n");
                guide.append("```bash\n");
                guide.append("npm install -g @angular/cli\n");
                guide.append("ng new frontend\n");
                guide.append("cd frontend\n");
                guide.append("```\n\n");

                guide.append("### 3.2 安装必要的依赖\n\n");
                guide.append("```bash\n");
                guide.append("npm install axios\n");
                guide.append("```\n\n");

                guide.append("### 3.3 启动前端项目\n\n");
                guide.append("```bash\n");
                guide.append("ng serve\n");
                guide.append("```\n\n");
            }
        }

        // 4. 数据库初始化
        if (!databaseTechs.isEmpty()) {
            guide.append("## 4. 数据库初始化\n\n");
            TechnologyStack databaseTech = databaseTechs.get(0);
            
            if (databaseTech.getName().equals("MySQL")) {
                guide.append("### 4.1 创建数据库\n\n");
                guide.append("```sql\n");
                guide.append("CREATE DATABASE IF NOT EXISTS your_database DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;\n");
                guide.append("USE your_database;\n");
                guide.append("```\n\n");
            } else if (databaseTech.getName().equals("PostgreSQL")) {
                guide.append("### 4.1 创建数据库\n\n");
                guide.append("```sql\n");
                guide.append("CREATE DATABASE your_database;\n");
                guide.append("\c your_database;\n");
                guide.append("```\n\n");
            } else if (databaseTech.getName().equals("Oracle")) {
                guide.append("### 4.1 创建表空间和用户\n\n");
                guide.append("```sql\n");
                guide.append("CREATE TABLESPACE your_tablespace DATAFILE 'your_tablespace.dbf' SIZE 100M AUTOEXTEND ON NEXT 10M;\n");
                guide.append("CREATE USER your_user IDENTIFIED BY your_password DEFAULT TABLESPACE your_tablespace;\n");
                guide.append("GRANT CONNECT, RESOURCE TO your_user;\n");
                guide.append("```\n\n");
            }
        }

        // 5. 项目集成
        guide.append("## 5. 项目集成\n\n");
        guide.append("### 5.1 前端调用后端 API\n\n");
        guide.append("在前端项目中创建 API 调用文件：\n");
        guide.append("\n");
        guide.append("```javascript\n");
        guide.append("// src/api/index.js\n");
        guide.append("import axios from 'axios';\n\n");
        guide.append("const api = axios.create({\n");
        guide.append("  baseURL: 'http://localhost:8080',\n");
        guide.append("  timeout: 10000,\n");
        guide.append("});\n\n");
        guide.append("export default api;\n");
        guide.append("```\n\n");

        // 6. 部署指南
        guide.append("## 6. 部署指南\n\n");
        guide.append("### 6.1 后端部署\n\n");
        guide.append("1. 构建后端项目：\n");
        guide.append("```bash\n");
        guide.append("cd backend\n");
        guide.append("mvn clean package\n");
        guide.append("```\n\n");
        guide.append("2. 部署 JAR 文件：\n");
        guide.append("```bash\n");
        guide.append("java -jar target/your-project.jar\n");
        guide.append("```\n\n");

        guide.append("### 6.2 前端部署\n\n");
        guide.append("1. 构建前端项目：\n");
        guide.append("```bash\n");
        guide.append("cd frontend\n");
        guide.append("npm run build\n");
        guide.append("```\n\n");
        guide.append("2. 将 dist 目录部署到 Web 服务器（如 Nginx、Apache 等）\n\n");

        // 7. 注意事项
        guide.append("## 7. 注意事项\n\n");
        guide.append("1. 确保技术栈版本兼容性\n");
        guide.append("2. 配置正确的数据库连接信息\n");
        guide.append("3. 实现适当的错误处理和日志记录\n");
        guide.append("4. 进行充分的测试\n");
        guide.append("5. 考虑生产环境的安全性\n\n");

        return guide.toString();
    }

}