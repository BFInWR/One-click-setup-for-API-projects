<template>
  <div class="tech-stack-selector">
    <h2>技术栈套餐选择</h2>
    
    <!-- 预设套餐 -->
    <div class="preset-combos">
      <h3>预设套餐</h3>
      <div class="combo-list">
        <el-card
          v-for="combo in combos"
          :key="combo.id"
          class="combo-card"
          :class="{ active: selectedCombo === combo.id }"
          @click="selectCombo(combo.id)"
        >
          <template #header>
            <div class="combo-header">
              <span>{{ combo.name }}</span>
            </div>
          </template>
          <div class="combo-description">{{ combo.description }}</div>
          <div class="tech-list">
            <div v-for="tech in combo.technologyStacks" :key="tech.id" class="tech-item">
              {{ tech.name }} {{ tech.version }}
            </div>
          </div>
        </el-card>
      </div>
    </div>

    <!-- 自定义选择 -->
    <div class="custom-selection">
      <h3>自定义选择</h3>
      
      <!-- 后端框架 -->
      <div class="tech-category">
        <h4>后端框架</h4>
        <el-radio-group v-model="customSelection.backend">
          <el-radio
            v-for="tech in backendTechs"
            :key="tech.id"
            :label="tech.id"
          >
            {{ tech.name }} {{ tech.version }}
          </el-radio>
        </el-radio-group>
      </div>

      <!-- 数据库 -->
      <div class="tech-category">
        <h4>数据库</h4>
        <el-radio-group v-model="customSelection.database">
          <el-radio
            v-for="tech in databaseTechs"
            :key="tech.id"
            :label="tech.id"
          >
            {{ tech.name }} {{ tech.version }}
          </el-radio>
        </el-radio-group>
      </div>

      <!-- 前端框架 -->
      <div class="tech-category">
        <h4>前端框架</h4>
        <el-radio-group v-model="customSelection.frontend">
          <el-radio
            v-for="tech in frontendTechs"
            :key="tech.id"
            :label="tech.id"
          >
            {{ tech.name }} {{ tech.version }}
          </el-radio>
        </el-radio-group>
      </div>

      <!-- 应用服务器 -->
      <div class="tech-category">
        <h4>应用服务器</h4>
        <el-radio-group v-model="customSelection.server">
          <el-radio
            v-for="tech in serverTechs"
            :key="tech.id"
            :label="tech.id"
          >
            {{ tech.name }} {{ tech.version }}
          </el-radio>
        </el-radio-group>
      </div>

      <el-button type="primary" @click="confirmCustomSelection">确认选择</el-button>
    </div>

    <!-- 选择结果 -->
    <div class="selection-result" v-if="finalSelection">
      <h3>最终选择</h3>
      <div class="tech-list">
        <div v-for="tech in finalSelection" :key="tech.id" class="tech-item">
          {{ tech.name }} {{ tech.version }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';

// 状态
const combos = ref([]);
const technologyStacks = ref([]);
const selectedCombo = ref('');
const customSelection = ref({
  backend: '',
  database: '',
  frontend: '',
  server: ''
});
const finalSelection = ref([]);

// 计算属性
const backendTechs = computed(() => {
  return technologyStacks.value.filter(tech => tech.category === 'backend');
});

const databaseTechs = computed(() => {
  return technologyStacks.value.filter(tech => tech.category === 'database');
});

const frontendTechs = computed(() => {
  return technologyStacks.value.filter(tech => tech.category === 'frontend');
});

const serverTechs = computed(() => {
  return technologyStacks.value.filter(tech => tech.category === 'server');
});

// 方法
const fetchData = async () => {
  try {
    // 获取预设套餐
    const comboResponse = await axios.get('http://localhost:8080/api-generator/api/technology/combos');
    combos.value = comboResponse.data;
    
    // 获取所有技术栈
    const stackResponse = await axios.get('http://localhost:8080/api-generator/api/technology/stacks');
    technologyStacks.value = stackResponse.data;
  } catch (error) {
    console.error('获取技术栈数据失败:', error);
    // 使用模拟数据
    combos.value = [
      {
        id: "combo-a",
        name: "套餐A",
        description: "Spring Boot 2.7.18 + MySQL 8.0 + Vue 3.x + Tomcat 9.0",
        technologyStacks: [
          { id: "spring-boot-2.7.18", name: "Spring Boot", version: "2.7.18", category: "backend" },
          { id: "mysql-8.0", name: "MySQL", version: "8.0+", category: "database" },
          { id: "vue-3.x", name: "Vue", version: "3.x", category: "frontend" },
          { id: "tomcat-9.0", name: "Tomcat", version: "9.0+", category: "server" }
        ]
      },
      {
        id: "combo-b",
        name: "套餐B",
        description: "Spring Boot 3.2.x + PostgreSQL 14.0 + React 18.x + Tomcat 9.0",
        technologyStacks: [
          { id: "spring-boot-3.2.x", name: "Spring Boot", version: "3.2.x", category: "backend" },
          { id: "postgresql-14.0", name: "PostgreSQL", version: "14.0+", category: "database" },
          { id: "react-18.x", name: "React", version: "18.x", category: "frontend" },
          { id: "tomcat-9.0", name: "Tomcat", version: "9.0+", category: "server" }
        ]
      }
    ];
    technologyStacks.value = [
      { id: "spring-boot-2.7.18", name: "Spring Boot", version: "2.7.18", category: "backend" },
      { id: "spring-boot-3.2.x", name: "Spring Boot", version: "3.2.x", category: "backend" },
      { id: "spring-cloud-2023.0.x", name: "Spring Cloud", version: "2023.0.x", category: "backend" },
      { id: "mysql-8.0", name: "MySQL", version: "8.0+", category: "database" },
      { id: "postgresql-14.0", name: "PostgreSQL", version: "14.0+", category: "database" },
      { id: "oracle-19c", name: "Oracle", version: "19c+", category: "database" },
      { id: "sqlserver-2022", name: "SQL Server", version: "2022+", category: "database" },
      { id: "vue-3.x", name: "Vue", version: "3.x", category: "frontend" },
      { id: "react-18.x", name: "React", version: "18.x", category: "frontend" },
      { id: "angular-16.x", name: "Angular", version: "16.x", category: "frontend" },
      { id: "tomcat-9.0", name: "Tomcat", version: "9.0+", category: "server" },
      { id: "jetty-11.0", name: "Jetty", version: "11.0+", category: "server" },
      { id: "undertow-2.3.x", name: "Undertow", version: "2.3.x", category: "server" }
    ];
  }
};

const selectCombo = (comboId) => {
  selectedCombo.value = comboId;
  // 清空自定义选择
  customSelection.value = {
    backend: '',
    database: '',
    frontend: '',
    server: ''
  };
  const combo = combos.value.find(c => c.id === comboId);
  if (combo) {
    finalSelection.value = combo.technologyStacks;
  }
};

const confirmCustomSelection = async () => {
  const selectedTechs = [];
  
  // 收集选中的技术栈
  const backendTech = technologyStacks.value.find(tech => tech.id === customSelection.value.backend);
  const databaseTech = technologyStacks.value.find(tech => tech.id === customSelection.value.database);
  const frontendTech = technologyStacks.value.find(tech => tech.id === customSelection.value.frontend);
  const serverTech = technologyStacks.value.find(tech => tech.id === customSelection.value.server);
  
  if (backendTech) selectedTechs.push(backendTech);
  if (databaseTech) selectedTechs.push(databaseTech);
  if (frontendTech) selectedTechs.push(frontendTech);
  if (serverTech) selectedTechs.push(serverTech);
  
  // 验证兼容性
  try {
    const response = await axios.post('http://localhost:8080/api-generator/api/technology/validate', selectedTechs);
    if (response.data) {
      finalSelection.value = selectedTechs;
      selectedCombo.value = '';
    } else {
      ElMessage.error('所选技术栈不兼容');
    }
  } catch (error) {
    console.error('验证技术栈兼容性失败:', error);
    ElMessage.error('验证技术栈兼容性失败');
  }
};

const nextStep = () => {
  // 触发下一步事件，传递最终选择的技术栈
  emit('next', finalSelection.value);
};

// 事件
const emit = defineEmits(['next']);

// 监听自定义选择变化
watch(customSelection, (newValue) => {
  // 如果自定义选择有任何值，取消预设套餐的选择
  if (newValue.backend || newValue.database || newValue.frontend || newValue.server) {
    selectedCombo.value = '';
  }
}, { deep: true });

// 生命周期
onMounted(() => {
  fetchData();
});
</script>

<style scoped>
.tech-stack-selector {
  padding: 20px;
}

.preset-combos,
.custom-selection {
  margin-bottom: 30px;
}

.combo-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
  margin-top: 15px;
}

.combo-card {
  cursor: pointer;
  transition: all 0.3s ease;
}

.combo-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.combo-card.active {
  border: 2px solid #409EFF;
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.3);
}

.combo-description {
  margin: 10px 0;
  color: #666;
}

.tech-list {
  margin-top: 15px;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.tech-item {
  background-color: #f0f9ff;
  color: #1677ff;
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 12px;
}

.tech-category {
  margin-bottom: 20px;
}

.tech-category h4 {
  margin-bottom: 10px;
  color: #333;
}

.selection-result {
  margin-top: 30px;
  padding: 20px;
  background-color: #f6ffed;
  border: 1px solid #b7eb8f;
  border-radius: 4px;
}

.selection-result h3 {
  margin-bottom: 15px;
  color: #52c41a;
}
</style>