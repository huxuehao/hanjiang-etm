<script lang="ts" setup>
import { useUserStore } from "@/stores/user";
import { computed, ref } from "vue";
import { ElMessageBox } from "element-plus";
import UserInfoForm from "@/components/user/UserInfoForm.vue";
import RepasswordForm from "@/components/user/RepasswordForm.vue";
import setting from '@/config/setting'

const userStore = useUserStore();
let userInfo = computed(() => {
  return userStore.userInfo
})

// 个人信息
let userInfoVisible = ref(false)
const openUserInfo = () => {
  userInfoVisible.value = true
};
// 修改密码
let repasswordVisible = ref(false)
const openRepassword = () => {
  repasswordVisible.value = true
};
// 退出登录
const handelLogout = () => {
  ElMessageBox.confirm("确认退出当前系统?", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    userStore.doLogout(userStore.userInfo.id).then(() => {
      window.location.href = `/${setting.baseUrl}#/login`;
    })
  });
};
</script>

<template>
  <el-popover
    :width="300"
    popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding:20px;"
  >
    <template #reference>
      <el-avatar src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
    </template>
    <template #default>
      <div>
        <el-avatar src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" style="margin-bottom: 8px" />
        <div>
          <el-row>
            <el-col :span="12">
              <p style="margin: 0; font-weight: 500" >
                {{ userInfo.name }}
                <el-icon v-if="userInfo.gender === 1" color="#3C95F8" size="15"><Male /></el-icon>
                <el-icon v-else-if="userInfo.gender === 0" color="#EC407A" size="15"><Female /></el-icon>
              </p>
            </el-col>
            <el-col :span="12" style="text-align: right;">
              <el-link type="primary" href="https://gitee.com/studioustiger" target="_blank" title="我的Gitee">Gitee</el-link>
              <span style="display: inline-block;width: 10px;"></span>
              <el-link type="success" href="https://blog.csdn.net/m0_45067620" target="_blank" title="我的CSND">CSND</el-link>
            </el-col>
          </el-row>
          <p style="margin: 0; font-size: 14px; color: var(--el-color-info)">
            {{ userInfo.email }}
          </p>
        </div>
        <el-divider />
        <p class="info-list" @click="openUserInfo">
          ☀️ 基本信息
        </p>
        <p class="info-list" @click="openRepassword">
          👀 修改密码
        </p>
        <p class="logout-button" @click="handelLogout">
          退出登录
        </p>
      </div>
    </template>
  </el-popover>
  <!-- 个人信息 -->
  <el-dialog
    v-model="userInfoVisible"
    title="基本信息"
    width="500px"
    top="10vh"
    :close-on-click-modal="false"
    append-to-body>
    <UserInfoForm v-if="userInfoVisible" />
  </el-dialog>
  <el-dialog
    v-model="repasswordVisible"
    title="修改密码"
    width="500px"
    :close-on-click-modal="false"
    append-to-body>
    <RepasswordForm v-if="repasswordVisible" />
  </el-dialog>
</template>

<style lang="scss" scoped>
.info-list {
  width: calc(100% - 25px);
  height: 32px;
  line-height: 32px;
  transition: 0.3s;
  padding: 0 10px;
  cursor: pointer;
  user-select: none;
  border-radius: 5px;
}
.info-list:hover {
  background-color: #f2f2f2;
}

.logout-button {
  text-align: center;
  height: 32px;
  line-height: 32px;
  transition: 0.3s;
  padding: 0 10px;
  cursor: pointer;
  user-select: none;
  color: #ff8284;
  border-radius: 5px;
  border: 1px solid #ffffff;
  background-color: #FFF2F0;
}

.logout-button:hover {
  border: 1px solid #ffdbd5;
}
</style>
