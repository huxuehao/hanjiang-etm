<script lang="ts" setup>
import { computed } from "vue"
import { useUserStore } from "@/stores/user";
import { useToolsStore } from "@/stores/tools";

const toolsStore = useToolsStore();
let fold_ = computed(() => toolsStore.getFold())

const userStore = useUserStore();
const openMessage = () => {
  userStore.setFold(!userStore.getFold());
};
</script>

<template>
  <el-icon 
    v-if="fold_ === 'true'" 
    class="icon-btn"
    :class="userStore.getFold()?'move-right':'move-left'"
    :title="userStore.getFold()?'展开':'折叠'" 
    @click="openMessage()">
    <Expand v-if="userStore.getFold()" />
    <Fold v-else />
  </el-icon>
</template>

<style lang="scss" scoped>
@use "./style.scss" as *;
</style>
