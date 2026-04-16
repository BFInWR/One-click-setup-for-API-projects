<template>
  <div class="app">
    <header class="app-header">
      <h1>AI接口项目生成系统</h1>
      <p>快速搭建基于所选技术栈的接口项目</p>
    </header>

    <main class="app-main">
      <el-steps :active="activeStep" finish-status="success">
        <el-step title="技术栈选择" description="选择项目使用的技术栈套餐"></el-step>
        <el-step title="接口文档输入" description="输入接口文档和数据库表结构"></el-step>
        <el-step title="文档生成" description="生成AI可读懂的Markdown文档"></el-step>
        <el-step title="项目指导" description="获取项目搭建步骤指导"></el-step>
      </el-steps>

      <div class="step-content">
        <!-- 步骤1：技术栈选择 -->
        <div v-if="activeStep === 0">
          <TechnologyStackSelector @next="handleTechStackNext" />
        </div>

        <!-- 步骤2：接口文档输入 -->
        <div v-else-if="activeStep === 1">
          <ApiDocInput @next="handleApiDocNext" />
        </div>

        <!-- 步骤3：文档生成 -->
        <div v-else-if="activeStep === 2">
          <MarkdownGenerator :technologyStacks="selectedTechStacks" :apiDoc="apiDoc" />
        </div>

        <!-- 步骤4：项目指导 -->
        <div v-else-if="activeStep === 3">
          <ProjectGuide :technologyStacks="selectedTechStacks" />
        </div>
      </div>

      <div class="step-actions">
        <el-button v-if="activeStep > 0" @click="prevStep">上一步</el-button>
        <el-button v-if="activeStep < 3" type="primary" @click="nextStep">下一步</el-button>
        <el-button v-if="activeStep === 3" type="success" @click="restart">重新开始</el-button>
      </div>
    </main>

    <footer class="app-footer">
      <p>&copy; 2026 AI接口项目生成系统</p>
    </footer>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import TechnologyStackSelector from './components/TechnologyStackSelector.vue';
import ApiDocInput from './components/ApiDocInput.vue';
import MarkdownGenerator from './components/MarkdownGenerator.vue';
import ProjectGuide from './components/ProjectGuide.vue';

// 状态
const activeStep = ref(0);
const selectedTechStacks = ref([]);
const apiDoc = ref(null);

// 方法
const handleTechStackNext = (techStacks) => {
  selectedTechStacks.value = techStacks;
  activeStep.value = 1;
};

const handleApiDocNext = (doc) => {
  apiDoc.value = doc;
  activeStep.value = 2;
};

const nextStep = () => {
  // 验证当前步骤是否完成
  if (activeStep.value === 0 && selectedTechStacks.value.length === 0) {
    // 技术栈未选择
    return;
  } else if (activeStep.value === 1 && !apiDoc.value) {
    // 接口文档未输入
    return;
  }
  
  if (activeStep.value < 3) {
    activeStep.value++;
  }
};

const prevStep = () => {
  if (activeStep.value > 0) {
    activeStep.value--;
  }
};

const restart = () => {
  activeStep.value = 0;
  selectedTechStacks.value = [];
  apiDoc.value = null;
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  line-height: 1.6;
  color: #333;
  background-color: #f5f5f5;
}

.app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.app-header {
  background-color: #409EFF;
  color: white;
  padding: 2rem 0;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.app-header h1 {
  font-size: 2.5rem;
  margin-bottom: 0.5rem;
}

.app-header p {
  font-size: 1.2rem;
  opacity: 0.9;
}

.app-main {
  flex: 1;
  padding: 2rem;
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
}

.step-content {
  margin: 2rem 0;
  padding: 2rem;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.step-actions {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  margin-top: 2rem;
}

.app-footer {
  background-color: #333;
  color: white;
  text-align: center;
  padding: 1rem 0;
  margin-top: 2rem;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .app-main {
    padding: 1rem;
  }

  .step-content {
    padding: 1rem;
  }

  .app-header h1 {
    font-size: 2rem;
  }
}
</style>