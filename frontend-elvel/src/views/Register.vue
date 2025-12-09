<!-- src/views/Register.vue -->
<template>
  <div class="register">
    <div class="container">
      <div class="register-card">
        <h1>注册</h1>
        <form @submit.prevent="handleRegister" class="register-form">
          <div class="form-group">
            <label for="username">用户名</label>
            <input
                id="username"
                v-model="registerForm.username"
                type="text"
                required
                placeholder="请输入用户名"
                @blur="validateUsername"
            >
            <div v-if="usernameError" class="field-error">{{ usernameError }}</div>
          </div>

          <div class="form-group">
            <label for="name">姓名</label>
            <input
                id="name"
                v-model="registerForm.name"
                type="text"
                required
                placeholder="请输入您的真实姓名"
                @blur="validateName"
            >
            <div v-if="nameError" class="field-error">{{ nameError }}</div>
          </div>

          <div class="form-group">
            <label for="email">邮箱</label>
            <input
                id="email"
                v-model="registerForm.email"
                type="email"
                required
                placeholder="请输入邮箱"
                @blur="validateEmail"
            >
            <div v-if="emailError" class="field-error">{{ emailError }}</div>
          </div>

          <div class="form-group">
            <label for="phone">电话</label>
            <input
                id="phone"
                v-model="registerForm.phone"
                type="tel"
                required
                placeholder="请输入手机号码"
                @blur="validatePhone"
            >
            <div v-if="phoneError" class="field-error">{{ phoneError }}</div>
          </div>

          <div class="form-group">
            <label for="password">密码</label>
            <input
                id="password"
                v-model="registerForm.password"
                type="password"
                required
                placeholder="请输入密码"
                @blur="validatePassword"
            >
            <div v-if="passwordError" class="field-error">{{ passwordError }}</div>
          </div>

          <div class="form-group">
            <label for="confirmPassword">确认密码</label>
            <input
                id="confirmPassword"
                v-model="registerForm.confirmPassword"
                type="password"
                required
                placeholder="请再次输入密码"
                @blur="validateConfirmPassword"
            >
            <div v-if="confirmPasswordError" class="field-error">{{ confirmPasswordError }}</div>
          </div>

          <div class="form-group">
            <label class="agree-terms">
              <input type="checkbox" v-model="registerForm.agreeTerms" required>
              <span>我已阅读并同意 <a href="#" @click.prevent>服务条款</a> 和 <a href="#" @click.prevent>隐私政策</a></span>
            </label>
          </div>

          <button
              type="submit"
              class="btn btn-primary btn-block"
              :disabled="loading || !isFormValid"
          >
            {{ loading ? '注册中...' : '注册' }}
          </button>
        </form>

        <div class="register-footer">
          <p>已有账号？ <router-link to="/login">立即登录</router-link></p>
        </div>

        <div v-if="error" class="error-message">
          {{ error }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { authApi } from '../utils/api'

export default {
  name: 'Register',
  setup() {
    const store = useStore()
    const router = useRouter()

    const loading = ref(false)
    const error = ref('')

    const registerForm = reactive({
      username: '',
      name: '',
      email: '',
      phone: '',
      password: '',
      confirmPassword: '',
      agreeTerms: false
    })

    const usernameError = ref('')
    const nameError = ref('')
    const emailError = ref('')
    const phoneError = ref('')
    const passwordError = ref('')
    const confirmPasswordError = ref('')

    const validateUsername = () => {
      if (!registerForm.username) {
        usernameError.value = '用户名不能为空'
      } else if (registerForm.username.length < 3) {
        usernameError.value = '用户名至少3个字符'
      } else {
        usernameError.value = ''
      }
    }

    const validateName = () => {
      if (!registerForm.name) {
        nameError.value = '姓名不能为空'
      } else {
        nameError.value = ''
      }
    }

    const validateEmail = () => {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
      if (!registerForm.email) {
        emailError.value = '邮箱不能为空'
      } else if (!emailRegex.test(registerForm.email)) {
        emailError.value = '请输入有效的邮箱地址'
      } else {
        emailError.value = ''
      }
    }

    const validatePhone = () => {
      const phoneRegex = /^1[3-9]\d{9}$/
      if (!registerForm.phone) {
        phoneError.value = '电话不能为空'
      } else if (!phoneRegex.test(registerForm.phone)) {
        phoneError.value = '请输入有效的手机号码'
      } else {
        phoneError.value = ''
      }
    }

    const validatePassword = () => {
      if (!registerForm.password) {
        passwordError.value = '密码不能为空'
      } else if (registerForm.password.length < 6) {
        passwordError.value = '密码至少6个字符'
      } else {
        passwordError.value = ''
      }
    }

    const validateConfirmPassword = () => {
      if (!registerForm.confirmPassword) {
        confirmPasswordError.value = '请确认密码'
      } else if (registerForm.password !== registerForm.confirmPassword) {
        confirmPasswordError.value = '两次输入的密码不一致'
      } else {
        confirmPasswordError.value = ''
      }
    }

    const isFormValid = computed(() => {
      return registerForm.username &&
          registerForm.name &&
          registerForm.email &&
          registerForm.phone &&
          registerForm.password &&
          registerForm.confirmPassword &&
          registerForm.agreeTerms &&
          !usernameError.value &&
          !nameError.value &&
          !emailError.value &&
          !phoneError.value &&
          !passwordError.value &&
          !confirmPasswordError.value
    })

    const handleRegister = async () => {
      validateUsername()
      validateName()
      validateEmail()
      validatePhone()
      validatePassword()
      validateConfirmPassword()

      if (!isFormValid.value) {
        error.value = '请正确填写所有字段'
        return
      }

      loading.value = true
      error.value = ''

      try {
        // 调用实际注册API
        await authApi.register({
          username: registerForm.username,
          name: registerForm.name,
          email: registerForm.email,
          phone: registerForm.phone,
          password: registerForm.password
        })

        // 注册成功后跳转到登录页
        router.push({
          path: '/login',
          query: { message: '注册成功，请登录' }
        })

      } catch (err) {
        error.value = err.message || '注册失败，请稍后重试'
      } finally {
        loading.value = false
      }
    }

    return {
      registerForm,
      loading,
      error,
      usernameError,
      nameError,
      emailError,
      phoneError,
      passwordError,
      confirmPasswordError,
      isFormValid,
      validateUsername,
      validateName,
      validateEmail,
      validatePhone,
      validatePassword,
      validateConfirmPassword,
      handleRegister
    }
  }
}
</script>

<style scoped>
.register {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 80vh;
  padding: 2rem 0;
}

.register-card {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

.register-card h1 {
  text-align: center;
  margin-bottom: 2rem;
  color: #2c3e50;
}

.register-form {
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

.field-error {
  color: #c62828;
  font-size: 0.8rem;
  margin-top: 0.25rem;
}

.agree-terms {
  display: flex;
  align-items: flex-start;
  gap: 0.5rem;
  font-size: 0.9rem;
  cursor: pointer;
}

.agree-terms input {
  width: auto;
  margin-top: 0.25rem;
}

.agree-terms a {
  color: #4CAF50;
  text-decoration: none;
}

.agree-terms a:hover {
  text-decoration: underline;
}

.btn-block {
  width: 100%;
  padding: 0.75rem;
  font-size: 1rem;
}

.register-footer {
  text-align: center;
  padding-top: 1.5rem;
  border-top: 1px solid #eee;
}

.register-footer a {
  color: #4CAF50;
  text-decoration: none;
  font-weight: 500;
}

.register-footer a:hover {
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
  .register-card {
    margin: 1rem;
    padding: 1.5rem;
  }

  .agree-terms {
    font-size: 0.8rem;
  }
}
</style>
