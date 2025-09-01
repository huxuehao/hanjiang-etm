<script lang="ts" setup>
import { computed, ref, watch } from "vue";
import SideMenuItem from "./SideMenuItem.vue";
import { useUserStore } from "@/stores/user";
import { useMenusStore } from "@/stores/menus";
import { parseMenu, findFirstLeafNode, findNodeWithRoutePath } from "@/utils/tools";
import router from "@/router/index";

const userStore = useUserStore();
const userMenus = useMenusStore();

// 获取当前菜单列表
let menuList = computed(() => {
  const menus = userStore.getMenus();
  const currentFirstFloorMenu = userMenus.getCurrentFirstFloorMenu();
  for(let menu of menus) {
    if(menu.id === currentFirstFloorMenu.id && currentFirstFloorMenu.hasChildren) {
      return menu.children;
    }
  }
  return [];
});

let isCollapse = ref(false);

const handleOpen = (key: string, keyPath: string[]) => {};
const handleClose = (key: string, keyPath: string[]) => {};
let activeMenu = computed(() => {
  return userMenus.getCurrentMenu();
});

// 监控子菜单的变化
watch(() => menuList.value, (value) => {
  // 通过当前路由获取当前子菜单中是否有匹配的路由
  const node = findNodeWithRoutePath(value, activeMenu.value.path)
  // 成立条件：没有匹配的路由
  if(!node) {
    // 获取当前菜单中的第一个节点
    const firstNode = findFirstLeafNode(value)
    if(firstNode) {
      const { path, query } = parseMenu(firstNode);
      router.push({ path, query });
    }
  }
},{ immediate: true })
</script>

<template>
  <div class="menu-box">
    <el-scrollbar height="calc(100% - 10px)" >
      <el-menu
        style="border: none;"
        mode="vertical"
        text-color="#445160"
        active-text-color="#2079F4"
        :default-active="activeMenu.fullPath"
        :unique-opened="false"
        :router="false"
        :collapse="isCollapse"
        @open="handleOpen"
        @close="handleClose"
      >
        <SideMenuItem :data-list="menuList"></SideMenuItem>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<style lang="scss" scoped>
.menu-box {
  position: relative;
  height: calc(100%); 
  width: 100%; 
  transition: 300ms;
  border-radius: 5px;
  overflow: hidden;
  background-color: #FFFFFF;

}

/* 被选中菜单项的样式 */
::v-deep .el-menu-item.is-active {
  position: relative;
  color: #458CFB;
  background-color: var(--global-bg-color);
}
::v-deep .el-menu-item:hover {
  background-color: #FFFFFF;
  color: #458CFB;
  background-color: var(--global-bg-color);
}

::v-deep .el-menu .default-icon {
  font-size: 14px;
}
::v-deep .el-menu .default-font {
  font-size: 13px;
}
::v-deep .el-sub-menu__title {
  font-size: 13px;
}

/* 左侧竖条的样式 */
// ::v-deep .el-menu-item.is-active::before {
//   content: '';
//   position: absolute;
//   left: 0;
//   top: 0;
//   bottom: 0;
//   width: 3px;
//   background-color: #408DF6;
// }
</style>
