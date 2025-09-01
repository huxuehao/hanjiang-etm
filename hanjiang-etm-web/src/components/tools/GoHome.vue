<script lang="ts" setup>
import setting from "@/config/setting"
import router from "@/router/index";
import { computed } from "vue"
import { useToolsStore } from "@/stores/tools";

const toolsStore = useToolsStore();
let home_ = computed(() => toolsStore.getHome())

const goHome = () => {
  router.push({path: setting.homePath})
};
</script>

<template>
  <el-icon v-if="home_ === 'true'" class="icon-btn scale" style="font-size: 18px;" title="首页" @click="goHome()"><House /></el-icon>
</template>

<style lang="scss" scoped>
@use "./style.scss" as *;
</style>