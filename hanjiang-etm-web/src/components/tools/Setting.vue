<script lang="ts" setup>
import { computed } from "vue"
import { useToolsStore } from "@/stores/tools";

const toolsStore = useToolsStore();

let fold = computed(() => toolsStore.getFold())
let refresh = computed(() => toolsStore.getRefresh())
let home = computed(() => toolsStore.getHome())
let search = computed(() => toolsStore.getSearch())
let screenfull = computed(() => toolsStore.getScreenfull())
</script>

<template>
  <el-popover
    popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding:20px;"
  >
    <template #reference>
      <el-icon class="icon-btn rotate" title="设置"><Setting /></el-icon>
    </template>
    <p class="logout-button" :class="fold === 'true' ? 'show' : 'close'" @click="() => toolsStore.setFold()">
      折叠按钮
    </p>
    <p class="logout-button" :class="refresh === 'true' ? 'show' : 'close'" @click="() => toolsStore.setRefresh()">
      折叠按钮
    </p>
    <p class="logout-button" :class="home === 'true' ? 'show' : 'close'" @click="() => toolsStore.setHome()">
      首页按钮
    </p>
    <p class="logout-button" :class="search === 'true' ? 'show' : 'close'" @click="() => toolsStore.setSearch()">
      搜索按钮
    </p>
    <p class="logout-button" :class="screenfull === 'true' ? 'show' : 'close'" @click="() => toolsStore.setScreenfull()">
      全屏按钮
    </p>
  </el-popover>
</template>

<style lang="scss" scoped>
@use "./style.scss" as *;

.logout-button {
  text-align: center;
  height: 32px;
  line-height: 32px;
  transition: 0.3s;
  padding: 0 10px;
  cursor: pointer;
  user-select: none;
  border-radius: 5px;
  color: #E8F1FE;
}

.logout-button:hover {
  box-shadow: rgba(56, 66, 76, 0.35) 0px 0px 18px -10px,
}

.show {
  background-color: #458bfb17;
  color: #5f9af9;
}

.close {
  background-color: #EFEFF0;
  color: #aeaeae;
}
</style>