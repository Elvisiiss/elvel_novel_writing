<!-- src/components/creator/BookOutline.vue -->
<template>
  <div class="book-outline">
    <h3>书籍大纲</h3>
    <textarea
        v-model="localOutline"
        @blur="updateOutline"
        rows="10"
    ></textarea>
  </div>
</template>

<script>
import { ref, watch } from 'vue'

export default {
  name: 'BookOutline',
  props: {
    outline: String
  },
  emits: ['update-outline'],
  setup(props, { emit }) {
    const localOutline = ref(props.outline)

    watch(() => props.outline, (newVal) => {
      localOutline.value = newVal
    })

    const updateOutline = () => {
      emit('update-outline', localOutline.value)
    }

    return {
      localOutline,
      updateOutline
    }
  }
}
</script>

<style scoped>
.book-outline {
  margin-bottom: 20px;
}

.book-outline h3 {
  margin-bottom: 10px;
}

.book-outline textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  resize: vertical;
}
</style>
