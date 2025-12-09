<!-- src/views/Library.vue -->
<template>
  <div class="library">
    <div class="container">
      <h1>书库</h1>
      <div class="search-bar">
        <input
            v-model="searchQuery"
            placeholder="搜索书籍..."
            type="text"
        />
      </div>

      <div class="books-grid">
        <div
            v-for="book in filteredBooks"
            :key="book.id"
            class="book-card"
            @click="$router.push(`/read/${book.id}`)"
        >
          <h3>{{ book.title }}</h3>
          <p class="author">作者: {{ book.author }}</p>
          <p class="description">{{ book.description }}</p>
          <div class="book-meta">
            <span class="version">{{ book.version }}</span>
            <span class="chapters">{{ book.chapters }}章</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'

export default {
  name: 'Library',
  setup() {
    const searchQuery = ref('')

    // 模拟书籍数据
    const books = ref([
      {
        id: '1',
        title: '星辰变',
        author: '我吃西红柿',
        description: '一部关于修真与成长的奇幻小说',
        version: 'v1',
        chapters: 12
      },
      {
        id: '2',
        title: '斗破苍穹',
        author: '天蚕土豆',
        description: '少年逆袭的修炼之路',
        version: 'v1',
        chapters: 8
      },
      {
        id: '3',
        title: '星辰变 v2',
        author: '读者创作',
        description: '基于星辰变的二次创作版本',
        version: 'v2',
        chapters: 6
      }
    ])

    const filteredBooks = computed(() => {
      if (!searchQuery.value) return books.value
      return books.value.filter(book =>
          book.title.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
          book.author.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
          book.description.toLowerCase().includes(searchQuery.value.toLowerCase())
      )
    })

    return {
      searchQuery,
      filteredBooks
    }
  }
}
</script>

<style scoped>
.library {
  padding: 2rem 0;
}

.search-bar {
  margin: 2rem 0;
}

.search-bar input {
  max-width: 400px;
  padding: 10px 15px;
  border: 1px solid #ddd;
  border-radius: 25px;
  font-size: 16px;
}

.books-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 2rem;
}

.book-card {
  border: 1px solid #eee;
  border-radius: 8px;
  padding: 1.5rem;
  cursor: pointer;
  transition: all 0.3s;
  background: white;
}

.book-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0,0,0,0.1);
}

.book-card h3 {
  margin-bottom: 0.5rem;
  color: #2c3e50;
}

.author {
  color: #666;
  font-size: 0.9rem;
  margin-bottom: 1rem;
}

.description {
  color: #555;
  margin-bottom: 1rem;
  line-height: 1.4;
}

.book-meta {
  display: flex;
  justify-content: space-between;
  font-size: 0.8rem;
  color: #888;
}

.version {
  background: #e3f2fd;
  padding: 2px 8px;
  border-radius: 4px;
}

.chapters {
  background: #f3e5f5;
  padding: 2px 8px;
  border-radius: 4px;
}
</style>
