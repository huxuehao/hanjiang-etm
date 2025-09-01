<script lang="ts" setup>
import router from "@/router/index";
import { parseMenu } from "@/utils/tools";

withDefaults(
  defineProps<{
    dataList: any[];
  }>(),
  {
    dataList: () => [],
  }
);

// 路由跳转
const openMenu = (item: any) => {
  const { path, query } = parseMenu(item);
  router.push({ path, query });
};


</script>

<template>
  <template v-for="item in dataList">
    <el-menu-item
      v-if="!item.children?.length"
      @click="openMenu(item)"
      :index="parseMenu(item).fullPath"
    >
      <template #title>
        <el-icon size="15px">
          <component :is="item.icon ? item.icon : 'Tickets'" />
        </el-icon>
        <span class="default-font">{{ item.name }}</span>
      </template>
    </el-menu-item>
    <el-sub-menu v-else :index="item.path">
      <template #title>
        <el-icon size="15px">
          <component :is="item.icon ? item.icon : 'Tickets'" />
        </el-icon>
        <span class="default-font">{{ item.name }}</span>
      </template>
      <SideMenuItem :dataList="item.children"></SideMenuItem>
    </el-sub-menu>
  </template>
</template>

<style lang="scss" scoped>
.default-font {
  font-size: 14px;
  user-select: none;
}
</style>
