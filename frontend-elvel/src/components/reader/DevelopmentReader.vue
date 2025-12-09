<!-- src/components/reader/DevelopmentReader.vue -->
<template>
  <div class="development-reader">
    <div
        v-for="(chapter, chapterIndex) in book.chapters"
        :key="chapterIndex"
        class="chapter"
    >
      <h2>{{ chapter.title }}</h2>

      <div class="content-section">
        <h3>内容</h3>
        <div class="chapter-content">
          {{ chapter.content }}
        </div>
      </div>

      <div v-if="chapter.plotDecisions.length > 0" class="decisions-section">
        <h3>创作决策</h3>
        <div
            v-for="(decision, decisionIndex) in chapter.plotDecisions"
            :key="decisionIndex"
            class="decision-item"
        >
          <span class="decision-type" :class="decision.type">
            {{ decision.type === 'system' ? '系统' : '自定义' }}
          </span>
          <span class="decision-text">{{ decision.option }}</span>
          <span class="decision-time">{{ formatTime(decision.timestamp) }}</span>
        </div>
      </div>

      <div v-if="chapter.keywords.length > 0" class="keywords-section">
        <h3>关键词</h3>
        <div class="keywords">
          <span
              v-for="(keyword, keywordIndex) in chapter.keywords"
              :key="keywordIndex"
              class="keyword"
          >
            {{ keyword }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DevelopmentReader',
  props: {
    book: Object
  },
  setup() {
    const formatTime = (timestamp) => {
      return new Date(timestamp).toLocaleString('zh-CN')
    }

    return {
      formatTime
    }
  }
}
</script>

<style scoped>
.development-reader {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.chapter {
  margin-bottom: 3rem;
  padding-bottom: 2rem;
  border-bottom: 1px solid #eee;
}

.chapter:last-child {
  margin-bottom: 0;
  border-bottom: none;
}

.chapter h2 {
  color: #2c3e50;
  margin-bottom: 1.5rem;
}

.content-section,
.decisions-section,
.keywords-section {
  margin-bottom: 2rem;
}

.content-section h3,
.decisions-section h3,
.keywords-section h3 {
  color: #555;
  margin-bottom: 1rem;
  font-size: 1.1rem;
}

.chapter-content {
  white-space: pre-wrap;
  line-height: 1.8;
  background: #f9f9f9;
  padding: 1rem;
  border-radius: 4px;
  border-left: 3px solid #4CAF50;
}

.decision-item {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 8px 12px;
  background: #f5f5f5;
  border-radius: 4px;
  margin-bottom: 8px;
}

.decision-type {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 0.8rem;
  font-weight: bold;
}

.decision-type.system {
  background: #e3f2fd;
  color: #1976d2;
}

.decision-type.custom {
  background: #f3e5f5;
  color: #7b1fa2;
}

.decision-text {
  flex: 1;
}

.decision-time {
  font-size: 0.8rem;
  color: #666;
}

.keywords {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.keyword {
  background: #ffeb3b;
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 0.8rem;
}
</style>
