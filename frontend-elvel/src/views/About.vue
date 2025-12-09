<!-- src/views/About.vue -->
<template>
  <div class="about">
    <div class="container">
      <h1>关于我们</h1>
      <div class="about-content">
        <section class="intro">
          <h2>平台简介</h2>
          <p>小说创作平台是一个创新的AI辅助创作工具，旨在帮助创作者更好地实现他们的创意想法。</p>
        </section>

        <section class="features">
          <h2>核心功能</h2>
          <ul>
            <li>AI辅助生成小说大纲和内容</li>
            <li>交互式剧情发展选择</li>
            <li>多版本创作支持</li>
            <li>创作过程透明化</li>
            <li>读者参与式阅读体验</li>
          </ul>
        </section>

        <section class="contact">
          <h2>联系我们</h2>
          <p>如有问题或建议，请通过以下方式联系我们：</p>
          <p>邮箱：3115089759@qq.com</p>
        </section>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'About'
}
</script>

<style scoped>
.about {
  padding: 2rem 0;
}

.about-content {
  max-width: 800px;
  margin: 0 auto;
}

.about-content section {
  margin-bottom: 3rem;
}

.about-content h2 {
  color: #2c3e50;
  margin-bottom: 1rem;
  border-bottom: 2px solid #4CAF50;
  padding-bottom: 0.5rem;
}

.about-content p {
  line-height: 1.6;
  margin-bottom: 1rem;
}

.about-content ul {
  list-style-type: none;
  padding-left: 0;
}

.about-content li {
  padding: 0.5rem 0;
  border-bottom: 1px solid #eee;
}

.about-content li:before {
  content: "✓ ";
  color: #4CAF50;
  font-weight: bold;
}
</style>
