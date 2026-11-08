<template>
  <div class="product-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span class="title">商品管理</span>
        </div>
      </template>

      <!-- 搜索和操作区 -->
      <div class="search-bar">
        <el-input
          v-model="searchName"
          placeholder="搜索商品名称..."
          clearable
          style="width: 250px"
          @keyup.enter="handleSearch"
        />
        <el-button type="primary" @click="handleSearch">搜索</el-button>
        <el-button @click="handleReset">重置</el-button>
        <el-button type="success" @click="showAddDialog">新增商品</el-button>
      </div>

      <!-- 商品列表 -->
      <el-table :data="productList" stripe style="width: 100%; margin-top: 20px" v-loading="loading">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="商品名称" />
        <el-table-column prop="price" label="价格（元）" width="120">
          <template #default="{ row }">
            ¥{{ row.price.toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column prop="stock" label="库存" width="100" />
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column prop="updateTime" label="更新时间" width="180" />
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" size="small" @click="handleEdit(row)">
              编辑
            </el-button>
            <el-button link type="danger" size="small" @click="handleDelete(row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="pagination">
        <el-pagination
          v-model:current-page="current"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @current-change="handlePageChange"
          @size-change="handleSizeChange"
        />
      </div>
    </el-card>

    <!-- 新增/编辑弹窗 -->
    <el-dialog
      v-model="dialogVisible"
      :title="isEditing ? '编辑商品' : '新增商品'"
      width="500px"
      @close="resetForm"
    >
      <el-form
        ref="formRef"
        :model="formData"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品价格" prop="price">
          <el-input-number v-model="formData.price" :precision="2" :min="0" />
        </el-form-item>
        <el-form-item label="商品库存" prop="stock">
          <el-input-number v-model="formData.stock" :min="0" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确认</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { FormInstance, FormRules } from 'element-plus'
import { useProductStore } from '@/stores/product'
import * as productApi from '@/api/product'
import type { ProductVO, ProductDTO } from '@/types'

const store = useProductStore()

// 响应式数据
const formRef = ref<FormInstance>()
const dialogVisible = ref(false)
const isEditing = ref(false)
const editingId = ref<number | null>(null)
const searchName = ref('')

const formData = ref<ProductDTO>({
  name: '',
  price: 0,
  stock: 0
})

// 表单验证规则
const rules: FormRules = {
  name: [
    { required: true, message: '商品名称不能为空', trigger: 'blur' }
  ],
  price: [
    { required: true, message: '商品价格不能为空', trigger: 'blur' },
    {
      validator: (_rule, value, callback) => {
        if (value === undefined || value === null || value < 0) {
          callback(new Error('商品价格不能小于0'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ],
  stock: [
    { required: true, message: '商品库存不能为空', trigger: 'blur' },
    {
      validator: (_rule, value, callback) => {
        if (value === undefined || value === null || value < 0) {
          callback(new Error('商品库存不能小于0'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ]
}

// 计算属性
const productList = computed(() => store.productList)
const total = computed(() => store.total)
const current = computed({
  get: () => store.current,
  set: (value) => store.setCurrentPage(value)
})
const pageSize = computed({
  get: () => store.pageSize,
  set: (value) => store.setPageSize(value)
})
const loading = computed(() => store.loading)

// 搜索
const handleSearch = async () => {
  store.setSearchName(searchName.value)
  await loadProducts()
}

// 重置搜索
const handleReset = async () => {
  searchName.value = ''
  store.resetSearch()
  await loadProducts()
}

// 显示新增弹窗
const showAddDialog = () => {
  isEditing.value = false
  editingId.value = null
  resetForm()
  dialogVisible.value = true
}

// 编辑商品
const handleEdit = (product: ProductVO) => {
  isEditing.value = true
  editingId.value = product.id
  formData.value = {
    name: product.name,
    price: product.price,
    stock: product.stock
  }
  dialogVisible.value = true
}

// 删除商品
const handleDelete = (id: number) => {
  ElMessageBox.confirm(
    '确定要删除该商品吗？此操作无法撤销。',
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      await store.deleteProduct(id)
      ElMessage.success('删除成功')
      await loadProducts()
    } catch (error) {
      ElMessage.error('删除失败')
    }
  }).catch(() => {
    // 用户取消删除
  })
}

// 提交表单
const handleSubmit = async () => {
  if (!formRef.value) return

  await formRef.value.validate(async (valid) => {
    if (!valid) return

    try {
      if (isEditing.value && editingId.value) {
        // 编辑
        await productApi.updateProduct(editingId.value, formData.value)
        ElMessage.success('修改成功')
      } else {
        // 新增
        await productApi.createProduct(formData.value)
        ElMessage.success('新增成功')
      }
      dialogVisible.value = false
      await loadProducts()
    } catch (error) {
      ElMessage.error(isEditing.value ? '修改失败' : '新增失败')
    }
  })
}

// 重置表单
const resetForm = () => {
  formData.value = {
    name: '',
    price: 0,
    stock: 0
  }
  formRef.value?.clearValidate()
}

// 页码变化
const handlePageChange = async () => {
  await loadProducts()
}

// 每页数量变化
const handleSizeChange = async () => {
  await loadProducts()
}

// 加载商品列表
const loadProducts = async () => {
  try {
    await store.fetchProductList()
  } catch (error) {
    ElMessage.error('加载商品列表失败')
  }
}

// 组件挂载时加载数据
onMounted(() => {
  loadProducts()
})
</script>

<style scoped lang="css">
.product-container {
  padding: 20px;
  background-color: #f5f7fa;
  min-height: 100vh;
}

.box-card {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.title {
  font-size: 20px;
  font-weight: bold;
  color: #333;
}

.search-bar {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
  align-items: center;
  flex-wrap: wrap;
}

.pagination {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}
</style>
