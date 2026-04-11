<template>
  <div class="project-guide">
    <h2>项目生成指导</h2>
    
    <el-card v-loading="loading" element-loading-text="生成中...">
      <template #header>
        <div class="card-header">
          <span>项目搭建步骤</span>
          <el-button type="primary" @click="downloadGuide" :disabled="!guideContent">
            下载指南
          </el-button>
        </div>
      </template>
      
      <div class="guide-content" v-if="guideContent">
        <div v-html="renderedGuide"></div>
      </div>
      <div class="empty-state" v-else>
        <el-empty description="请先选择技术栈"></el-empty>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { marked } from 'marked';
import hljs from 'highlight.js';
import 'highlight.js/styles/github.css';

// 状态
const loading = ref(false);
const guideContent = ref('');
const technologyStacks = ref([]);

// 计算属性
const renderedGuide = computed(() => {
  if (!guideContent.value) return '';
  
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
  
  return marked(guideContent.value);
});

// 方法
const generateGuide = async () => {
  if (!technologyStacks.value || technologyStacks.value.length === 0) {
    ElMessage.warning('请先选择技术栈');
    return;
  }

  loading.value = true;
  try {
    const response = await axios.post('http://localhost:8080/api-generator/api/project/generate-guide', technologyStacks.value);
    guideContent.value = response.data;
    ElMessage.success('项目指导生成成功');
  } catch (error) {
    console.error('生成项目指导失败:', error);
    ElMessage.error('生成项目指导失败');
  } finally {
    loading.value = false;
  }
};

const downloadGuide = () => {
  if (!guideContent.value) return;
  
  const blob = new Blob([guideContent.value], { type: 'text/markdown' });
  const url = URL.createObjectURL(blob);
  const a = document.createElement('a');
  a.href = url;
  a.download = '项目搭建指导.md';
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
  URL.revokeObjectURL(url);
  ElMessage.success('指南下载成功');
};

// 监听技术栈变化
watch(technologyStacks, () => {
  generateGuide();
}, { deep: true, immediate: true });

// 暴露属性
defineProps({
  technologyStacks: {
    type: Array,
    default: () => []
  }
});
</script>

<style scoped>
.project-guide {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.guide-content {
  margin-top: 20px;
  line-height: 1.6;
}

.guide-content h1,
.guide-content h2,
.guide-content h3,
.guide-content h4 {
  margin-top: 1.5em;
  margin-bottom: 0.8em;
  font-weight: 600;
}

.guide-content h1 {
  font-size: 1.8em;
  border-bottom: 1px solid #eaecef;
  padding-bottom: 0.3em;
}

.guide-content h2 {
  font-size: 1.5em;
  border-bottom: 1px solid #eaecef;
  padding-bottom: 0.3em;
}

.guide-content h3 {
  font-size: 1.2em;
}

.guide-content h4 {
  font-size: 1em;
}

.guide-content p {
  margin-bottom: 1em;
}

.guide-content ul,
.guide-content ol {
  margin-bottom: 1em;
  padding-left: 2em;
}

.guide-content li {
  margin-bottom: 0.5em;
}

.guide-content code {
  background-color: #f6f8fa;
  padding: 0.2em 0.4em;
  border-radius: 3px;
  font-size: 0.9em;
}

.guide-content pre {
  background-color: #f6f8fa;
  padding: 1em;
  border-radius: 6px;
  overflow-x: auto;
  margin-bottom: 1em;
}

.guide-content pre code {
  background-color: transparent;
  padding: 0;
}

.empty-state {
  padding: 40px 0;
  text-align: center;
}
</style>