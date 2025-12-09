<!-- src/views/Reader.vue -->
<template>
  <div class="reader">
    <div class="reader-header">
      <h1>{{ book.title }}</h1>
      <div class="reader-controls">
        <select v-model="readingMode" @change="changeReadingMode">
          <option value="immersive">沉浸模式</option>
          <option value="development">发展模式</option>
          <option value="creation">创作模式</option>
        </select>
        <button v-if="readingMode === 'creation'" @click="toggleCreation">
          {{ isCreating ? '保存创作' : '开始创作' }}
        </button>
      </div>
    </div>

    <div class="reader-content">
      <ImmersiveReader
          v-if="readingMode === 'immersive'"
          :book="book"
      />
      <DevelopmentReader
          v-else-if="readingMode === 'development'"
          :book="book"
      />
      <CreationReader
          v-else
          :book="book"
          :is-creating="isCreating"
          @create-version="createNewVersion"
      />
    </div>
  </div>
</template>

<script>
import {onMounted, ref} from 'vue'
import {useRoute} from 'vue-router'
import ImmersiveReader from '../components/reader/ImmersiveReader.vue'
import DevelopmentReader from '../components/reader/DevelopmentReader.vue'
import CreationReader from '../components/reader/CreationReader.vue'

export default {
  name: 'Reader',
  components: {
    ImmersiveReader,
    DevelopmentReader,
    CreationReader
  },
  setup() {
    const route = useRoute()
    const bookId = route.params.bookId
    const readingMode = ref('immersive')
    const isCreating = ref(false)
    const book = ref({
      title: '',
      chapters: [],
      version: 'v1'
    })

    // 模拟获取书籍数据
    onMounted(() => {
      // 实际项目中这里会调用API获取书籍数据
      book.value = {
        title: '示例书籍',
        chapters: [
          {
            title: '第一章',
            content: '这是第一章的内容...',
            plotDecisions: [
              {type: 'system', option: '主角发现了一个秘密', timestamp: '2023-01-01T00:00:00Z'}
            ],
            keywords: ['秘密', '发现']
          }
        ],
        version: 'v1'
      }
    })

    const changeReadingMode = () => {
      isCreating.value = false
    }

    const toggleCreation = () => {
      isCreating.value = !isCreating.value
    }

    const createNewVersion = (newContent) => {
      // 创建新版本
      // 实际项目中这里会调用API创建新版本
      alert(`已创建新版本，基于${book.value.version}的v2版本`)
      book.value.version = 'v2'
      isCreating.value = false
    }

    return {
      bookId,
      readingMode,
      isCreating,
      book,
      changeReadingMode,
      toggleCreation,
      createNewVersion
    }
  }
}
</script>

<style scoped>
.reader {
  max-width: 1000px;
  margin: 0 auto;
}

.reader-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
}

.reader-controls {
  display: flex;
  gap: 10px;
}

.reader-controls select {
  padding: 5px 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.reader-controls button {
  padding: 5px 10px;
  background-color: #2196F3;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>
