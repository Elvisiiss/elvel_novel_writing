<!-- src/components/reader/CreationReader.vue -->
<template>
  <div class="creation-reader">
    <!-- 显示原作内容 -->
    <DevelopmentReader :book="book" />

    <!-- 创作面板 -->
    <div v-if="isCreating" class="creation-panel">
      <h3>基于此版本创作 ({{ book.version }})</h3>
      <p class="creation-notice">您正在基于原版创作新版本，新版本将标记为 v{{ nextVersion }}</p>

      <div class="creation-controls">
        <button @click="saveCreation" class="btn btn-primary">保存为新版本</button>
        <button @click="cancelCreation" class="btn btn-secondary">取消</button>
      </div>

      <!-- 这里可以添加创作编辑器 -->
      <div class="creation-editor">
        <h4>添加您的创作内容</h4>
        <textarea
            v-model="newContent"
            placeholder="在这里添加您的创作内容..."
            rows="6"
        ></textarea>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import DevelopmentReader from './DevelopmentReader.vue'

export default {
  name: 'CreationReader',
  components: {
    DevelopmentReader
  },
  props: {
    book: Object,
    isCreating: Boolean
  },
  emits: ['create-version'],
  setup(props, { emit }) {
    const newContent = ref('')

    const nextVersion = computed(() => {
      const currentVersion = props.book.version
      const versionNumber = parseInt(currentVersion.replace('v', '')) || 1
      return versionNumber + 1
    })

    const saveCreation = () => {
      if (newContent.value.trim()) {
        emit('create-version', {
          originalVersion: props.book.version,
          newContent: newContent.value,
          newVersion: `v${nextVersion.value}`
        })
        newContent.value = ''
      }
    }

    const cancelCreation = () => {
      newContent.value = ''
      // 通知父组件取消创作模式
      emit('create-version', null)
    }

    return {
      newContent,
      nextVersion,
      saveCreation,
      cancelCreation
    }
  }
}
</script>

<style scoped>
.creation-panel {
  margin-top: 2rem;
  padding: 1.5rem;
  background: #f8f9fa;
  border: 2px dashed #dee2e6;
  border-radius: 8px;
}

.creation-notice {
  color: #666;
  margin-bottom: 1rem;
  font-style: italic;
}

.creation-controls {
  margin-bottom: 1rem;
}

.creation-editor textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  resize: vertical;
}
</style>
