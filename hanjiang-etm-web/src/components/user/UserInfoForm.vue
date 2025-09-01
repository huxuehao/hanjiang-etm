<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { selectCurrentUserInfo, userUpdate} from '@/api/user';
import { ElMessage } from 'element-plus';

const loading = ref(false)

/**
 * 基本信息
 */
const rules = {
  name: [
    {
      required: true,
      message: "必填项",
      trigger: "blur",
    },
  ],
  code: [
    {
      required: true,
      message: "必填项",
      trigger: "blur",
    },
  ],
  phone: [
    {
      required: true,
      message: "必填项",
      trigger: "blur",
    },
  ],
  account: [
    {
      required: true,
      message: "必填项",
      trigger: "blur",
    },
  ],
  orgId: [
    {
      required: true,
      message: "必填项",
      trigger: "blur",
    },
  ],
};
const formData = ref<any>({})
onMounted(() => {
  selectCurrentUserInfo().then((res) => {
    formData.value = res.data.data
  })
})
const form = ref()
const submitUserInfo = () => {
  form.value.validate((valid: any) => {
    if(valid) {
      loading.value = true
      userUpdate(formData.value).then((res) => {
        if(res.data.code === 200) {
          ElMessage({
            message: "操作成功",
            type: "success",
            plain: true
          })
        }
        selectCurrentUserInfo().then((res) => {
          formData.value = res.data.data
        })
      }).finally(() => {
        loading.value = false
      })
    }
  })
};

</script>

<template>
  <el-form ref="form" :model="formData" :rules="rules" label-position="top" label-width="80px">
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item label="登录账号" prop="account">
          <el-input
            placeholder="请填写登录账号"
            v-model="formData.account"
            maxlength="40"
            clearable
          ></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item label="用户姓名" prop="name">
          <el-input
            placeholder="请填写用户姓名"
            v-model="formData.name"
            maxlength="10"
            clearable
          >
          </el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item label="用户编号" prop="code">
          <el-input
            placeholder="请填写用户编号"
            v-model="formData.code"
            maxlength="40"
            clearable
          >
          </el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item label="用户昵称" prop="nickname">
          <el-input
            placeholder="请填写用户昵称"
            v-model="formData.nickname"
            maxlength="10"
            clearable
          >
          </el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item label="手机号码" prop="phone">
          <el-input
            placeholder="请填写手机号码"
            maxlength="11"
            v-model="formData.phone"
            clearable
          ></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item label="邮箱号码" prop="email">
          <el-input
            placeholder="请填写邮箱号码"
            maxlength="100"
            v-model="formData.email"
            clearable
          ></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item>
          <el-button
            v-permission="'submit_user_info'"
            @click="submitUserInfo()"
            type="primary"
            :loading="loading"
            style="width: 100%;">
            提交用户信息
          </el-button>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<style lang="scss" scoped>
.task-step {
  cursor: pointer;
}
</style>