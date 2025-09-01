<script lang="ts" setup>
import { computed } from "vue"
import { useUserStore } from "@/stores/user";
import { useToolsStore } from "@/stores/tools";

const toolsStore = useToolsStore();
let screenfull_ = computed(() => toolsStore.getScreenfull())

const userStore = useUserStore();
const screenfull = () => {
  userStore.setScreenfull(!userStore.getScreenfull());
};

let isScreenfull = computed(() => {
  return userStore.getScreenfull();
})
</script>

<template>
  <el-icon 
    v-if="screenfull_ === 'true'"
    class="icon-btn scale" 
    @click="screenfull" 
    :style="{fontSize: isScreenfull ? '20px' : '18px'}"
    :title="isScreenfull?'退出全屏':'全屏'">
    <Notification v-if="isScreenfull" />
    <FullScreen v-else />
  </el-icon>
</template>

<style lang="scss" scoped>
@use "./style.scss" as *;
</style>