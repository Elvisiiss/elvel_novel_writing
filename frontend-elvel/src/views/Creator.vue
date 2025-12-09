<!-- src/views/Creator.vue -->
<template>
  <div class="creator">
    <div v-if="!currentBook" class="book-init">
      <h2>开始创作新书</h2>
      <div class="input-group">
        <label for="bookTitle">书名：</label>
        <input
            id="bookTitle"
            v-model="bookTitle"
            type="text"
            placeholder="请输入书名"
        />
        <button @click="generateOutline" :disabled="!bookTitle">
          生成大纲
        </button>
      </div>
    </div>

    <div v-else class="book-creation">
      <BookOutline
          :outline="currentBook.outline"
          @update-outline="updateOutline"
      />

      <ChapterEditor
          :content="currentChapter.content"
          :chapter-title="currentChapter.title"
          @update-content="updateChapterContent"
      />

      <PlotOptions
          :options="plotOptions"
          @select-option="selectPlotOption"
          @custom-option="addCustomOption"
          @rewrite="rewriteContent"
          @next-chapter="nextChapter"
          @finish-book="finishBook"
      />

      <ProgressTracker
          :chapters="currentBook.chapters"
          :current-chapter="currentChapterIndex"
      />
    </div>
  </div>
</template>

<script>
import { ref, reactive, computed } from 'vue'
import BookOutline from '../components/creator/BookOutline.vue'
import ChapterEditor from '../components/creator/ChapterEditor.vue'
import PlotOptions from '../components/creator/PlotOptions.vue'
import ProgressTracker from '../components/creator/ProgressTracker.vue'

export default {
  name: 'Creator',
  components: {
    BookOutline,
    ChapterEditor,
    PlotOptions,
    ProgressTracker
  },
  setup() {
    const bookTitle = ref('')
    const currentBook = ref(null)
    const currentChapterIndex = ref(0)

    const plotOptions = ref([
      { id: 1, text: '主角发现了一个秘密' },
      { id: 2, text: '出现新的角色' },
      { id: 3, text: '情节发生转折' }
    ])

    const generateOutline = async () => {
      // 模拟API调用生成大纲
      // 实际项目中这里会调用后端API
      currentBook.value = {
        title: bookTitle.value,
        outline: `《${bookTitle.value}》的大纲：
        1. 开篇引入主角和背景
        2. 主角面临第一个挑战
        3. 情节发展，出现转折
        4. 高潮部分
        5. 结局`,
        chapters: [
          {
            title: '第一章',
            content: '',
            plotDecisions: [],
            keywords: []
          }
        ]
      }
    }

    const updateOutline = (newOutline) => {
      currentBook.value.outline = newOutline
    }

    const currentChapter = computed(() => {
      return currentBook.value ? currentBook.value.chapters[currentChapterIndex.value] : null
    })

    const updateChapterContent = (content) => {
      if (currentChapter.value) {
        currentChapter.value.content = content
      }
    }

    const selectPlotOption = (option) => {
      // 记录剧情选择并生成新内容
      currentChapter.value.plotDecisions.push({
        type: 'system',
        option: option.text,
        timestamp: new Date().toISOString()
      })

      // 模拟生成新内容
      generateNewContent(option)
    }

    const addCustomOption = (customOption) => {
      // 添加自定义剧情选项
      currentChapter.value.plotDecisions.push({
        type: 'custom',
        option: customOption,
        timestamp: new Date().toISOString()
      })

      // 生成基于自定义选项的内容
      generateNewContent({ text: customOption })
    }

    const generateNewContent = (option) => {
      // 模拟生成新内容
      // 实际项目中这里会调用AI生成API
      const newContent = `基于选择"${option.text}"，故事继续发展...`
      currentChapter.value.content += '\n\n' + newContent
    }

    const rewriteContent = () => {
      // 重写当前生成的内容
      currentChapter.value.content = currentChapter.value.content.split('\n\n').slice(0, -1).join('\n\n')
    }

    const nextChapter = () => {
      // 结束当前章节，开始新章节
      currentChapterIndex.value++
      currentBook.value.chapters.push({
        title: `第${currentChapterIndex.value + 1}章`,
        content: '',
        plotDecisions: [],
        keywords: []
      })
    }

    const finishBook = () => {
      // 完成书籍，跳转到发布页面
      // 实际项目中这里会保存书籍数据
      const bookId = 'book_' + Date.now() // 模拟书籍ID
      this.$router.push(`/publish/${bookId}`)
    }

    return {
      bookTitle,
      currentBook,
      currentChapterIndex,
      plotOptions,
      currentChapter,
      generateOutline,
      updateOutline,
      updateChapterContent,
      selectPlotOption,
      addCustomOption,
      rewriteContent,
      nextChapter,
      finishBook
    }
  }
}
</script>

<style scoped>
.creator {
  max-width: 1200px;
  margin: 0 auto;
}

.book-init {
  text-align: center;
  padding: 40px 20px;
}

.input-group {
  margin: 20px 0;
}

.input-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: bold;
}

.input-group input {
  padding: 10px;
  width: 300px;
  margin-right: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.input-group button {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.input-group button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.book-creation {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

@media (max-width: 768px) {
  .book-creation {
    grid-template-columns: 1fr;
  }
}
</style>
