<!-- src/App.vue -->
<template>
  <div id="app">
    <Header />
    <main class="main-content">
      <router-view />
    </main>
    <Footer />
  </div>
</template>

<script>
import Header from './components/common/Header.vue'
import Footer from './components/common/Footer.vue'
import { onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'App',
  components: {
    Header,
    Footer
  },
  setup() {
    const store = useStore()

    onMounted(() => {
      // 初始化认证状态
      store.dispatch('initializeAuth')
    })
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.main-content {
  flex: 1;
  padding: 20px;
}
</style>
