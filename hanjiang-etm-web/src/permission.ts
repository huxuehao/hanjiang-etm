import router from "@/router";
import { getToken } from "./utils/auth";
import NProgress from "nprogress";
import "nprogress/nprogress.css";
NProgress.configure({ easing: "ease", speed: 150, showSpinner: false });
import { useMenusStore } from '@/stores/menus'
import cache from "./utils/cache";
import { getButtonPermissions } from "./api/role";
import { isEmpty } from "./utils/tools";
import type { RouteLocationNormalized } from "vue-router";
import setting from '@/config/setting'

let userMenus:any = null

router.beforeEach(async (to, from, next) => {
  NProgress.start();

  // 使用pinia
  if(userMenus === null) {
    userMenus =  useMenusStore()
  }

  if (getToken()) {
    // 获取按钮权限
    if(isEmpty(cache.local.getJSON(setting.permissions))) {
      await getButtonPermissions().then((res) => {
        cache.local.setJSON(setting.permissions, res.data.data)
      })
    }
    if (to.path === "/login") {
      next({ path: "/" });
      NProgress.done();
    } else {
      userMenus.setCurrentMenu({title: to.meta.name ,fullPath: to.fullPath, icon: to.meta.icon, path: to.path});
      next();
    }
  } else {
    if (setting.whiteList.indexOf(to.path) !== -1) {
      next();
    } else {
      next({
        path: '/login'
      });
      NProgress.done();
    }
  }
});

router.afterEach((to, from) => {
  NProgress.done();
  setPageTitle(to);
});


/**
 * 设置页面标题，根据路由元信息和系统信息拼接标题
 * @param to 当前路由对象
 */
export const setPageTitle = (to: RouteLocationNormalized): void => {
  const { name } = to.meta
  if (name) {
    setTimeout(() => {
      document.title = `${name} - ${setting.systemName}`
    }, 150)
  } else {
    setTimeout(() => {
      document.title = `${String(to.name)} - ${setting.systemName}`
    }, 150)
  }
}
