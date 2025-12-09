<!-- src/views/Publish.vue -->
<template>
  <div class="publish">
    <div class="container">
      <div v-if="!isPublished" class="publish-form">
        <h1>发布书籍</h1>
        <div class="book-info">
          <h2>{{ book.title }}</h2>
          <p class="outline-preview">{{ book.outline }}</p>
        </div>

        <div class="form-group">
          <label for="description">书籍简介：</label>
          <textarea
              id="description"
              v-model="book.description"
              rows="4"
              placeholder="请输入书籍简介..."
          ></textarea>
        </div>

        <div class="form-group">
          <label for="tags">标签：</label>
          <input
              id="tags"
              v-model="book.tags"
              placeholder="请输入标签，用逗号分隔"
          />
        </div>

        <div class="publish-actions">
          <button @click="publishBook" class="btn btn-primary">发布</button>
          <button @click="$router.back()" class="btn btn-secondary">返回编辑</button>
        </div>
      </div>

      <div v-else class="publish-success">
        <h1>发布成功！</h1>
        <p>您的书籍《{{ book.title }}》已成功发布到平台</p>
        <div class="success-actions">
          <router-link :to="`/read/${bookId}`" class="btn btn-primary">阅读书籍</router-link>
          <router-link to="/library" class="btn btn-secondary">浏览书库</router-link>
          <router-link to="/create" class="btn btn-warning">继续创作</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'

export default {
  name: 'Publish',
  setup() {
    const route = useRoute()
    const bookId = route.params.bookId
    const isPublished = ref(false)

    const book = ref({
      title: '',
      outline: '',
      description: '',
      tags: '',
      chapters: []
    })

    onMounted(() => {
      // 模拟从本地存储或状态管理获取书籍数据
      const savedBook = localStorage.getItem(`book_${bookId}`)
      if (savedBook) {
        book.value = { ...book.value, ...JSON.parse(savedBook) }
      }
    })

    const publishBook = () => {
      // 模拟发布过程
      // 实际项目中这里会调用API
      isPublished.value = true
      // 保存发布状态
      localStorage.setItem(`book_published_${bookId}`, 'true')
    }

    return {
      bookId,
      isPublished,
      book,
      publishBook
    }
  }
}
</script>

<style scoped>
.publish {
  padding: 2rem 0;
}

.publish-form,
.publish-success {
  max-width: 600px;
  margin: 0 auto;
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.book-info {
  margin-bottom: 2rem;
  padding-bottom: 1rem;
  border-bottom: 1px solid #eee;
}

.outline-preview {
  color: #666;
  font-style: italic;
  margin-top: 1rem;
}

.publish-actions,
.success-actions {
  display: flex;
  gap: 1rem;
  margin-top: 2rem;
}

.publish-success {
  text-align: center;
}

.publish-success h1 {
  color: #4CAF50;
  margin-bottom: 1rem;
}
</style>
