<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { selectCurrentUserInfo, useRePwd} from '@/api/user';
import { ElMessage } from 'element-plus';

/**
 * 修改密码
 */
const loading = ref(false)
const formData = ref<any>({})
onMounted(() => {
  selectCurrentUserInfo().then((res) => {
    formData.value = res.data.data
  })
})

// 验证两次密码输入的是否相同
const validatePwdEq = (rule:any, value:any, callback:any) => {
  const newPwd = pwdFormData.value.newPwd;
  if (value && newPwd !== value) {
    callback(new Error('两次新密码值不同'));
  } else {
    callback();
  }
};
const pwdRules = {
  oldPwd: [
    {
      required: true,
      message: "请填写旧密码",
      trigger: "blur",
    },
  ],
  newPwd: [
    {
      required: true,
      message: "请填写新密码",
      trigger: "blur",
    },
  ],
  newRepwd: [
    {
      required: true,
      message: "请再次填写新密码",
      trigger: "blur",
    }, {
      validator: validatePwdEq,
      trigger: 'blur'
    }
  ],
}
const pwdFormData = ref<any>({})
const formPwd = ref()
const submitRePwd = () => {
  formPwd.value.validate((valid: any) => {
    if(valid) {
      loading.value = true
      useRePwd(pwdFormData.value).then(() => {
        pwdFormData.value = {}
        ElMessage({
          message: "操作成功",
          type: "success",
          plain: true
        })
      }).finally(() => {
        loading.value = false
      })
    }
  })
};

</script>

<template>
  <el-form ref="formPwd" :model="pwdFormData" :rules="pwdRules" label-position="top" label-width="80px">
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item label="旧密码" prop="oldPwd">
          <el-input
            placeholder="请填写旧密码"
            v-model="pwdFormData.oldPwd"
            maxlength="100"
            type="password"
            show-password
            clearable
          ></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item label="新密码" prop="newPwd">
          <el-input
            placeholder="请填写新密码"
            v-model="pwdFormData.newPwd"
            maxlength="100"
            type="password"
            show-password
            clearable
          ></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item label="新密码" prop="newRepwd">
          <el-input
            placeholder="请再次填写新密码"
            v-model="pwdFormData.newRepwd"
            maxlength="100"
            type="password"
            show-password
            clearable
          ></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col :span="24">
        <el-form-item>
          <el-button
            v-permission="'submit_repassword'"
            @click="submitRePwd()"
            type="primary"
            :loading="loading"
            style="width: 100%;">
            提交重置密码
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