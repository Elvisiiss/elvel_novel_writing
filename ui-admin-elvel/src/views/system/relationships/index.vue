<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="原始书籍ID" prop="originalBookId">
        <el-input
          v-model="queryParams.originalBookId"
          placeholder="请输入原始书籍ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="衍生书籍ID" prop="derivativeBookId">
        <el-input
          v-model="queryParams.derivativeBookId"
          placeholder="请输入衍生书籍ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分叉来源章节ID" prop="forkFromChapterId">
        <el-input
          v-model="queryParams.forkFromChapterId"
          placeholder="请输入分叉来源章节ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分叉来源生成记录ID" prop="forkFromGenerationId">
        <el-input
          v-model="queryParams.forkFromGenerationId"
          placeholder="请输入分叉来源生成记录ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否获得授权" prop="permissionGranted">
        <el-input
          v-model="queryParams.permissionGranted"
          placeholder="请输入是否获得授权"
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
          v-hasPermi="['system:relationships:add']"
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
          v-hasPermi="['system:relationships:edit']"
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
          v-hasPermi="['system:relationships:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:relationships:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="relationshipsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="关系ID" align="center" prop="id" />
      <el-table-column label="原始书籍ID" align="center" prop="originalBookId" />
      <el-table-column label="衍生书籍ID" align="center" prop="derivativeBookId" />
      <el-table-column label="分叉来源章节ID" align="center" prop="forkFromChapterId" />
      <el-table-column label="分叉来源生成记录ID" align="center" prop="forkFromGenerationId" />
      <el-table-column label="关系类型：直接/间接" align="center" prop="relationshipType" />
      <el-table-column label="是否获得授权" align="center" prop="permissionGranted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:relationships:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:relationships:remove']"
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

    <!-- 添加或修改版本关系对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="原始书籍ID" prop="originalBookId">
          <el-input v-model="form.originalBookId" placeholder="请输入原始书籍ID" />
        </el-form-item>
        <el-form-item label="衍生书籍ID" prop="derivativeBookId">
          <el-input v-model="form.derivativeBookId" placeholder="请输入衍生书籍ID" />
        </el-form-item>
        <el-form-item label="分叉来源章节ID" prop="forkFromChapterId">
          <el-input v-model="form.forkFromChapterId" placeholder="请输入分叉来源章节ID" />
        </el-form-item>
        <el-form-item label="分叉来源生成记录ID" prop="forkFromGenerationId">
          <el-input v-model="form.forkFromGenerationId" placeholder="请输入分叉来源生成记录ID" />
        </el-form-item>
        <el-form-item label="是否获得授权" prop="permissionGranted">
          <el-input v-model="form.permissionGranted" placeholder="请输入是否获得授权" />
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
import { listRelationships, getRelationships, delRelationships, addRelationships, updateRelationships } from "@/api/system/relationships"

export default {
  name: "Relationships",
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
      // 版本关系表格数据
      relationshipsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        originalBookId: null,
        derivativeBookId: null,
        forkFromChapterId: null,
        forkFromGenerationId: null,
        relationshipType: null,
        permissionGranted: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        originalBookId: [
          { required: true, message: "原始书籍ID不能为空", trigger: "blur" }
        ],
        derivativeBookId: [
          { required: true, message: "衍生书籍ID不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询版本关系列表 */
    getList() {
      this.loading = true
      listRelationships(this.queryParams).then(response => {
        this.relationshipsList = response.rows
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
        originalBookId: null,
        derivativeBookId: null,
        forkFromChapterId: null,
        forkFromGenerationId: null,
        relationshipType: null,
        permissionGranted: null,
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
      this.title = "添加版本关系"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getRelationships(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改版本关系"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRelationships(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addRelationships(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除版本关系编号为"' + ids + '"的数据项？').then(function() {
        return delRelationships(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/relationships/export', {
        ...this.queryParams
      }, `relationships_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
