// src/utils/api.js
// 添加认证相关API
export const authApi = {
    // 用户登录
    async login(username, password) {
        const response = await fetch('/api/auth/login/password', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.message || '登录失败');
        }

        return data;
    },

    // 用户注册
    async register(userData) {
        const response = await fetch('/api/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.message || '注册失败');
        }

        return data;
    },

    // 忘记密码
    async forgotPassword(data) {
        const response = await fetch('/api/auth/forgot-password', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        if (!response.ok) {
            throw new Error(result.message || '密码重置失败');
        }

        return result;
    }
};

// 保留原有的模拟API
export const api = {
    // 生成大纲
    async generateOutline(bookTitle) {
        // 模拟API延迟
        await new Promise(resolve => setTimeout(resolve, 1000));

        return {
            outline: `《${bookTitle}》的大纲：
      1. 开篇引入主角和背景
      2. 主角面临第一个挑战
      3. 情节发展，出现转折
      4. 高潮部分
      5. 结局`
        };
    },

    // 生成内容
    async generateContent(prompt, previousContent) {
        await new Promise(resolve => setTimeout(resolve, 1500));

        return {
            content: `基于"${prompt}"，故事继续发展...\n\n这是新生成的内容，推动了情节的发展。`
        };
    },

    // 保存书籍
    async saveBook(bookData) {
        await new Promise(resolve => setTimeout(resolve, 500));

        return {
            success: true,
            bookId: 'book_' + Date.now()
        };
    },

    // 发布书籍
    async publishBook(bookId, publishData) {
        await new Promise(resolve => setTimeout(resolve, 800));

        return {
            success: true,
            publishedAt: new Date().toISOString()
        };
    }
};
