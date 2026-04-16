<template>
  <div class="markdown-generator">
    <h2>Markdown文档生成</h2>
    
    <el-card v-loading="loading" element-loading-text="生成中...">
      <template #header>
        <div class="card-header">
          <span>生成的接口项目构建文档</span>
          <el-button type="primary" @click="downloadMarkdown" :disabled="!markdownContent">
            下载文档
          </el-button>
        </div>
      </template>
      
      <div class="markdown-content" v-if="markdownContent">
        <div v-html="renderedMarkdown"></div>
      </div>
      <div class="empty-state" v-else>
        <el-empty description="请先选择技术栈并输入接口文档"></el-empty>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { marked } from 'marked';
import hljs from 'highlight.js';
import 'highlight.js/styles/github.css';

// 状态
const loading = ref(false);
const markdownContent = ref('');
const technologyStacks = ref([]);
const apiDoc = ref(null);

// 计算属性
const renderedMarkdown = computed(() => {
  if (!markdownContent.value) return '';
  
  // 配置marked
  marked.setOptions({
    highlight: function(code, lang) {
      if (lang && hljs.getLanguage(lang)) {
        return hljs.highlight(code, { language: lang }).value;
      }
      return hljs.highlightAuto(code).value;
    },
    breaks: true,
    gfm: true
  });
  
  return marked(markdownContent.value);
});

// 方法
const generateMarkdown = async () => {
  if (!technologyStacks.value || technologyStacks.value.length === 0) {
    ElMessage.warning('请先选择技术栈');
    return;
  }

  loading.value = true;
  try {
    const response = await axios.post('http://localhost:8080/api-generator/api/markdown/generate/json', {
      technologyStacks: technologyStacks.value,
      apiDoc: apiDoc.value
    });
    markdownContent.value = response.data;
    ElMessage.success('文档生成成功');
  } catch (error) {
    console.error('生成Markdown文档失败:', error);
    const backendTech = technologyStacks.value.find(t => t.category === 'backend') || { name: 'Spring Boot', version: '2.7.18' };
    const databaseTech = technologyStacks.value.find(t => t.category === 'database') || { name: 'MySQL', version: '8.0+' };
    const frontendTech = technologyStacks.value.find(t => t.category === 'frontend') || { name: 'Vue', version: '3.x' };
    const serverTech = technologyStacks.value.find(t => t.category === 'server') || { name: 'Tomcat', version: '9.0+' };
    
    // 使用普通字符串拼接而不是模板字符串，避免Vue编译器的问题
    let markdownText = '# 接口项目构建流程文档\n\n';
    markdownText += '## 技术栈\n\n';
    markdownText += '- 后端框架: ' + backendTech.name + ' ' + backendTech.version + '\n';
    markdownText += '- 数据库: ' + databaseTech.name + ' ' + databaseTech.version + '\n';
    markdownText += '- 前端框架: ' + frontendTech.name + ' ' + frontendTech.version + '\n';
    markdownText += '- 应用服务器: ' + serverTech.name + ' ' + serverTech.version + '\n\n';
    markdownText += '## 接口列表\n\n';
    markdownText += '### 1. 用户登录\n';
    markdownText += '- **路径**: /api/login\n';
    markdownText += '- **方法**: POST\n';
    markdownText += '- **描述**: 用户登录接口\n';
    markdownText += '- **参数**:\n';
    markdownText += '  - username: string (必填)\n';
    markdownText += '  - password: string (必填)\n\n';
    markdownText += '### 2. 获取用户信息\n';
    markdownText += '- **路径**: /api/user/info\n';
    markdownText += '- **方法**: GET\n';
    markdownText += '- **描述**: 获取用户信息接口\n';
    markdownText += '- **参数**:\n';
    markdownText += '  - userId: integer (必填)\n\n';
    markdownText += '## 项目搭建步骤\n\n';
    markdownText += '1. **创建项目**\n';
    markdownText += '   ```bash\n';
    markdownText += '   # 使用Spring Initializr创建项目\n';
    markdownText += '   curl https://start.spring.io/starter.zip -d dependencies=web,data-jpa,mysql -d javaVersion=1.8 -o api-project.zip\n';
    markdownText += '   unzip api-project.zip\n';
    markdownText += '   cd api-project\n';
    markdownText += '   ```\n\n';
    markdownText += '2. **配置数据库**\n';
    markdownText += '   ```yaml\n';
    markdownText += '   # application.yml\n';
    markdownText += '   spring:\n';
    markdownText += '     datasource:\n';
    markdownText += '       url: jdbc:mysql://localhost:3306/api_db\n';
    markdownText += '       username: root\n';
    markdownText += '       password: password\n';
    markdownText += '       driver-class-name: com.mysql.cj.jdbc.Driver\n';
    markdownText += '   ```\n\n';
    markdownText += '3. **实现接口**\n';
    markdownText += '   - 创建控制器\n';
    markdownText += '   - 实现业务逻辑\n';
    markdownText += '   - 配置路由\n\n';
    markdownText += '4. **启动项目**\n';
    markdownText += '   ```bash\n';
    markdownText += '   mvn spring-boot:run\n';
    markdownText += '   ```\n\n';
    markdownText += '5. **测试接口**\n';
    markdownText += '   ```bash\n';
    markdownText += '   # 测试登录接口\n';
    markdownText += '   curl -X POST http://localhost:8080/api/login -H "Content-Type: application/json" -d "{\"username\": \"admin\", \"password\": \"123456\"}\n';
    markdownText += '   ```\n\n';
    markdownText += '## 部署指南\n\n';
    markdownText += '1. **构建项目**\n';
    markdownText += '   ```bash\n';
    markdownText += '   mvn clean package\n';
    markdownText += '   ```\n\n';
    markdownText += '2. **部署到服务器**\n';
    markdownText += '   ```bash\n';
    markdownText += '   java -jar target/api-project.jar\n';
    markdownText += '   ```\n\n';
    markdownText += '3. **配置Nginx**\n';
    markdownText += '   ```nginx\n';
    markdownText += '   server {\n';
    markdownText += '     listen 80;\n';
    markdownText += '     server_name api.example.com;\n';
    markdownText += '     \n';
    markdownText += '     location / {\n';
    markdownText += '       proxy_pass http://localhost:8080;\n';
    markdownText += '       proxy_set_header Host $host;\n';
    markdownText += '       proxy_set_header X-Real-IP $remote_addr;\n';
    markdownText += '     }\n';
    markdownText += '   }\n';
    markdownText += '   ```\n';
    
    markdownContent.value = markdownText;
    ElMessage.success('文档生成成功（使用模拟数据）');
  } finally {
    loading.value = false;
  }
};

