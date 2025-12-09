<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="章节ID" prop="chapterId">
        <el-input
          v-model="queryParams.chapterId"
          placeholder="请输入章节ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生成序号" prop="generationIndex">
        <el-input
          v-model="queryParams.generationIndex"
          placeholder="请输入生成序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="基于的决策点ID" prop="decisionPointId">
        <el-input
          v-model="queryParams.decisionPointId"
          placeholder="请输入基于的决策点ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用的AI模型" prop="aiModel">
        <el-input
          v-model="queryParams.aiModel"
          placeholder="请输入使用的AI模型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="消耗的token数" prop="tokenCount">
        <el-input
          v-model="queryParams.tokenCount"
          placeholder="请输入消耗的token数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否重写过" prop="isRewritten">
        <el-input
          v-model="queryParams.isRewritten"
          placeholder="请输入是否重写过"
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
          v-hasPermi="['system:generations:add']"
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
          v-hasPermi="['system:generations:edit']"
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
          v-hasPermi="['system:generations:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:generations:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="generationsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="生成记录ID" align="center" prop="id" />
      <el-table-column label="章节ID" align="center" prop="chapterId" />
      <el-table-column label="生成序号" align="center" prop="generationIndex" />
      <el-table-column label="生成内容" align="center" prop="content" />
      <el-table-column label="使用的提示词" align="center" prop="prompt" />
      <el-table-column label="基于的决策点ID" align="center" prop="decisionPointId" />
      <el-table-column label="使用的AI模型" align="center" prop="aiModel" />
      <el-table-column label="消耗的token数" align="center" prop="tokenCount" />
      <el-table-column label="是否重写过" align="center" prop="isRewritten" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:generations:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:generations:remove']"
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

    <!-- 添加或修改内容生成记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="章节ID" prop="chapterId">
          <el-input v-model="form.chapterId" placeholder="请输入章节ID" />
        </el-form-item>
        <el-form-item label="生成序号" prop="generationIndex">
          <el-input v-model="form.generationIndex" placeholder="请输入生成序号" />
        </el-form-item>
        <el-form-item label="生成内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="使用的提示词" prop="prompt">
          <el-input v-model="form.prompt" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="基于的决策点ID" prop="decisionPointId">
          <el-input v-model="form.decisionPointId" placeholder="请输入基于的决策点ID" />
        </el-form-item>
        <el-form-item label="使用的AI模型" prop="aiModel">
          <el-input v-model="form.aiModel" placeholder="请输入使用的AI模型" />
        </el-form-item>
        <el-form-item label="消耗的token数" prop="tokenCount">
          <el-input v-model="form.tokenCount" placeholder="请输入消耗的token数" />
        </el-form-item>
        <el-form-item label="是否重写过" prop="isRewritten">
          <el-input v-model="form.isRewritten" placeholder="请输入是否重写过" />
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
import { listGenerations, getGenerations, delGenerations, addGenerations, updateGenerations } from "@/api/system/generations"

export default {
  name: "Generations",
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
      // 内容生成记录表格数据
      generationsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        chapterId: null,
        generationIndex: null,
        content: null,
        prompt: null,
        decisionPointId: null,
        aiModel: null,
        tokenCount: null,
        isRewritten: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        chapterId: [
          { required: true, message: "章节ID不能为空", trigger: "blur" }
        ],
        generationIndex: [
          { required: true, message: "生成序号不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "生成内容不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询内容生成记录列表 */
    getList() {
      this.loading = true
      listGenerations(this.queryParams).then(response => {
        this.generationsList = response.rows
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
        chapterId: null,
        generationIndex: null,
        content: null,
        prompt: null,
        decisionPointId: null,
        aiModel: null,
        tokenCount: null,
        isRewritten: null,
        createTime: null
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
      this.title = "添加内容生成记录"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getGenerations(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改内容生成记录"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGenerations(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addGenerations(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除内容生成记录编号为"' + ids + '"的数据项？').then(function() {
        return delGenerations(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/generations/export', {
        ...this.queryParams
      }, `generations_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
