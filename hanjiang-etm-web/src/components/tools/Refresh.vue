<script lang="ts" setup>
import { nextTick } from 'vue';
import { useUserStore } from "@/stores/user";
import { computed } from "vue"
import { useToolsStore } from "@/stores/tools";

const toolsStore = useToolsStore();
let refresh_ = computed(() => toolsStore.getRefresh())

const userStore = useUserStore();

const refresh = () => {
  userStore.setRefresh(false)
  nextTick(() => {
    userStore.setRefresh(true)
  })
};
</script>

<template>
  <el-icon v-if="refresh_ === 'true'" class="icon-btn rotate" @click="refresh" title="刷新"><Refresh /></el-icon>
</template>

<style lang="scss" scoped>
@use "./style.scss" as *;
</style>