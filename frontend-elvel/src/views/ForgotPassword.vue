<!-- src/views/ForgotPassword.vue -->
<template>
  <div class="forgot-password">
    <div class="container">
      <div class="forgot-password-card">
        <h1>忘记密码</h1>
        <p class="description">请输入您的个人信息以重置密码</p>

        <form @submit.prevent="handleResetPassword" class="forgot-password-form">
          <div class="form-group">
            <label for="name">姓名</label>
            <input
                id="name"
                v-model="resetForm.name"
                type="text"
                required
                placeholder="请输入您的姓名"
                @blur="validateName"
            >
            <div v-if="nameError" class="field-error">{{ nameError }}</div>
          </div>

          <div class="form-group">
            <label for="email">邮箱</label>
            <input
                id="email"
                v-model="resetForm.email"
                type="email"
                required
                placeholder="请输入您的邮箱"
                @blur="validateEmail"
            >
            <div v-if="emailError" class="field-error">{{ emailError }}</div>
          </div>

          <div class="form-group">
            <label for="phone">电话</label>
            <input
                id="phone"
                v-model="resetForm.phone"
                type="tel"
                required
                placeholder="请输入您的手机号码"
                @blur="validatePhone"
            >
            <div v-if="phoneError" class="field-error">{{ phoneError }}</div>
          </div>

          <div class="form-group">
            <label for="newPassword">新密码</label>
            <input
                id="newPassword"
                v-model="resetForm.newPassword"
                type="password"
                required
                placeholder="请输入新密码"
                @blur="validateNewPassword"
            >
            <div v-if="newPasswordError" class="field-error">{{ newPasswordError }}</div>
          </div>

          <div class="form-group">
            <label for="confirmPassword">确认新密码</label>
            <input
                id="confirmPassword"
                v-model="resetForm.confirmPassword"
                type="password"
                required
                placeholder="请再次输入新密码"
                @blur="validateConfirmPassword"
            >
            <div v-if="confirmPasswordError" class="field-error">{{ confirmPasswordError }}</div>
          </div>

          <button
              type="submit"
              class="btn btn-primary btn-block"
              :disabled="loading || !isFormValid"
          >
            {{ loading ? '重置中...' : '重置密码' }}
          </button>
        </form>

        <div class="form-footer">
          <router-link to="/login" class="back-to-login">返回登录</router-link>
        </div>

        <div v-if="successMessage" class="success-message">
          {{ successMessage }}
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
import { useRouter } from 'vue-router'
import { authApi } from '../utils/api'

export default {
  name: 'ForgotPassword',
  setup() {
    const router = useRouter()

    const loading = ref(false)
    const error = ref('')
    const successMessage = ref('')

    const resetForm = reactive({
      name: '',
      email: '',
      phone: '',
      newPassword: '',
      confirmPassword: ''
    })

    const nameError = ref('')
    const emailError = ref('')
    const phoneError = ref('')
    const newPasswordError = ref('')
    const confirmPasswordError = ref('')

    const validateName = () => {
      if (!resetForm.name) {
        nameError.value = '姓名不能为空'
      } else {
        nameError.value = ''
      }
    }

    const validateEmail = () => {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
      if (!resetForm.email) {
        emailError.value = '邮箱不能为空'
      } else if (!emailRegex.test(resetForm.email)) {
        emailError.value = '请输入有效的邮箱地址'
      } else {
        emailError.value = ''
      }
    }

    const validatePhone = () => {
      const phoneRegex = /^1[3-9]\d{9}$/
      if (!resetForm.phone) {
        phoneError.value = '电话不能为空'
      } else if (!phoneRegex.test(resetForm.phone)) {
        phoneError.value = '请输入有效的手机号码'
      } else {
        phoneError.value = ''
      }
    }

    const validateNewPassword = () => {
      if (!resetForm.newPassword) {
        newPasswordError.value = '新密码不能为空'
      } else if (resetForm.newPassword.length < 6) {
        newPasswordError.value = '新密码至少6个字符'
      } else {
        newPasswordError.value = ''
      }
    }

    const validateConfirmPassword = () => {
      if (!resetForm.confirmPassword) {
        confirmPasswordError.value = '请确认新密码'
      } else if (resetForm.newPassword !== resetForm.confirmPassword) {
        confirmPasswordError.value = '两次输入的密码不一致'
      } else {
        confirmPasswordError.value = ''
      }
    }

    const isFormValid = computed(() => {
      return resetForm.name &&
          resetForm.email &&
          resetForm.phone &&
          resetForm.newPassword &&
          resetForm.confirmPassword &&
          !nameError.value &&
          !emailError.value &&
          !phoneError.value &&
          !newPasswordError.value &&
          !confirmPasswordError.value
    })

    const handleResetPassword = async () => {
      validateName()
      validateEmail()
      validatePhone()
      validateNewPassword()
      validateConfirmPassword()

      if (!isFormValid.value) {
        error.value = '请正确填写所有字段'
        return
      }

      loading.value = true
      error.value = ''
      successMessage.value = ''

      try {
        // 调用忘记密码API
        await authApi.forgotPassword({
          name: resetForm.name,
          email: resetForm.email,
          phone: resetForm.phone,
          newPassword: resetForm.newPassword
        })

        successMessage.value = '密码重置成功，请使用新密码登录'

        // 3秒后跳转到登录页
        setTimeout(() => {
          router.push('/login')
        }, 3000)

      } catch (err) {
        error.value = err.message || '密码重置失败，请检查您的信息'
      } finally {
        loading.value = false
      }
    }

    return {
      resetForm,
      loading,
      error,
      successMessage,
      nameError,
      emailError,
      phoneError,
      newPasswordError,
      confirmPasswordError,
      isFormValid,
      validateName,
      validateEmail,
      validatePhone,
      validateNewPassword,
      validateConfirmPassword,
      handleResetPassword
    }
  }
}
</script>

<style scoped>
.forgot-password {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 80vh;
  padding: 2rem 0;
}

.forgot-password-card {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

.forgot-password-card h1 {
  text-align: center;
  margin-bottom: 1rem;
  color: #2c3e50;
}

.description {
  text-align: center;
  color: #666;
  margin-bottom: 2rem;
}

.forgot-password-form {
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

.btn-block {
  width: 100%;
  padding: 0.75rem;
  font-size: 1rem;
}

.form-footer {
  text-align: center;
  padding-top: 1.5rem;
  border-top: 1px solid #eee;
}

.back-to-login {
  color: #4CAF50;
  text-decoration: none;
  font-weight: 500;
}

.back-to-login:hover {
  text-decoration: underline;
}

.success-message {
  background: #e8f5e8;
  color: #2e7d32;
  padding: 0.75rem;
  border-radius: 4px;
  margin-top: 1rem;
  text-align: center;
}

.error-message {
  background: #ffebee;
  color: #c62828;
  padding: 0.75rem;
  border-radius: 4px;
  margin-top: 1rem;
  text-align: center;
}

@media (max-width: 480px) {
  .forgot-password-card {
    margin: 1rem;
    padding: 1.5rem;
  }
}
</style>
