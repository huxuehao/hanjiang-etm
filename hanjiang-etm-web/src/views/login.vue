<script lang="ts" setup>
import LoginForm from "@/components/login/LoginForm.vue";
import setting from "@/config/setting";
</script>

<template>
  <div class="login-page">
    <div class="background">
      <img src="/src/assets/lf_icon1.png" class="background-image" />
    </div>
    <div class="login-container">
      <div class="title-box">
        <span class="title-left">欢迎使用</span>
        <span class="title-right">{{ setting.systemName }}</span>
      </div>
      <LoginForm />
    </div>
    <div class="powered-by">Powered by Huxuehao</div>
  </div>
</template>

<style lang="scss" scoped>
.login-page {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100vh;
  background-color: var(--global-bg-color);

  .background {
    margin-left: 8rem;
    margin-top: 2rem;

    .background-image {
      width: 80%;
    }
  }

  .login-container {
    background-color: rgba(255, 255, 255);
    padding: 3rem 2.5rem;
    margin-bottom: 2rem;
    border-radius: 6px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
    margin-right: 12rem;

    .title-box {
      margin-bottom: 1.25rem;
      letter-spacing: 1px;

      .title-left {
        font-size: 1.6rem;
        font-weight: bolder;
        color: #474747;
      }

      .title-right {
        font-size: 0.9rem;
        margin-left: 0.625rem;
        user-select: none;
        color: var(--main-color);
        text-shadow:
            2px 2px 0 #e7e7e7,
            -2px -2px 0 #e7e7e7,
            2px -2px 0 #e7e7e7,
            -2px 2px 0 #e7e7e7;
        font-style: italic;
        font-weight: bolder;
        letter-spacing: 2px;
      }
    }
  }
}
.powered-by {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  background-color: #FFFFFF;
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;
  padding: 4px 10px;
  font-size: 12px;
  color: #98A2B3;
}
</style>
