<script lang="ts" setup>
import { useUserStore } from "@/stores/user";
import { computed } from "vue";

const userStore = useUserStore();
let userInfo = computed(() => {
  return userStore.userInfo
})

const getTimePeriod = () => {
  const now = new Date();
  const hour = now.getHours();

  if (hour >= 5 && hour < 8) {
    return '早上';
  } else if (hour >= 8 && hour < 12) {
    return '上午';
  } else if (hour >= 12 && hour < 18) {
    return '下午';
  } else {
    return '晚上';
  }
}

</script>

<template>
  <div class="container">
    <div class="left-section">
      <h1>{{ getTimePeriod() }}好, {{ userInfo.nickname || userInfo.name || userInfo.account }}</h1>
      <h2>欢迎使用轻量级考试培训平台-ETM ONLINE</h2>
      <div style="margin-top: 40px">
        <div style="margin-top: 20px">
          <span style="padding: 8px; background-color: #FCE4EC; border-radius: 6px;min-width: 0">支持 5 种试题类型</span>
          <br/><br/>
          <span>单选题、多选题、判断题、填空题、解答题，支持题目的批量导入。</span>
        </div>
        <div style="margin-top: 20px">
          <span style="padding: 8px; background-color: #d5f5d8; border-radius: 6px;min-width: 0">支持配置试卷模版</span>
          <br/><br/>
          <span>以便在考试模块直接选择试卷。试卷模版与试题之间是解耦的，即试题的二次修改/删除不会影响到试卷模版。</span>
        </div>
        <div style="margin-top: 20px">
          <span style="padding: 8px; background-color: #cceaf9; border-radius: 6px;min-width: 0">支持 3 种题目规则</span>
          <br/><br/>
          <span>题目固定，试卷相同。</span>
          <span>题目随机，试卷不同。</span>
          <span>题目随机，试卷相同。</span>
        </div>

        <div style="margin-top: 20px">
          <span style="padding: 8px; background-color: #FCE4EC; border-radius: 6px;min-width: 0">支持 2 种批改方式</span>
          <br/><br/>
          <span>机器批改：系统将完成试卷的自动批改。</span><br/>
          <span>人工批改：系统将自动批改单选、多选、判断题，其他题型需人工批改。</span>
        </div>
        <div style="margin-top: 20px">
          <span style="padding: 8px; background-color: #d5f5d8; border-radius: 6px;min-width: 0">支持防作弊</span>
          <br/><br/>
          <span>开启防作弊后，系统将采取一定的检测手段，有但不限于：检测切换浏览器标签、切换应用、最小化浏览器窗口等其他违规行为。</span>
        </div>
      </div>
    </div>
    <div class="right-section">
      <img src="/src/assets/lf_icon2.png" alt="插图" />
    </div>
  </div>
</template>

<style lang="scss" scoped>
body {
  font-family: Arial, sans-serif;
  background-color: #f0f4f8;
  color: #333;
}

.container {
  position: relative;
  display: flex;
  padding: 20px;
}

.left-section {
  flex: 0 0 30%;
  padding-left: 15%;
  padding-top: 7%;
}

.right-section {
  flex: 0 0 45%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.right-section img {
  max-width: 100%;
  height: auto;
}

h1, h2 {
  margin-bottom: 20px;
  color: #2c3e50;
}
</style>
