<!-- src/components/reader/ImmersiveReader.vue -->
<template>
  <div class="immersive-reader">
    <div
        v-for="(chapter, index) in book.chapters"
        :key="index"
        class="chapter"
    >
      <h2>{{ chapter.title }}</h2>
      <div class="chapter-content">
        {{ chapter.content }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ImmersiveReader',
  props: {
    book: Object
  }
}
</script>

<style scoped>
.immersive-reader {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  line-height: 1.8;
}

.chapter {
  margin-bottom: 3rem;
}

.chapter:last-child {
  margin-bottom: 0;
}

.chapter h2 {
  color: #2c3e50;
  margin-bottom: 1.5rem;
  padding-bottom: 0.5rem;
  border-bottom: 2px solid #eee;
}

.chapter-content {
  white-space: pre-wrap;
  font-size: 16px;
  color: #333;
}
</style>
