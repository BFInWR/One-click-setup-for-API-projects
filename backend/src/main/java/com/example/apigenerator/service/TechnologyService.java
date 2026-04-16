package com.example.apigenerator.service;

import com.example.apigenerator.model.Combo;
import com.example.apigenerator.model.TechnologyStack;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 技术栈服务
 */
@Service
public class TechnologyService {

    /**
     * 获取所有技术栈
     */
    public List<TechnologyStack> getAllTechnologyStacks() {
        List<TechnologyStack> stacks = new ArrayList<>();

        // 后端框架
        TechnologyStack springBoot2 = new TechnologyStack();
        springBoot2.setId("spring-boot-2.7.18");
        springBoot2.setName("Spring Boot");
        springBoot2.setVersion("2.7.18");
        springBoot2.setDescription("稳定版本，生态成熟");
        springBoot2.setCategory("backend");
        stacks.add(springBoot2);

        TechnologyStack springBoot3 = new TechnologyStack();
        springBoot3.setId("spring-boot-3.2.x");
        springBoot3.setName("Spring Boot");
        springBoot3.setVersion("3.2.x");
        springBoot3.setDescription("最新版本，支持Java 17+");
        springBoot3.setCategory("backend");
        stacks.add(springBoot3);

        TechnologyStack springCloud = new TechnologyStack();
        springCloud.setId("spring-cloud-2023.0.x");
        springCloud.setName("Spring Cloud");
        springCloud.setVersion("2023.0.x");
        springCloud.setDescription("微服务架构");
        springCloud.setCategory("backend");
        stacks.add(springCloud);

        // 数据库
        TechnologyStack mysql = new TechnologyStack();
        mysql.setId("mysql-8.0");
        mysql.setName("MySQL");
        mysql.setVersion("8.0+");
        mysql.setDescription("关系型数据库，广泛使用");
        mysql.setCategory("database");
        stacks.add(mysql);

        TechnologyStack postgresql = new TechnologyStack();
        postgresql.setId("postgresql-14.0");
        postgresql.setName("PostgreSQL");
        postgresql.setVersion("14.0+");
        postgresql.setDescription("功能丰富的开源数据库");
        postgresql.setCategory("database");
        stacks.add(postgresql);

        TechnologyStack oracle = new TechnologyStack();
        oracle.setId("oracle-19c");
        oracle.setName("Oracle");
        oracle.setVersion("19c+");
        oracle.setDescription("企业级数据库");
        oracle.setCategory("database");
        stacks.add(oracle);

        // 前端框架
        TechnologyStack vue = new TechnologyStack();
        vue.setId("vue-3.x");
        vue.setName("Vue");
        vue.setVersion("3.x");
        vue.setDescription("渐进式JavaScript框架");
        vue.setCategory("frontend");
        stacks.add(vue);

        TechnologyStack react = new TechnologyStack();
        react.setId("react-18.x");
        react.setName("React");
        react.setVersion("18.x");
        react.setDescription("组件化前端库");
        react.setCategory("frontend");
        stacks.add(react);

        TechnologyStack angular = new TechnologyStack();
        angular.setId("angular-16.x");
        angular.setName("Angular");
        angular.setVersion("16.x");
        angular.setDescription("完整的前端框架");
        angular.setCategory("frontend");
        stacks.add(angular);

        // 应用服务器
        TechnologyStack tomcat = new TechnologyStack();
        tomcat.setId("tomcat-9.0");
        tomcat.setName("Tomcat");
        tomcat.setVersion("9.0+");
        tomcat.setDescription("Spring Boot内置");
        tomcat.setCategory("server");
        stacks.add(tomcat);

        TechnologyStack jetty = new TechnologyStack();
        jetty.setId("jetty-11.0");
        jetty.setName("Jetty");
        jetty.setVersion("11.0+");
        jetty.setDescription("轻量级服务器");
        jetty.setCategory("server");
        stacks.add(jetty);

        TechnologyStack undertow = new TechnologyStack();
        undertow.setId("undertow-2.3.x");
        undertow.setName("Undertow");
        undertow.setVersion("2.3.x");
        undertow.setDescription("高性能服务器");
        undertow.setCategory("server");
        stacks.add(undertow);

        return stacks;
    }

    /**
     * 获取预设套餐
     */
    public List<Combo> getPresetCombos() {
        List<Combo> combos = new ArrayList<>();

        // 套餐A
        Combo comboA = new Combo();
        comboA.setId("combo-a");
        comboA.setName("套餐A");
        comboA.setDescription("Spring Boot 2.7.18 + MySQL 8.0 + Vue 3.x + Tomcat 9.0");
        List<TechnologyStack> stacksA = new ArrayList<>();
        stacksA.add(getTechnologyStackById("spring-boot-2.7.18"));
        stacksA.add(getTechnologyStackById("mysql-8.0"));
        stacksA.add(getTechnologyStackById("vue-3.x"));
        stacksA.add(getTechnologyStackById("tomcat-9.0"));
        comboA.setTechnologyStacks(stacksA);
        combos.add(comboA);

        // 套餐B
        Combo comboB = new Combo();
        comboB.setId("combo-b");
        comboB.setName("套餐B");
        comboB.setDescription("Spring Boot 3.2.x + PostgreSQL 14.0 + React 18.x + Tomcat 9.0");
        List<TechnologyStack> stacksB = new ArrayList<>();
        stacksB.add(getTechnologyStackById("spring-boot-3.2.x"));
        stacksB.add(getTechnologyStackById("postgresql-14.0"));
        stacksB.add(getTechnologyStackById("react-18.x"));
        stacksB.add(getTechnologyStackById("tomcat-9.0"));
        comboB.setTechnologyStacks(stacksB);
        combos.add(comboB);

        // 套餐C
        Combo comboC = new Combo();
        comboC.setId("combo-c");
        comboC.setName("套餐C");
        comboC.setDescription("Spring Cloud 2023.0.x + MySQL 8.0 + Vue 3.x + Tomcat 9.0");
        List<TechnologyStack> stacksC = new ArrayList<>();
        stacksC.add(getTechnologyStackById("spring-cloud-2023.0.x"));
        stacksC.add(getTechnologyStackById("mysql-8.0"));
        stacksC.add(getTechnologyStackById("vue-3.x"));
        stacksC.add(getTechnologyStackById("tomcat-9.0"));
        comboC.setTechnologyStacks(stacksC);
        combos.add(comboC);

        // 套餐D
        Combo comboD = new Combo();
        comboD.setId("combo-d");
        comboD.setName("套餐D");
        comboD.setDescription("Spring Boot 2.7.18 + Oracle 19c + Angular 16.x + Tomcat 9.0");
        List<TechnologyStack> stacksD = new ArrayList<>();
        stacksD.add(getTechnologyStackById("spring-boot-2.7.18"));
        stacksD.add(getTechnologyStackById("oracle-19c"));
        stacksD.add(getTechnologyStackById("angular-16.x"));
        stacksD.add(getTechnologyStackById("tomcat-9.0"));
        comboD.setTechnologyStacks(stacksD);
        combos.add(comboD);

        return combos;
    }

    /**
     * 根据ID获取技术栈
     */
    private TechnologyStack getTechnologyStackById(String id) {
        return getAllTechnologyStacks().stream()
                .filter(stack -> stack.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * 验证技术栈兼容性
     */
    public boolean validateCompatibility(List<TechnologyStack> stacks) {
        // 简单的兼容性检查逻辑
        // 这里可以根据实际情况实现更复杂的兼容性检查
        return true;
    }

}