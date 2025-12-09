<!-- src/views/Login.vue -->
<template>
  <div class="login">
    <div class="container">
      <div class="login-card">
        <h1>登录</h1>

        <div v-if="successMessage" class="success-message">
          {{ successMessage }}
        </div>

        <form @submit.prevent="handleLogin" class="login-form">
          <div class="form-group">
            <label for="username">用户名或邮箱</label>
            <input
                id="username"
                v-model="loginForm.username"
                type="text"
                required
                placeholder="请输入用户名或邮箱"
            >
          </div>

          <div class="form-group">
            <label for="password">密码</label>
            <input
                id="password"
                v-model="loginForm.password"
                type="password"
                required
                placeholder="请输入密码"
            >
          </div>

          <div class="form-options">
            <label class="remember-me">
              <input type="checkbox" v-model="loginForm.rememberMe">
              <span>记住我</span>
            </label>
            <router-link to="/forgot-password" class="forgot-password">
              忘记密码？
            </router-link>
          </div>

          <button
              type="submit"
              class="btn btn-primary btn-block"
              :disabled="loading"
          >
            {{ loading ? '登录中...' : '登录' }}
          </button>
        </form>

        <div class="login-footer">
          <p>还没有账号？ <router-link to="/register">立即注册</router-link></p>
        </div>

        <div v-if="error" class="error-message">
          {{ error }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { authApi } from '../utils/api'

export default {
  name: 'Login',
  setup() {
    const store = useStore()
    const router = useRouter()
    const route = router.currentRoute

    const loading = ref(false)
    const error = ref('')
    const successMessage = ref('')

    const loginForm = reactive({
      username: '',
      password: '',
      rememberMe: false
    })

    // 检查URL参数中的消息
    if (route.value.query.message) {
      successMessage.value = route.value.query.message
      // 3秒后自动清除消息
      setTimeout(() => {
        successMessage.value = ''
      }, 3000)
    }

    const handleLogin = async () => {
      if (!loginForm.username || !loginForm.password) {
        error.value = '请输入用户名和密码'
        return
      }

      loading.value = true
      error.value = ''
      successMessage.value = ''

      try {
        // 调用实际登录API
        const response = await authApi.login(loginForm.username, loginForm.password)

        if (response.success && response.data) {
          const userData = {
            id: response.data.user.id,
            username: response.data.user.username,
            email: response.data.user.email,
            token: response.data.token,
            avatar: '',
            bio: ''
          }

          // 保存用户信息到store
          store.dispatch('login', userData)

          // 跳转到首页或之前访问的页面
          const redirect = route.value.query.redirect || '/'
          router.push(redirect)
        } else {
          error.value = response.message || '登录失败'
        }

      } catch (err) {
        error.value = err.message || '登录失败，请检查用户名和密码'
      } finally {
        loading.value = false
      }
    }

    return {
      loginForm,
      loading,
      error,
      successMessage,
      handleLogin
    }
  }
}
</script>

<style scoped>
.success-message {
  background: #e8f5e8;
  color: #2e7d32;
  padding: 0.75rem;
  border-radius: 4px;
  margin-bottom: 1rem;
  text-align: center;
}

.login {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 80vh;
  padding: 2rem 0;
}

.login-card {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

.login-card h1 {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50;
}

.login-form {
  margin-bottom: 1.5rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 500;
  color: #555;
}

.form-group input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
  transition: border-color 0.3s;
}

.form-group input:focus {
  outline: none;
  border-color: #4CAF50;
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
  font-size: 0.9rem;
}

.remember-me {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  cursor: pointer;
}

.remember-me input {
  margin: 0;
}

.forgot-password {
  color: #4CAF50;
  text-decoration: none;
}

.forgot-password:hover {
  text-decoration: underline;
}

.btn-block {
  width: 100%;
  padding: 0.75rem;
  font-size: 1rem;
}

.login-footer {
  text-align: center;
  padding-top: 1.5rem;
  border-top: 1px solid #eee;
}

.login-footer a {
  color: #4CAF50;
  text-decoration: none;
  font-weight: 500;
}

.login-footer a:hover {
  text-decoration: underline;
}

.error-message {
  background: #ffebee;
  color: #c62828;
  padding: 0.75rem;
  border-radius: 4px;
  margin-top: 1rem;
  text-align: center;
  font-size: 0.9rem;
}

@media (max-width: 480px) {
  .login-card {
    margin: 1rem;
    padding: 1.5rem;
  }

  .form-options {
    flex-direction: column;
    gap: 1rem;
    align-items: flex-start;
  }
}
</style>
