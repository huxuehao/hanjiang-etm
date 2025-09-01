<script lang="ts" setup>
import { ref, computed, watch } from "vue";
import { useUserStore } from "@/stores/user";
import { useMenusStore } from "@/stores/menus";
import router from "@/router/index";
import { useRoute } from "vue-router";
import { findTopNodeWithRoutePath, parseMenu } from "@/utils/tools";
import UserIconBtn from "../user/UserIconBtn.vue";

defineProps({
  showText: {
    type: Boolean,
    default: () => true
  }
})

const userStore = useUserStore();
const userMenus = useMenusStore();
const route = useRoute();

// 获取第一层菜单列表
let menuList:any = computed(() => {
  let firstFloorMenu:any = []
  for(let menuItem of userStore.getMenus()) {
    let {id, name, path, icon, code, params, sort} = menuItem
    let hasChildren = menuItem.children?.length > 0 || false
    firstFloorMenu.push({
      id, name, path, icon, code, params, sort, hasChildren
    })
  }
  return firstFloorMenu;
});

// 打开路由
const openMenu = (item: any) => {
  const { path, query } = parseMenu(item);
  router.push({ path, query });
};

// 菜单项数据
let currentKey = ref('0');
// 点击左侧菜单
const handleMenuClick = (menu: any) => {
  setCurrentKey(menu)
};
const setCurrentKey = (menu: any) => {
  currentKey.value = menu.id;
  userMenus.setCurrentFirstFloorMenu(menu)
  if(!menu.hasChildren) {
    openMenu(menu)
  }
};

// 初始化方法
const init = () => {
  const node = findTopNodeWithRoutePath(userStore.getMenus(), route.fullPath)
  if(node) {
    setCurrentKey(node)
  } else {
    currentKey.value = userMenus.getCurrentFirstFloorMenu()?.id || menuList[0]?.id || '0'
  }
};
// 初始化
init()
// 监听路由变化
watch(() => route.fullPath, () => {
  init()
})

</script>

<template>
  <div class="bar">
    <div class="logo">
      <img src="@/assets/logo.png" alt="" width="47px" />
    </div>
    <div style="height: calc(100% - 140px); position: relative;">
      <el-scrollbar height="100%">
      <div class="menu-items-container">
        <div
          v-for="item in menuList"
          :key="item.id"
          class="menu-item"
          :class="{ selected: currentKey === item.id }"
          @click="handleMenuClick(item)"
        >
          <span class="menu-item-icon-container">
            <el-icon size="32px" class="menu-item-icon"
              ><component :is="item.icon"
            /></el-icon>
          </span>
          <span v-show="!showText" class="menu-item-label">{{ item.name }}</span>
        </div>
      </div>
    </el-scrollbar>
    </div>
    <div class="user_info">
      <UserIconBtn></UserIconBtn>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.bar {
  width: 100%;
  height: 100%;
  // background-color: #23262E;
  background: linear-gradient(135deg,#23262E -20%, #3a3e48 100%);

  .logo,.user_info {
    width: 100%;
    height: 70px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
}

.menu-items-container {
  width: 100%;
}

.menu-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 5px 0;
  color: #e7ecfa;
  cursor: pointer;
  border-radius: 3px;
  margin: 0 5px 10px 5px;
  transition: all 0.3s ease;
}

.menu-item:hover {
  color: #458CFB;
}

.menu-item.selected {
  color: #458CFB;
}

.menu-item-icon-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 25px;
  border-radius: 50%;
  margin-bottom: 3px;
}

.menu-item.selected .menu-item-icon {
  color: #458CFB;
}

.menu-item-label {
  font-size: 16px;
  width: 32px;
  text-align: center;
  margin-top: 5px;
}
</style>
