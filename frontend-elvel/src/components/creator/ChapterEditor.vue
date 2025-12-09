<!-- src/components/creator/ChapterEditor.vue -->
<template>
  <div class="chapter-editor">
    <h3>{{ chapterTitle }}</h3>
    <textarea
        v-model="localContent"
        @input="updateContent"
        placeholder="开始编写本章内容..."
        rows="15"
    ></textarea>
    <div class="editor-tools">
      <button @click="addKeyword" class="btn btn-secondary">添加关键词</button>
      <input
          v-model="newKeyword"
          @keyup.enter="addKeyword"
          placeholder="输入关键词"
          class="keyword-input"
      />
      <div class="keywords">
        <span
            v-for="(keyword, index) in keywords"
            :key="index"
            class="keyword-tag"
        >
          {{ keyword }}
          <span @click="removeKeyword(index)" class="remove-keyword">×</span>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, watch } from 'vue'

export default {
  name: 'ChapterEditor',
  props: {
    content: String,
    chapterTitle: String
  },
  emits: ['update-content'],
  setup(props, { emit }) {
    const localContent = ref(props.content || '')
    const newKeyword = ref('')
    const keywords = ref([])

    watch(() => props.content, (newVal) => {
      localContent.value = newVal
    })

    const updateContent = () => {
      emit('update-content', localContent.value)
    }

    const addKeyword = () => {
      if (newKeyword.value.trim() && !keywords.value.includes(newKeyword.value.trim())) {
        keywords.value.push(newKeyword.value.trim())
        newKeyword.value = ''
      }
    }

    const removeKeyword = (index) => {
      keywords.value.splice(index, 1)
    }

    return {
      localContent,
      newKeyword,
      keywords,
      updateContent,
      addKeyword,
      removeKeyword
    }
  }
}
</script>

<style scoped>
.chapter-editor {
  margin-bottom: 20px;
}

.chapter-editor h3 {
  margin-bottom: 10px;
  color: #2c3e50;
}

.chapter-editor textarea {
  width: 100%;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  resize: vertical;
  font-family: inherit;
  line-height: 1.6;
}

.editor-tools {
  margin-top: 15px;
}

.keyword-input {
  margin-left: 10px;
  padding: 5px 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  width: 150px;
}

.keywords {
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;
  gap: 5px;
}

.keyword-tag {
  background: #e3f2fd;
  padding: 3px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
  display: flex;
  align-items: center;
  gap: 5px;
}

.remove-keyword {
  cursor: pointer;
  font-weight: bold;
  color: #666;
}

.remove-keyword:hover {
  color: #f44336;
}
</style>
