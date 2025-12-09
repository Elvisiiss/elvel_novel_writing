<!-- src/views/Profile.vue -->
<template>
  <div class="profile">
    <div class="container">
      <div class="profile-card">
        <h1>个人信息</h1>

        <div class="profile-content">
          <!-- 头像上传 -->
          <div class="avatar-section">
            <div class="avatar-upload">
              <div class="avatar-preview">
                <img
                    v-if="user.avatar"
                    :src="user.avatar"
                    :alt="user.username"
                    class="avatar"
                >
                <div v-else class="avatar-placeholder">
                  {{ user.username?.charAt(0)?.toUpperCase() }}
                </div>
              </div>
              <button
                  type="button"
                  class="btn btn-outline btn-sm"
                  @click="triggerAvatarUpload"
              >
                更换头像
              </button>
              <input
                  ref="avatarInput"
                  type="file"
                  accept="image/*"
                  @change="handleAvatarUpload"
                  style="display: none"
              >
            </div>
          </div>

          <!-- 个人信息表单 -->
          <form @submit.prevent="handleSave" class="profile-form">
            <div class="form-row">
              <div class="form-group">
                <label for="username">用户名</label>
                <input
                    id="username"
                    v-model="profileForm.username"
                    type="text"
                    required
                >
              </div>

              <div class="form-group">
                <label for="email">邮箱</label>
                <input
                    id="email"
                    v-model="profileForm.email"
                    type="email"
                    required
                >
              </div>
            </div>

            <div class="form-group">
              <label for="bio">个人简介</label>
              <textarea
                  id="bio"
                  v-model="profileForm.bio"
                  rows="4"
                  placeholder="介绍一下自己..."
                  maxlength="200"
              ></textarea>
              <div class="char-count">{{ profileForm.bio?.length || 0 }}/200</div>
            </div>

            <div class="form-actions">
              <button
                  type="submit"
                  class="btn btn-primary"
                  :disabled="loading || !hasChanges"
              >
                {{ loading ? '保存中...' : '保存更改' }}
              </button>
              <button
                  type="button"
                  class="btn btn-secondary"
                  @click="resetForm"
                  :disabled="!hasChanges"
              >
                重置
              </button>
            </div>
          </form>

          <!-- 统计信息 -->
          <div class="stats-section">
            <h3>创作统计</h3>
            <div class="stats-grid">
              <div class="stat-item">
                <div class="stat-number">{{ userStats.booksCreated }}</div>
                <div class="stat-label">创作书籍</div>
              </div>
              <div class="stat-item">
                <div class="stat-number">{{ userStats.chaptersWritten }}</div>
                <div class="stat-label">撰写章节</div>
              </div>
              <div class="stat-item">
                <div class="stat-number">{{ userStats.wordsWritten }}</div>
                <div class="stat-label">总字数</div>
              </div>
              <div class="stat-item">
                <div class="stat-number">{{ userStats.versionsCreated }}</div>
                <div class="stat-label">创建版本</div>
              </div>
            </div>
          </div>
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
import { ref, reactive, computed, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'Profile',
  setup() {
    const store = useStore()
    const avatarInput = ref(null)

    const loading = ref(false)
    const error = ref('')
    const successMessage = ref('')

    const user = computed(() => store.getters.currentUser)

    const profileForm = reactive({
      username: '',
      email: '',
      bio: ''
    })

    const userStats = ref({
      booksCreated: 3,
      chaptersWritten: 15,
      wordsWritten: '12.5k',
      versionsCreated: 2
    })

    const hasChanges = computed(() => {
      return profileForm.username !== user.value?.username ||
          profileForm.email !== user.value?.email ||
          profileForm.bio !== user.value?.bio
    })

    const initForm = () => {
      if (user.value) {
        profileForm.username = user.value.username || ''
        profileForm.email = user.value.email || ''
        profileForm.bio = user.value.bio || ''
      }
    }

    const resetForm = () => {
      initForm()
      error.value = ''
      successMessage.value = ''
    }

    const triggerAvatarUpload = () => {
      avatarInput.value?.click()
    }

    const handleAvatarUpload = (event) => {
      const file = event.target.files[0]
      if (file) {
        // 模拟头像上传
        const reader = new FileReader()
        reader.onload = (e) => {
          // 在实际项目中，这里会上传到服务器
          const userData = { ...user.value, avatar: e.target.result }
          store.dispatch('updateProfile', userData)
          successMessage.value = '头像更新成功'

          setTimeout(() => {
            successMessage.value = ''
          }, 3000)
        }
        reader.readAsDataURL(file)
      }
    }

    const handleSave = async () => {
      if (!hasChanges.value) return

      loading.value = true
      error.value = ''
      successMessage.value = ''

      try {
        // 模拟API调用
        await new Promise(resolve => setTimeout(resolve, 1000))

        const updatedUser = {
          ...user.value,
          username: profileForm.username,
          email: profileForm.email,
          bio: profileForm.bio
        }

        store.dispatch('updateProfile', updatedUser)
        successMessage.value = '个人信息更新成功'

        setTimeout(() => {
          successMessage.value = ''
        }, 3000)

      } catch (err) {
        error.value = '保存失败，请稍后重试'
      } finally {
        loading.value = false
      }
    }

    onMounted(() => {
      initForm()
    })

    return {
      user,
      profileForm,
      userStats,
      loading,
      error,
      successMessage,
      hasChanges,
      avatarInput,
      triggerAvatarUpload,
      handleAvatarUpload,
      handleSave,
      resetForm
    }
  }
}
</script>

<style scoped>
.profile {
  padding: 2rem 0;
}

.profile-card {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  max-width: 800px;
  margin: 0 auto;
}

.profile-card h1 {
  margin-bottom: 2rem;
  color: #2c3e50;
  text-align: center;
}

.avatar-section {
  display: flex;
  justify-content: center;
  margin-bottom: 2rem;
}

.avatar-upload {
  text-align: center;
}

.avatar-preview {
  margin-bottom: 1rem;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
}

.avatar-placeholder {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background: #4CAF50;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2rem;
  font-weight: bold;
  margin: 0 auto;
}

.btn-sm {
  padding: 0.5rem 1rem;
  font-size: 0.9rem;
}

.profile-form {
  margin-bottom: 2rem;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
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

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
  transition: border-color 0.3s;
}

.form-group input:focus,
.form-group textarea:focus {
  outline: none;
  border-color: #4CAF50;
}

.char-count {
  text-align: right;
  font-size: 0.8rem;
  color: #666;
  margin-top: 0.25rem;
}

.form-actions {
  display: flex;
  gap: 1rem;
  justify-content: flex-start;
}

.stats-section {
  border-top: 1px solid #eee;
  padding-top: 2rem;
}

.stats-section h3 {
  margin-bottom: 1.5rem;
  color: #2c3e50;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 1rem;
}

.stat-item {
  text-align: center;
  padding: 1rem;
  background: #f8f9fa;
  border-radius: 8px;
}

.stat-number {
  font-size: 1.5rem;
  font-weight: bold;
  color: #4CAF50;
  margin-bottom: 0.5rem;
}

.stat-label {
  font-size: 0.9rem;
  color: #666;
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

@media (max-width: 768px) {
  .profile-card {
    margin: 1rem;
    padding: 1.5rem;
  }

  .form-row {
    grid-template-columns: 1fr;
  }

  .form-actions {
    flex-direction: column;
  }

  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>
