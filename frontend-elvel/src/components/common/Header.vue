<!-- src/components/common/Header.vue -->
<template>
  <header class="header">
    <div class="container">
      <div class="header-content">
        <h1 class="logo" @click="$router.push('/')">小说创作平台</h1>
        <nav class="nav">
          <router-link to="/" class="nav-link">首页</router-link>
          <router-link to="/create" class="nav-link" v-if="isAuthenticated">创作</router-link>
          <router-link to="/library" class="nav-link">书库</router-link>
          <router-link to="/about" class="nav-link">关于</router-link>
        </nav>
        <div class="user-section">
          <div v-if="isAuthenticated" class="user-menu">
            <button class="user-btn" @click="toggleDropdown">
              <span class="user-name">{{ user?.username }}</span>
              <span class="dropdown-arrow">▼</span>
            </button>
            <div v-if="showDropdown" class="dropdown-menu">
              <router-link to="/profile" class="dropdown-item" @click="closeDropdown">
                <i class="icon">👤</i>个人信息
              </router-link>
              <button class="dropdown-item" @click="logout">
                <i class="icon">🚪</i>退出登录
              </button>
            </div>
          </div>
          <div v-else class="auth-buttons">
            <router-link to="/login" class="btn btn-outline">登录</router-link>
            <router-link to="/register" class="btn btn-primary">注册</router-link>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
import { ref, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'Header',
  setup() {
    const store = useStore()
    const router = useRouter()
    const showDropdown = ref(false)

    const isAuthenticated = computed(() => store.getters.isAuthenticated)
    const user = computed(() => store.getters.currentUser)

    const toggleDropdown = () => {
      showDropdown.value = !showDropdown.value
    }

    const closeDropdown = () => {
      showDropdown.value = false
    }

    const logout = () => {
      store.dispatch('logout')
      closeDropdown()
      router.push('/')
    }

    // 点击外部关闭下拉菜单
    const handleClickOutside = (event) => {
      if (!event.target.closest('.user-menu')) {
        showDropdown.value = false
      }
    }

    // 添加全局点击事件监听
    document.addEventListener('click', handleClickOutside)

    return {
      isAuthenticated,
      user,
      showDropdown,
      toggleDropdown,
      closeDropdown,
      logout
    }
  }
}
</script>

<style scoped>
.header {
  background-color: #2c3e50;
  color: white;
  padding: 1rem 0;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  position: relative;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo {
  font-size: 1.5rem;
  font-weight: bold;
  cursor: pointer;
  margin: 0;
}

.nav {
  display: flex;
  gap: 2rem;
}

.nav-link {
  color: white;
  text-decoration: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.nav-link:hover,
.nav-link.router-link-active {
  background-color: rgba(255,255,255,0.1);
}

.user-section {
  position: relative;
}

.auth-buttons {
  display: flex;
  gap: 1rem;
}

.btn-outline {
  background: transparent;
  border: 1px solid white;
  color: white;
}

.btn-outline:hover {
  background: rgba(255,255,255,0.1);
}

.user-menu {
  position: relative;
}

.user-btn {
  background: rgba(255,255,255,0.1);
  border: none;
  color: white;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  transition: background-color 0.3s;
}

.user-btn:hover {
  background: rgba(255,255,255,0.2);
}

.user-name {
  font-weight: 500;
}

.dropdown-arrow {
  font-size: 0.8rem;
}

.dropdown-menu {
  position: absolute;
  top: 100%;
  right: 0;
  background: white;
  border-radius: 4px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  min-width: 150px;
  margin-top: 0.5rem;
  z-index: 1000;
}

.dropdown-item {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1rem;
  color: #333;
  text-decoration: none;
  border: none;
  background: none;
  width: 100%;
  text-align: left;
  cursor: pointer;
  transition: background-color 0.3s;
}

.dropdown-item:hover {
  background-color: #f5f5f5;
}

.dropdown-item .icon {
  font-size: 1rem;
}

@media (max-width: 768px) {
  .header-content {
    flex-direction: column;
    gap: 1rem;
  }

  .nav {
    gap: 1rem;
  }

  .auth-buttons {
    flex-direction: column;
    gap: 0.5rem;
  }
}
</style>
