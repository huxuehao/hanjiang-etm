import { ref } from "vue";
import { defineStore } from "pinia";
import cache from "@/utils/cache";

export const useToolsStore = defineStore("tools", () => {
  // 折叠
  const fold = ref(cache.local.get("fold-tool") || 'true');
  const getFold = () => {
    return fold.value
  };
  const setFold = () => {
    fold.value = fold.value === 'true' ? 'false' : 'true'
    cache.local.set("fold-tool", fold.value)
  };
  // 刷新
  const refresh = ref(cache.local.get("refresh-tool") || 'true');
  const getRefresh = () => {
    return refresh.value
  };
  const setRefresh = () => {
    refresh.value = refresh.value === 'true' ? 'false' : 'true'
    cache.local.set("refresh-tool", refresh.value)
  };

  // 快捷
  const quick = ref(cache.local.get("quick-tool") || 'true');
  const getQuick = () => {
    return quick.value
  };
  const setQuick = () => {
    quick.value = quick.value === 'true' ? 'false' : 'true'
    cache.local.set("quick-tool", quick.value)
  };

  // 首页
  const home = ref(cache.local.get("home-tool") || 'true');
  const getHome = () => {
    return home.value
  };
  const setHome = () => {
    home.value = home.value === 'true' ? 'false' : 'true'
    cache.local.set("home-tool", home.value)
  };

  // 搜索
  const search = ref(cache.local.get("search-tool") || 'true');
  const getSearch = () => {
    return search.value
  };
  const setSearch = () => {
    search.value = search.value === 'true' ? 'false' : 'true'
    cache.local.set("search-tool", search.value)
  };

  // 全屏
  const screenfull = ref(cache.local.get("screenfull-tool") || 'true');
  const getScreenfull = () => {
    return screenfull.value
  };
  const setScreenfull = () => {
    screenfull.value = screenfull.value === 'true' ? 'false' : 'true'
    cache.local.set("screenfull-tool", screenfull.value)
  };

  // 消息
  const message = ref(cache.local.get("message-tool") || 'true');
  const getMessage = () => {
    return message.value
  };
  const setMessage = () => {
    message.value = message.value === 'true' ? 'false' : 'true'
    cache.local.set("message-tool", message.value)
  };

  // 通知
  const notify = ref(cache.local.get("notify-tool") || 'true');
  const getNotify = () => {
    return notify.value
  };
  const setNotify = () => {
    notify.value = notify.value === 'true' ? 'false' : 'true'
    cache.local.set("notify-tool", notify.value)
  };

  return { 
    getFold,
    setFold,

    setRefresh,
    getRefresh,

    getQuick,
    setQuick,

    getHome,
    setHome,
    
    getSearch,
    setSearch,

    getScreenfull,
    setScreenfull,

    getMessage,
    setMessage,

    getNotify,
    setNotify
  };
});
