<!-- src/views/Home.vue -->
<template>
  <div class="home">
    <div class="hero">
      <div class="container">
        <h1>欢迎来到小说创作平台</h1>
        <p>AI辅助创作，让您的创意无限延伸</p>
        <div class="hero-actions">
          <router-link to="/create" class="btn btn-primary">开始创作</router-link>
          <router-link to="/library" class="btn btn-secondary">浏览书库</router-link>
        </div>
      </div>
    </div>

    <div class="features">
      <div class="container">
        <h2>平台特色</h2>
        <div class="features-grid">
          <div class="feature">
            <h3>AI辅助创作</h3>
            <p>基于AI技术，为您提供剧情发展和内容建议</p>
          </div>
          <div class="feature">
            <h3>多版本创作</h3>
            <p>支持基于原作的二次创作，尊重原创</p>
          </div>
          <div class="feature">
            <h3>创作过程透明</h3>
            <p>读者可以查看完整的创作历程和决策</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home'
}
</script>

<style scoped>
.hero {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 4rem 0;
  text-align: center;
}

.hero h1 {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.hero p {
  font-size: 1.2rem;
  margin-bottom: 2rem;
  opacity: 0.9;
}

.hero-actions {
  display: flex;
  gap: 1rem;
  justify-content: center;
}

.features {
  padding: 4rem 0;
}

.features h2 {
  text-align: center;
  margin-bottom: 3rem;
  font-size: 2.5rem;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
}

.feature {
  text-align: center;
  padding: 2rem;
  border: 1px solid #eee;
  border-radius: 8px;
  transition: transform 0.3s;
}

.feature:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0,0,0,0.1);
}

.feature h3 {
  margin-bottom: 1rem;
  color: #2c3e50;
}

@media (max-width: 768px) {
  .hero h1 {
    font-size: 2rem;
  }

  .hero-actions {
    flex-direction: column;
    align-items: center;
  }
}
</style>
