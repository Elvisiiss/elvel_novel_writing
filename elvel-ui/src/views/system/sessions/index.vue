<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="书籍ID" prop="bookId">
        <el-input
          v-model="queryParams.bookId"
          placeholder="请输入书籍ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="章节ID" prop="chapterId">
        <el-input
          v-model="queryParams.chapterId"
          placeholder="请输入章节ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当前生成记录ID" prop="currentGenerationId">
        <el-input
          v-model="queryParams.currentGenerationId"
          placeholder="请输入当前生成记录ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="待处理决策点ID" prop="pendingDecisionPointId">
        <el-input
          v-model="queryParams.pendingDecisionPointId"
          placeholder="请输入待处理决策点ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否活跃" prop="isActive">
        <el-input
          v-model="queryParams.isActive"
          placeholder="请输入是否活跃"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最后活动时间" prop="lastActivityAt">
        <el-date-picker clearable
          v-model="queryParams.lastActivityAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择最后活动时间">
        </el-date-picker>
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
          v-hasPermi="['system:sessions:add']"
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
          v-hasPermi="['system:sessions:edit']"
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
          v-hasPermi="['system:sessions:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:sessions:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sessionsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="会话ID" align="center" prop="id" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="书籍ID" align="center" prop="bookId" />
      <el-table-column label="章节ID" align="center" prop="chapterId" />
      <el-table-column label="当前生成记录ID" align="center" prop="currentGenerationId" />
      <el-table-column label="待处理决策点ID" align="center" prop="pendingDecisionPointId" />
      <el-table-column label="会话状态数据" align="center" prop="sessionData" />
      <el-table-column label="是否活跃" align="center" prop="isActive" />
      <el-table-column label="最后活动时间" align="center" prop="lastActivityAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastActivityAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:sessions:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:sessions:remove']"
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

    <!-- 添加或修改创作会话对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="书籍ID" prop="bookId">
          <el-input v-model="form.bookId" placeholder="请输入书籍ID" />
        </el-form-item>
        <el-form-item label="章节ID" prop="chapterId">
          <el-input v-model="form.chapterId" placeholder="请输入章节ID" />
        </el-form-item>
        <el-form-item label="当前生成记录ID" prop="currentGenerationId">
          <el-input v-model="form.currentGenerationId" placeholder="请输入当前生成记录ID" />
        </el-form-item>
        <el-form-item label="待处理决策点ID" prop="pendingDecisionPointId">
          <el-input v-model="form.pendingDecisionPointId" placeholder="请输入待处理决策点ID" />
        </el-form-item>
        <el-form-item label="是否活跃" prop="isActive">
          <el-input v-model="form.isActive" placeholder="请输入是否活跃" />
        </el-form-item>
        <el-form-item label="最后活动时间" prop="lastActivityAt">
          <el-date-picker clearable
            v-model="form.lastActivityAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择最后活动时间">
          </el-date-picker>
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
import { listSessions, getSessions, delSessions, addSessions, updateSessions } from "@/api/system/sessions"

export default {
  name: "Sessions",
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
      // 创作会话表格数据
      sessionsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        bookId: null,
        chapterId: null,
        currentGenerationId: null,
        pendingDecisionPointId: null,
        sessionData: null,
        isActive: null,
        lastActivityAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
        bookId: [
          { required: true, message: "书籍ID不能为空", trigger: "blur" }
        ],
        chapterId: [
          { required: true, message: "章节ID不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询创作会话列表 */
    getList() {
      this.loading = true
      listSessions(this.queryParams).then(response => {
        this.sessionsList = response.rows
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
        userId: null,
        bookId: null,
        chapterId: null,
        currentGenerationId: null,
        pendingDecisionPointId: null,
        sessionData: null,
        isActive: null,
        createTime: null,
        lastActivityAt: null
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
      this.title = "添加创作会话"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getSessions(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改创作会话"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSessions(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addSessions(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除创作会话编号为"' + ids + '"的数据项？').then(function() {
        return delSessions(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/sessions/export', {
        ...this.queryParams
      }, `sessions_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
