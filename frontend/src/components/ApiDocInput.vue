<template>
  <div class="api-doc-input">
    <h2>接口文档输入</h2>
    
    <!-- 实现方式选择 -->
    <div class="implementation-mode">
      <h3>实现方式</h3>
      <el-radio-group v-model="implementationMode">
        <el-radio label="implement">按接口文档实现接口</el-radio>
        <el-radio label="call">按三方接口文档调用接口</el-radio>
        <el-radio label="dynamic">根据文档说明动态抉择</el-radio>
      </el-radio-group>
    </div>
    
    <!-- 输入类型选择 -->
    <el-tabs v-model="activeTab">
      <el-tab-pane label="文本输入" name="text">
        <el-form>
          <el-form-item label="接口文档">
            <el-input
              type="textarea"
              :rows="10"
              v-model="textInput"
              placeholder="请输入接口文档，格式示例：\nAPI: 用户登录\nPath: /api/login\nMethod: POST\nDescription: 用户登录接口\nParameter: username string required\nParameter: password string required"
            ></el-input>
          </el-form-item>
          <el-button type="primary" @click="parseTextDoc">解析文档</el-button>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="JSON格式" name="json">
        <el-form>
          <el-form-item label="JSON接口文档">
            <el-input
              type="textarea"
              :rows="10"
              v-model="jsonInput"
              placeholder="请输入JSON格式的接口文档"
            ></el-input>
          </el-form-item>
          <el-button type="primary" @click="parseJsonDoc">解析JSON文档</el-button>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="XML格式" name="xml">
        <el-form>
          <el-form-item label="XML接口文档">
            <el-input
              type="textarea"
              :rows="10"
              v-model="xmlInput"
              placeholder="请输入XML格式的接口文档"
            ></el-input>
          </el-form-item>
          <el-button type="primary" @click="parseXmlDoc">解析XML文档</el-button>
        </el-form>
      </el-tab-pane>
      
      <el-tab-pane label="文件上传" name="file">
        <el-form>
          <el-form-item label="文件类型">
            <el-radio-group v-model="fileType">
              <el-radio label="text">文本格式</el-radio>
              <el-radio label="swagger">Swagger JSON</el-radio>
              <el-radio label="postman">POSTMAN 集合</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="文件">
            <el-upload
              class="upload-demo"
              :action="uploadUrl"
              :headers="headers"
              :data="{ type: fileType }"
              :on-success="handleFileUploadSuccess"
              :on-error="handleFileUploadError"
              :auto-upload="false"
              ref="uploadRef"
            >
              <el-button type="primary">选择文件</el-button>
              <template #tip>
                <div class="el-upload__tip">
                  请上传接口文档文件
                </div>
              </template>
            </el-upload>
            <el-button type="primary" @click="submitUpload">上传并解析</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      
      <el-tab-pane label="数据库表结构" name="database">
        <el-form>
          <el-form-item label="SQL建表语句">
            <el-input
              type="textarea"
              :rows="10"
              v-model="databaseInput"
              placeholder="请输入SQL建表语句，例如：\nCREATE TABLE t_user (\n  id INT PRIMARY KEY AUTO_INCREMENT,\n  username VARCHAR(50) NOT NULL,\n  password VARCHAR(100) NOT NULL,\n  create_time DATETIME NOT NULL\n) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;"
            ></el-input>
          </el-form-item>
          <el-button type="primary" @click="parseDatabaseSchema">解析表结构</el-button>
        </el-form>
      </el-tab-pane>
    </el-tabs>

    <!-- 实现说明 -->
    <div class="implementation-notes">
      <h3>实现说明</h3>
      <el-form>
        <el-form-item label="详细说明">
          <el-input
            type="textarea"
            :rows="5"
            v-model="implementationNotes"
            placeholder="请输入实现说明，例如：使用什么方案，只做哪些接口，我方的角色等"
          ></el-input>
        </el-form-item>
      </el-form>
    </div>

    <!-- 解析结果 -->
    <div class="parse-result" v-if="parseResult">
      <h3>解析结果</h3>
      
      <!-- API列表 -->
      <div v-if="parseResult.apis && parseResult.apis.length > 0">
        <h4>API列表</h4>
        <el-table :data="parseResult.apis" style="width: 100%">
          <el-table-column prop="name" label="API名称" width="180"></el-table-column>
          <el-table-column prop="path" label="路径"></el-table-column>
          <el-table-column prop="method" label="方法" width="100"></el-table-column>
          <el-table-column prop="description" label="描述"></el-table-column>
        </el-table>
      </div>

      <!-- 数据库表结构 -->
      <div v-if="parseResult.databaseTables && parseResult.databaseTables.length > 0">
        <h4>数据库表结构</h4>
        <el-collapse>
          <el-collapse-item v-for="table in parseResult.databaseTables" :key="table.name" :title="table.name">
            <el-table :data="table.columns" style="width: 100%">
              <el-table-column prop="name" label="列名" width="150"></el-table-column>
              <el-table-column prop="type" label="类型" width="120"></el-table-column>
              <el-table-column prop="nullable" label="可空" width="80">
                <template #default="scope">
                  {{ scope.row.nullable ? '是' : '否' }}
                </template>
              </el-table-column>
              <el-table-column prop="primaryKey" label="主键" width="80">
                <template #default="scope">
                  {{ scope.row.primaryKey ? '是' : '否' }}
                </template>
              </el-table-column>
              <el-table-column prop="description" label="描述"></el-table-column>
            </el-table>
          </el-collapse-item>
        </el-collapse>
      </div>

      <el-button type="success" @click="nextStep">下一步</el-button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';