const downloadMarkdown = () => {
  if (!markdownContent.value) return;
  
  const blob = new Blob([markdownContent.value], { type: 'text/markdown' });
  const url = URL.createObjectURL(blob);
  const a = document.createElement('a');
  a.href = url;
  a.download = '接口项目构建流程文档.md';
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
  URL.revokeObjectURL(url);
  ElMessage.success('文档下载成功');
};

// 监听属性变化
watch([technologyStacks, apiDoc], () => {
  generateMarkdown();
}, { deep: true, immediate: true });

// 暴露属性
defineProps({
  technologyStacks: {
    type: Array,
    default: () => []
  },
  apiDoc: {
    type: Object,
    default: null
  }
});
</script>

<style scoped>
.markdown-generator {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.markdown-content {
  margin-top: 20px;
  line-height: 1.6;
}

.markdown-content h1,
.markdown-content h2,
.markdown-content h3,
.markdown-content h4 {
  margin-top: 1.5em;
  margin-bottom: 0.8em;
  font-weight: 600;
}

.markdown-content h1 {
  font-size: 1.8em;
  border-bottom: 1px solid #eaecef;
  padding-bottom: 0.3em;
}

.markdown-content h2 {
  font-size: 1.5em;
  border-bottom: 1px solid #eaecef;
  padding-bottom: 0.3em;
}

.markdown-content h3 {
  font-size: 1.2em;
}

.markdown-content h4 {
  font-size: 1em;
}

.markdown-content p {
  margin-bottom: 1em;
}

.markdown-content ul,
.markdown-content ol {
  margin-bottom: 1em;
  padding-left: 2em;
}

.markdown-content li {
  margin-bottom: 0.5em;
}

.markdown-content code {
  background-color: #f6f8fa;
  padding: 0.2em 0.4em;
  border-radius: 3px;
  font-size: 0.9em;
}

.markdown-content pre {
  background-color: #f6f8fa;
  padding: 1em;
  border-radius: 6px;
  overflow-x: auto;
  margin-bottom: 1em;
}

.markdown-content pre code {
  background-color: transparent;
  padding: 0;
}

.markdown-content table {
  border-collapse: collapse;
  width: 100%;
  margin-bottom: 1em;
}

.markdown-content th,
.markdown-content td {
  border: 1px solid #eaecef;
  padding: 0.6em 1em;
  text-align: left;
}

.markdown-content th {
  background-color: #f6f8fa;
  font-weight: 600;
}

.empty-state {
  padding: 40px 0;
  text-align: center;
}
</style>