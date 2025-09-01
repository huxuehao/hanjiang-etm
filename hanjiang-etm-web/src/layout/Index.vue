<script lang="ts" setup>
import '@/style/transition.scss'
import screenfull from 'screenfull';
import SideMenu from '@/components/layout/SideMenu.vue';
import ResizeBox from '@/components/resize/ResizeBox.vue';
import SiderBar from '@/components/layout/SiderBar.vue'
import TopBar from '@/components/layout/TopBar.vue';

import { useMenusStore } from "@/stores/menus";
import { computed, ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
const userMenus = useMenusStore();
const route = useRoute();

let isRefresh = computed(() => {
  return userStore.getRefresh()
})

let resizeBar = ref()
let isFold = ref(false)
watch(() => userStore.getFold(), (val) => {
  if(!val) {
    setTimeout(() => {
      isFold.value = val
    }, 150)
  } else {
    isFold.value = val
  }

  resizeBar.value.triggerFold(val)
})

// 获取第一层菜单列表
let currentFirstFloorMenu = computed(() => {
  return userMenus.getCurrentFirstFloorMenu();
});

// 监听全屏
watch(() => userStore.getScreenfull(), () => {
  screenfull.toggle();
})
</script>

<template>
  <div class="main">
    <ResizeBox :initial-size="70" :min-size="0" :max-size="70" :move="false" ref="resizeBar">
      <template #first>
        <SiderBar :showText="isFold"></SiderBar>
      </template>
      <template #second>
        <TopBar></TopBar>
        <ResizeBox v-if="currentFirstFloorMenu.hasChildren" :initial-size="180" :min-size="0" :max-size="250">
          <template #first>
            <SideMenu></SideMenu>
          </template>
          <template #second>
            <router-view
                v-if="isRefresh"
                v-slot="{ Component }"
                :key="route.path"
            >
              <transition name="slide-right" mode="out-in" appear>
                <div style="height: 100%; position: relative;">
                  <component :is="Component" />
                </div>
              </transition>
            </router-view>
          </template>
        </ResizeBox>
        <router-view
            v-else-if="isRefresh"
            v-slot="{ Component }"
            :key="route.path">
          <transition name="slide-right" mode="out-in" appear>
            <div style="height: 100%; position: relative;">
              <component :is="Component" />
            </div>
          </transition>
        </router-view>
      </template>
    </ResizeBox>
  </div>
</template>

<style lang="scss" scoped>
.main {
  position: relative;
  display: flex;
  flex-direction: row;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  background-color: var(--global-bg-color);

  &_left {
    width: 70px;
    height: 100%;
    overflow: hidden;
    flex-shrink: 0;
    border-right: 1px solid #E9EBF2;
  }

  &_right {
    flex: 1;
    min-width: 0;
    min-height: 0;
    overflow: hidden;
  }
}
</style>
