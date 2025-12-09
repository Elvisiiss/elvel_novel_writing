<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="决策点ID" prop="decisionPointId">
        <el-input
          v-model="queryParams.decisionPointId"
          placeholder="请输入决策点ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否为系统提供" prop="isSystemProvided">
        <el-input
          v-model="queryParams.isSystemProvided"
          placeholder="请输入是否为系统提供"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="被选择次数" prop="selectedCount">
        <el-input
          v-model="queryParams.selectedCount"
          placeholder="请输入被选择次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否被创作者选中" prop="isSelected">
        <el-input
          v-model="queryParams.isSelected"
          placeholder="请输入是否被创作者选中"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="选择后生成的内容ID" prop="nextGenerationId">
        <el-input
          v-model="queryParams.nextGenerationId"
          placeholder="请输入选择后生成的内容ID"
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
          v-hasPermi="['system:options:add']"
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
          v-hasPermi="['system:options:edit']"
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
          v-hasPermi="['system:options:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:options:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="optionsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="选项ID" align="center" prop="id" />
      <el-table-column label="决策点ID" align="center" prop="decisionPointId" />
      <el-table-column label="选项描述" align="center" prop="optionText" />
      <el-table-column label="是否为系统提供" align="center" prop="isSystemProvided" />
      <el-table-column label="关键词数组" align="center" prop="keywords" />
      <el-table-column label="被选择次数" align="center" prop="selectedCount" />
      <el-table-column label="是否被创作者选中" align="center" prop="isSelected" />
      <el-table-column label="选择后生成的内容ID" align="center" prop="nextGenerationId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:options:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:options:remove']"
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

    <!-- 添加或修改决策选项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="决策点ID" prop="decisionPointId">
          <el-input v-model="form.decisionPointId" placeholder="请输入决策点ID" />
        </el-form-item>
        <el-form-item label="选项描述" prop="optionText">
          <el-input v-model="form.optionText" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否为系统提供" prop="isSystemProvided">
          <el-input v-model="form.isSystemProvided" placeholder="请输入是否为系统提供" />
        </el-form-item>
        <el-form-item label="被选择次数" prop="selectedCount">
          <el-input v-model="form.selectedCount" placeholder="请输入被选择次数" />
        </el-form-item>
        <el-form-item label="是否被创作者选中" prop="isSelected">
          <el-input v-model="form.isSelected" placeholder="请输入是否被创作者选中" />
        </el-form-item>
        <el-form-item label="选择后生成的内容ID" prop="nextGenerationId">
          <el-input v-model="form.nextGenerationId" placeholder="请输入选择后生成的内容ID" />
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
import { listOptions, getOptions, delOptions, addOptions, updateOptions } from "@/api/system/options"

export default {
  name: "Options",
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
      // 决策选项表格数据
      optionsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        decisionPointId: null,
        optionText: null,
        isSystemProvided: null,
        keywords: null,
        selectedCount: null,
        isSelected: null,
        nextGenerationId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        decisionPointId: [
          { required: true, message: "决策点ID不能为空", trigger: "blur" }
        ],
        optionText: [
          { required: true, message: "选项描述不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询决策选项列表 */
    getList() {
      this.loading = true
      listOptions(this.queryParams).then(response => {
        this.optionsList = response.rows
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
        decisionPointId: null,
        optionText: null,
        isSystemProvided: null,
        keywords: null,
        selectedCount: null,
        isSelected: null,
        nextGenerationId: null,
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
      this.title = "添加决策选项"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getOptions(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改决策选项"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOptions(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addOptions(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除决策选项编号为"' + ids + '"的数据项？').then(function() {
        return delOptions(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/options/export', {
        ...this.queryParams
      }, `options_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
