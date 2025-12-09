<!-- src/components/creator/PlotOptions.vue -->
<template>
  <div class="plot-options">
    <h3>剧情发展选择</h3>

    <div class="options-list">
      <div
          v-for="option in options"
          :key="option.id"
          class="option-item"
          @click="$emit('select-option', option)"
      >
        {{ option.text }}
      </div>
    </div>

    <div class="custom-option">
      <input
          v-model="customOptionText"
          placeholder="自定义剧情发展..."
          @keyup.enter="addCustomOption"
      />
      <button @click="addCustomOption">添加</button>
    </div>

    <div class="action-buttons">
      <button @click="$emit('rewrite')">重写该次生成</button>
      <button @click="$emit('next-chapter')">结束该章节并开始下一章节</button>
      <button @click="$emit('finish-book')">写完了</button>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'PlotOptions',
  props: {
    options: Array
  },
  emits: ['select-option', 'custom-option', 'rewrite', 'next-chapter', 'finish-book'],
  setup(props, { emit }) {
    const customOptionText = ref('')

    const addCustomOption = () => {
      if (customOptionText.value.trim()) {
        emit('custom-option', customOptionText.value.trim())
        customOptionText.value = ''
      }
    }

    return {
      customOptionText,
      addCustomOption
    }
  }
}
</script>

<style scoped>
.plot-options {
  border: 1px solid #eee;
  padding: 15px;
  border-radius: 8px;
}

.options-list {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
  margin-bottom: 15px;
}

.option-item {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.option-item:hover {
  background-color: #f5f5f5;
}

.custom-option {
  display: flex;
  margin-bottom: 15px;
}

.custom-option input {
  flex: 1;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px 0 0 4px;
}

.custom-option button {
  padding: 8px 15px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 0 4px 4px 0;
  cursor: pointer;
}

.action-buttons {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}

.action-buttons button {
  padding: 8px 15px;
  background-color: #2196F3;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.action-buttons button:last-child {
  background-color: #FF9800;
}
</style>
