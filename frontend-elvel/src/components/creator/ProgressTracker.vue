<!-- src/components/creator/ProgressTracker.vue -->
<template>
  <div class="progress-tracker">
    <h3>创作进度</h3>
    <div class="chapters-list">
      <div
          v-for="(chapter, index) in chapters"
          :key="index"
          :class="['chapter-item', { active: index === currentChapter }]"
      >
        <span class="chapter-number">{{ chapter.title }}</span>
        <span class="chapter-status">
          {{ chapter.content ? '已完成' : '待完成' }}
        </span>
      </div>
    </div>
    <div class="progress-stats">
      <p>已完成章节: {{ completedChapters }}/{{ chapters.length }}</p>
      <p>总字数: {{ totalWords }} 字</p>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'

export default {
  name: 'ProgressTracker',
  props: {
    chapters: Array,
    currentChapter: Number
  },
  setup(props) {
    const completedChapters = computed(() => {
      return props.chapters.filter(chapter => chapter.content.trim().length > 0).length
    })

    const totalWords = computed(() => {
      return props.chapters.reduce((total, chapter) => {
        return total + (chapter.content ? chapter.content.length : 0)
      }, 0)
    })

    return {
      completedChapters,
      totalWords
    }
  }
}
</script>

<style scoped>
.progress-tracker {
  border: 1px solid #eee;
  padding: 15px;
  border-radius: 8px;
  background: white;
}

.progress-tracker h3 {
  margin-bottom: 15px;
  color: #2c3e50;
}

.chapters-list {
  max-height: 300px;
  overflow-y: auto;
}

.chapter-item {
  display: flex;
  justify-content: space-between;
  padding: 8px 12px;
  border-bottom: 1px solid #eee;
  transition: background-color 0.3s;
}

.chapter-item.active {
  background-color: #e8f5e8;
  border-left: 3px solid #4CAF50;
}

.chapter-item:last-child {
  border-bottom: none;
}

.chapter-number {
  font-weight: 500;
}

.chapter-status {
  font-size: 0.8rem;
  color: #666;
}

.progress-stats {
  margin-top: 15px;
  padding-top: 15px;
  border-top: 1px solid #eee;
  font-size: 0.9rem;
  color: #666;
}

.progress-stats p {
  margin-bottom: 5px;
}
</style>
