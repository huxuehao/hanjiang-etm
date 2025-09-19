<script lang="ts" setup>
import { type FormInstance } from "element-plus";
import { reactive, ref, watch } from "vue";
import { useUserStore } from "@/stores/user";
import DragVerify from "./DragVerify.vue";
import setting from '@/config/setting'

const userStore = useUserStore();


// 密码登录对象
let passwordForm = reactive({
  account: "administrator",
  password: "Admin@HanJ123.com",
});
// 表单校验规则
const rules = ref({
  account: [
    {
      required: true,
      message: "用户名不能为空",
      trigger: "blur",
    },
  ],
  password: [
    {
      required: true,
      message: "密码不能为空",
      trigger: "blur",
    },
  ]
});

const isPassing = ref(false)
const isClickPass = ref(false)
const onPass = () => {};

// 登录
let pwdRef = ref<FormInstance>();
const handelSubmit = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (!isPassing.value) {
      isClickPass.value = true
      return
    }

    if (valid) {
      userStore
        .doLogin({
          account: passwordForm.account.trim(),
          password: passwordForm.password,
        })
        .then(() => {
          window.location.href = `/${setting.baseUrl}/#/`;
          setTimeout(() => {
            location.reload();
          }, 0);
        })
    }
  });
};
</script>

<template>
  <div class="login-box">
    <el-form
      :model="passwordForm"
      :rules="rules"
      ref="pwdRef"
      label-width="auto"
    >
      <el-form-item label="" prop="account" label-width="0">
        <el-input
          v-model="passwordForm.account"
          placeholder="请输入账号"
          maxlength="100"
          prefix-icon="User"
          clearable
          size="default"
        ></el-input>
      </el-form-item>
      <el-form-item label="" prop="password" label-width="0">
        <el-input
          v-model="passwordForm.password"
          type="password"
          maxlength="100"
          autocomplete="off"
          placeholder="请输入密码"
          prefix-icon="Lock"
          show-password
          clearable
          size="default"
        ></el-input>
      </el-form-item>
      <div class="drag-verify">
          <div class="drag-verify-content" :class="{ error: !isPassing && isClickPass }">
            <DragVerify
              ref="dragVerify"
              v-model:value="isPassing"
              :width="298"
              text="按住滑块拖动"
              textColor="#b5b7c8"
              successText="验证成功"
              progressBarBg="#458CFB"
              background="#F1F1F4"
              handlerBg="#FFFFFF"
              @pass="onPass"
            />
          </div>
          <p class="error-text" :class="{ 'show-error-text': !isPassing && isClickPass }">请拖动滑块完成验证</p>
        </div>
    </el-form>
    <el-button
      type="primary"
      @click="handelSubmit(pwdRef)"
      class="login-btn"
      size="default"
    >
      登 录
    </el-button>
  </div>
</template>

<style lang="scss" scoped>
.login-box {
  width: 300px;
}

.login-btn {
  width: 100%;
  height: 40px;
}

.drag-verify {
  position: relative;
  padding-bottom: 20px;
  margin-top: 18px;

  .drag-verify-content {
    position: relative;
    z-index: 2;
    box-sizing: border-box;
    user-select: none;
    border-radius: 8px;
    transition: all 0.3s;

    &.error {
      border-color: #f56c6c;
    }
  }

  .error-text {
    position: absolute;
    top: 0;
    z-index: 1;
    margin-top: 10px;
    font-size: 13px;
    color: #f56c6c;
    transition: all 0.3s;

    &.show-error-text {
      transform: translateY(23px);
    }
  }
}
</style>
