package com.example.apigenerator.service;

import com.example.apigenerator.model.*;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口文档服务
 */
@Service
public class ApiDocService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 解析文本格式的接口文档
     */
    public ApiDoc parseTextApiDoc(String text) {
        ApiDoc apiDoc = new ApiDoc();
        apiDoc.setName("解析的接口文档");
        apiDoc.setDescription("从文本解析的接口文档");
        apiDoc.setApis(new ArrayList<>());
        apiDoc.setDatabaseTables(new ArrayList<>());

        // 简单的文本解析逻辑，实际项目中需要更复杂的解析
        // 这里只是一个示例
        String[] lines = text.split("\n");
        Api currentApi = null;
        List<ApiParameter> parameters = null;

        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("API:")) {
                if (currentApi != null) {
                    if (parameters != null) {
                        currentApi.setParameters(parameters);
                    }
                    apiDoc.getApis().add(currentApi);
                }
                currentApi = new Api();
                currentApi.setName(line.substring(4).trim());
                parameters = new ArrayList<>();
            } else if (line.startsWith("Path:")) {
                if (currentApi != null) {
                    currentApi.setPath(line.substring(5).trim());
                }
            } else if (line.startsWith("Method:")) {
                if (currentApi != null) {
                    currentApi.setMethod(line.substring(7).trim());
                }
            } else if (line.startsWith("Description:")) {
                if (currentApi != null) {
                    currentApi.setDescription(line.substring(12).trim());
                }
            } else if (line.startsWith("Parameter:")) {
                if (parameters != null) {
                    String paramStr = line.substring(10).trim();
                    String[] parts = paramStr.split(" ");
                    if (parts.length >= 3) {
                        ApiParameter param = new ApiParameter();
                        param.setName(parts[0]);
                        param.setType(parts[1]);
                        param.setRequired(parts[2].equals("required"));
                        parameters.add(param);
                    }
                }
            }
        }

        if (currentApi != null) {
            if (parameters != null) {
                currentApi.setParameters(parameters);
            }
            apiDoc.getApis().add(currentApi);
        }

        return apiDoc;
    }

    /**
     * 解析Swagger JSON格式的接口文档
     */
    public ApiDoc parseSwaggerJson(String json) {
        // 实际项目中需要解析Swagger JSON格式
        // 这里返回一个示例
        ApiDoc apiDoc = new ApiDoc();
        apiDoc.setName("Swagger接口文档");
        apiDoc.setDescription("从Swagger JSON解析的接口文档");
        apiDoc.setApis(new ArrayList<>());
        apiDoc.setDatabaseTables(new ArrayList<>());

        return apiDoc;
    }

    /**
     * 解析POSTMAN集合格式的接口文档
     */
    public ApiDoc parsePostmanCollection(String json) {
        // 实际项目中需要解析POSTMAN集合格式
        // 这里返回一个示例
        ApiDoc apiDoc = new ApiDoc();
        apiDoc.setName("POSTMAN接口文档");
        apiDoc.setDescription("从POSTMAN集合解析的接口文档");
        apiDoc.setApis(new ArrayList<>());
        apiDoc.setDatabaseTables(new ArrayList<>());

        return apiDoc;
    }

    /**
     * 解析数据库表结构
     */
    public List<DatabaseTable> parseDatabaseSchema(String schema) {
        List<DatabaseTable> tables = new ArrayList<>();

        // 简单的SQL解析逻辑，实际项目中需要更复杂的解析
        // 这里只是一个示例
        String[] lines = schema.split("\n");
        DatabaseTable currentTable = null;
        List<DatabaseColumn> columns = null;

        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("CREATE TABLE")) {
                if (currentTable != null) {
                    if (columns != null) {
                        currentTable.setColumns(columns);
                    }
                    tables.add(currentTable);
                }
                currentTable = new DatabaseTable();
                String tableName = line.substring(13, line.indexOf("(")).trim();
                currentTable.setName(tableName);
                columns = new ArrayList<>();
            } else if (line.startsWith(") ENGINE=")) {
                if (currentTable != null && columns != null) {
                    currentTable.setColumns(columns);
                    tables.add(currentTable);
                    currentTable = null;
                    columns = null;
                }
            } else if (currentTable != null && columns != null && !line.startsWith("PRIMARY KEY") && !line.startsWith(")")) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    DatabaseColumn column = new DatabaseColumn();
                    column.setName(parts[0]);
                    column.setType(parts[1].replace("(", "").replace(")", ""));
                    column.setNullable(!line.contains("NOT NULL"));
                    column.setPrimaryKey(line.contains("PRIMARY KEY"));
                    columns.add(column);
                }
            }
        }

        return tables;
    }

    /**
     * 验证接口文档的完整性
     */
    public boolean validateApiDoc(ApiDoc apiDoc) {
        if (apiDoc == null) {
            return false;
        }

        if (apiDoc.getApis() != null) {
            for (Api api : apiDoc.getApis()) {
                if (api.getPath() == null || api.getMethod() == null) {
                    return false;
                }
            }
        }

        return true;
    }

}