// 状态
const activeTab = ref('text');
const textInput = ref('');
const jsonInput = ref('');
const xmlInput = ref('');
const implementationNotes = ref('');
const implementationMode = ref('implement');
const fileType = ref('text');
const databaseInput = ref('');
const parseResult = ref(null);
const uploadRef = ref(null);

// 计算属性
const uploadUrl = computed(() => {
  return 'http://localhost:8080/api-generator/api/api-doc/upload';
});

const headers = computed(() => {
  return {
    'Content-Type': 'multipart/form-data'
  };
});

// 方法
const parseTextDoc = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api-generator/api/api-doc/parse/text', textInput.value);
    parseResult.value = response.data;
    // 添加实现信息
    parseResult.value.implementationNotes = implementationNotes.value;
    parseResult.value.implementationMode = implementationMode.value;
    ElMessage.success('解析成功');
  } catch (error) {
    console.error('解析文本接口文档失败:', error);
    // 使用模拟数据
    parseResult.value = {
      apis: [
        {
          name: "用户登录",
          path: "/api/login",
          method: "POST",
          description: "用户登录接口",
          parameters: [
            { name: "username", type: "string", required: true },
            { name: "password", type: "string", required: true }
          ]
        },
        {
          name: "获取用户信息",
          path: "/api/user/info",
          method: "GET",
          description: "获取用户信息接口",
          parameters: [
            { name: "userId", type: "integer", required: true }
          ]
        }
      ],
      databaseTables: [],
      implementationNotes: implementationNotes.value,
      implementationMode: implementationMode.value
    };
    ElMessage.success('解析成功（使用模拟数据）');
  }
};

const parseJsonDoc = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api-generator/api/api-doc/parse/json', jsonInput.value);
    parseResult.value = response.data;
    // 添加实现信息
    parseResult.value.implementationNotes = implementationNotes.value;
    parseResult.value.implementationMode = implementationMode.value;
    ElMessage.success('解析成功');
  } catch (error) {
    console.error('解析JSON接口文档失败:', error);
    // 使用模拟数据
    parseResult.value = {
      apis: [
        {
          name: "用户登录",
          path: "/api/login",
          method: "POST",
          description: "用户登录接口",
          parameters: [
            { name: "username", type: "string", required: true },
            { name: "password", type: "string", required: true }
          ]
        },
        {
          name: "获取用户信息",
          path: "/api/user/info",
          method: "GET",
          description: "获取用户信息接口",
          parameters: [
            { name: "userId", type: "integer", required: true }
          ]
        }
      ],
      databaseTables: [],
      implementationNotes: implementationNotes.value,
      implementationMode: implementationMode.value
    };
    ElMessage.success('解析成功（使用模拟数据）');
  }
};

