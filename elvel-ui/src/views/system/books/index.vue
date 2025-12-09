<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="书名" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入书名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="封面URL" prop="coverUrl">
        <el-input
          v-model="queryParams.coverUrl"
          placeholder="请输入封面URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类/体裁" prop="genre">
        <el-input
          v-model="queryParams.genre"
          placeholder="请输入分类/体裁"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入作者用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="基于的版本ID" prop="baseVersionId">
        <el-input
          v-model="queryParams.baseVersionId"
          placeholder="请输入基于的版本ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="版本号" prop="versionCode">
        <el-input
          v-model="queryParams.versionCode"
          placeholder="请输入版本号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总字数" prop="wordCount">
        <el-input
          v-model="queryParams.wordCount"
          placeholder="请输入总字数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="章节数" prop="chapterCount">
        <el-input
          v-model="queryParams.chapterCount"
          placeholder="请输入章节数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="阅读次数" prop="readCount">
        <el-input
          v-model="queryParams.readCount"
          placeholder="请输入阅读次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="点赞数" prop="likeCount">
        <el-input
          v-model="queryParams.likeCount"
          placeholder="请输入点赞数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:books:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:books:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:books:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:books:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="booksList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="书籍ID" align="center" prop="id" />
      <el-table-column label="书名" align="center" prop="title" />
      <el-table-column label="封面URL" align="center" prop="coverUrl" />
      <el-table-column label="书籍描述" align="center" prop="description" />
      <el-table-column label="分类/体裁" align="center" prop="genre" />
      <el-table-column label="标签数组" align="center" prop="tags" />
      <el-table-column label="作者用户ID" align="center" prop="userId" />
      <el-table-column label="基于的版本ID" align="center" prop="baseVersionId" />
      <el-table-column label="版本号" align="center" prop="versionCode" />
      <el-table-column label="状态：草稿/连载中/已完成/废弃" align="center" prop="status" />
      <el-table-column label="衍生权限：开放/需授权/关闭" align="center" prop="derivativePermission" />
      <el-table-column label="总字数" align="center" prop="wordCount" />
      <el-table-column label="章节数" align="center" prop="chapterCount" />
      <el-table-column label="阅读次数" align="center" prop="readCount" />
      <el-table-column label="点赞数" align="center" prop="likeCount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:books:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:books:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改书籍主对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="书名" prop="title">
          <el-input v-model="form.title" placeholder="请输入书名" />
        </el-form-item>
        <el-form-item label="封面URL" prop="coverUrl">
          <el-input v-model="form.coverUrl" placeholder="请输入封面URL" />
        </el-form-item>
        <el-form-item label="书籍描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="分类/体裁" prop="genre">
          <el-input v-model="form.genre" placeholder="请输入分类/体裁" />
        </el-form-item>
        <el-form-item label="作者用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入作者用户ID" />
        </el-form-item>
        <el-form-item label="基于的版本ID" prop="baseVersionId">
          <el-input v-model="form.baseVersionId" placeholder="请输入基于的版本ID" />
        </el-form-item>
        <el-form-item label="版本号" prop="versionCode">
          <el-input v-model="form.versionCode" placeholder="请输入版本号" />
        </el-form-item>
        <el-form-item label="总字数" prop="wordCount">
          <el-input v-model="form.wordCount" placeholder="请输入总字数" />
        </el-form-item>
        <el-form-item label="章节数" prop="chapterCount">
          <el-input v-model="form.chapterCount" placeholder="请输入章节数" />
        </el-form-item>
        <el-form-item label="阅读次数" prop="readCount">
          <el-input v-model="form.readCount" placeholder="请输入阅读次数" />
        </el-form-item>
        <el-form-item label="点赞数" prop="likeCount">
          <el-input v-model="form.likeCount" placeholder="请输入点赞数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBooks, getBooks, delBooks, addBooks, updateBooks } from "@/api/system/books"

export default {
  name: "Books",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 书籍主表格数据
      booksList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        coverUrl: null,
        description: null,
        genre: null,
        tags: null,
        userId: null,
        baseVersionId: null,
        versionCode: null,
        status: null,
        derivativePermission: null,
        wordCount: null,
        chapterCount: null,
        readCount: null,
        likeCount: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "书名不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "作者用户ID不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询书籍主列表 */
    getList() {
      this.loading = true
      listBooks(this.queryParams).then(response => {
        this.booksList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        coverUrl: null,
        description: null,
        genre: null,
        tags: null,
        userId: null,
        baseVersionId: null,
        versionCode: null,
        status: null,
        derivativePermission: null,
        wordCount: null,
        chapterCount: null,
        readCount: null,
        likeCount: null,
        createTime: null,
        updateTime: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加书籍主"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getBooks(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改书籍主"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBooks(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addBooks(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除书籍主编号为"' + ids + '"的数据项？').then(function() {
        return delBooks(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/books/export', {
        ...this.queryParams
      }, `books_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