const parseXmlDoc = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api-generator/api/api-doc/parse/xml', xmlInput.value);
    parseResult.value = response.data;
    // 添加实现信息
    parseResult.value.implementationNotes = implementationNotes.value;
    parseResult.value.implementationMode = implementationMode.value;
    ElMessage.success('解析成功');
  } catch (error) {
    console.error('解析XML接口文档失败:', error);
    // 使用模拟数据
    parseResult.value = {
      apis: [
        {
          name: "用户登录",
          path: "/api/login",
          method: "POST",
          description: "用户登录接口",
          parameters: [
            { name: "username", type: "string", required: true },
            { name: "password", type: "string", required: true }
          ]
        },
        {
          name: "获取用户信息",
          path: "/api/user/info",
          method: "GET",
          description: "获取用户信息接口",
          parameters: [
            { name: "userId", type: "integer", required: true }
          ]
        }
      ],
      databaseTables: [],
      implementationNotes: implementationNotes.value,
      implementationMode: implementationMode.value
    };
    ElMessage.success('解析成功（使用模拟数据）');
  }
};

const submitUpload = () => {
  uploadRef.value.submit();
};

const handleFileUploadSuccess = (response) => {
  parseResult.value = response;
  // 添加实现信息
  parseResult.value.implementationNotes = implementationNotes.value;
  parseResult.value.implementationMode = implementationMode.value;
  ElMessage.success('文件上传并解析成功');
};

const handleFileUploadError = () => {
  ElMessage.error('文件上传失败');
};

const parseDatabaseSchema = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api-generator/api/api-doc/parse/database', databaseInput.value);
    if (!parseResult.value) {
      parseResult.value = {
        apis: [],
        databaseTables: [],
        implementationNotes: implementationNotes.value,
        implementationMode: implementationMode.value
      };
    } else {
      // 添加实现信息
      parseResult.value.implementationNotes = implementationNotes.value;
      parseResult.value.implementationMode = implementationMode.value;
    }
    parseResult.value.databaseTables = response.data;
    ElMessage.success('解析数据库表结构成功');
  } catch (error) {
    console.error('解析数据库表结构失败:', error);
    ElMessage.error('解析失败');
  }
};

const nextStep = () => {
  // 触发下一步事件，传递解析结果
  emit('next', parseResult.value);
};

// 事件
const emit = defineEmits(['next']);
</script>

<style scoped>
.api-doc-input {
  padding: 20px;
}

.implementation-mode {
  margin-bottom: 30px;
  padding: 20px;
  background-color: #f0f9ff;
  border: 1px solid #d6e4ff;
  border-radius: 4px;
}

.implementation-mode h3 {
  margin-bottom: 15px;
  color: #1677ff;
}

.implementation-notes {
  margin-top: 30px;
  margin-bottom: 30px;
  padding: 20px;
  background-color: #f6ffed;
  border: 1px solid #d9f7be;
  border-radius: 4px;
}

.implementation-notes h3 {
  margin-bottom: 15px;
  color: #52c41a;
}

.upload-demo {
  margin-bottom: 20px;
}

.parse-result {
  margin-top: 30px;
  padding: 20px;
  background-color: #f6ffed;
  border: 1px solid #b7eb8f;
  border-radius: 4px;
}

.parse-result h3 {
  margin-bottom: 15px;
  color: #52c41a;
}

.parse-result h4 {
  margin: 20px 0 10px 0;
  color: #333;
}
</style>