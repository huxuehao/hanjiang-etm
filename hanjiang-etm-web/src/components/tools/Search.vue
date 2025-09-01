<script lang="ts" setup>
import { ref, onMounted, onUnmounted,watch } from "vue"
import { menuList as getMenuList } from "@/api/menu";
import router from "@/router/index";
import { computed } from "vue"
import { useToolsStore } from "@/stores/tools";

const toolsStore = useToolsStore();
let search_ = computed(() => toolsStore.getSearch())

const showSearchDialog = ref(false)
const searchVal = ref<string>("")
const searchResult: any = ref<any>([])
const searchInput = ref<HTMLInputElement | null>(null)
let menuList = ref<any>([])

// 点击搜索
const handelClick = () => {
  getMenuList().then((res) => {
    menuList.value = res.data.data
  })
  showSearchDialog.value = true
  focusInput()
}

onMounted(() => {
  document.addEventListener('keydown', handleKeydown)
})

onUnmounted(() => {
  document.removeEventListener('keydown', handleKeydown)
})

const handleKeydown = (event: KeyboardEvent) => {
  const isMac = navigator.platform.toUpperCase().indexOf('MAC') >= 0
  const isCommandKey = isMac ? event.metaKey : event.ctrlKey

  if (isCommandKey && event.key.toLowerCase() === 'k') {
    event.preventDefault()
    showSearchDialog.value = true
    focusInput()
  }
}

const focusInput = () => {
  setTimeout(() => {
    searchInput.value?.focus()
  }, 100)
}

watch(() => searchVal.value, (val)=> {
  search(val)
})
const search = (val: string) => {
  if (val) {
    searchResult.value = fuzzyQueryList(menuList.value, val)
  } else {
    searchResult.value = []
  }
}

// 模糊查询
const fuzzyQueryList = (arr: any[], val: string):any[] => {
  let arrV2 = arr.filter(item => {
    for(let mitem of arr) {
      if(item.id === mitem.parentId) {
        return false
      }
    }
    return true
  })

  const lowerVal = val.toLowerCase()
  const searchItem = (item: any): any | null => {
    const lowerItemName = item.name.toLowerCase()
    const lowerItemPath = item.path.toLowerCase()
    if (lowerItemName.includes(lowerVal) || lowerItemPath.includes(lowerVal)) {
      return true
    }
    return false
  }
  // 使用 map 和 filter 来优化处理逻辑，排除 null 结果
  return arrV2.filter(searchItem);
}

// 搜索逻辑
const highlightedIndex = ref(0) 
const historyHIndex = ref(0)

const isHighlighted = (index: number) => {
  return highlightedIndex.value === index
}

const searchBlur = () => {
  highlightedIndex.value = 0
}

const searchGoPage = (item: any) => {
  showSearchDialog.value = false
  router.push({path: item.path})
  searchVal.value = ''
  searchResult.value = []
}

const closeSearchDialog = () => {
  searchVal.value = ''
  searchResult.value = []
  highlightedIndex.value = 0
  historyHIndex.value = 0
}

// 鼠标 hover 高亮
const highlightOnHover = (index: number) => {
  highlightedIndex.value = index
}
</script>

<template>
  <el-icon v-if="search_ === 'true'" class="icon-btn jump" title="搜索" @click="handelClick()"><Search /></el-icon>
  <el-dialog
      v-model="showSearchDialog"
      width="600"
      :show-close="false"
      :lock-scroll="false"
      modal-class="search-modal"
      @close="closeSearchDialog"
    >
      <el-input
        v-model.trim="searchVal"
        placeholder="请输入菜单关键字"
        @input="search"
        @blur="searchBlur"
        ref="searchInput"
        prefix-icon="Search"
        size="default"
      >
        <template #suffix>
          <div class="search-keydown">
            <span>ESC</span>
          </div>
        </template>
      </el-input>
      <div class="result" v-show="searchResult.length">
        <el-scrollbar max-height="350px" always>
          <div class="box" v-for="(item, index) in searchResult" :key="index">
            <div
              :key="index"
              @click="searchGoPage(item)"
              @mouseenter="highlightOnHover(index)"
              :class="{
                highlighted: isHighlighted(index)
              }"
            >
              <span>
                <el-icon style="margin-right: 5px;"><component :is="item.icon"/></el-icon>
                {{ item.name }}
              </span>
              <span class="selected-icon" v-show="isHighlighted(index)">CLICK</span>
            </div>
          </div>
        </el-scrollbar>
        
      </div>
      <template #footer>
        <div class="dialog-footer"></div>
      </template>
    </el-dialog>
</template>

<style lang="scss" scoped>
@use "./style.scss" as *;

.el-input {
  height: 48px;

  ::v-deep .el-input__wrapper {
    background-color: rgba(241, 241, 244, 0.8);
    border: 1px solid #dbdfe9;
    border-radius: 5px !important;
    box-shadow: none;
  }

  ::v-deep .el-input__inner {
    color: #808290 !important;
  }

  .search-keydown {
    display: flex;
    align-items: center;
    height: 20px;
    padding: 0 4px;
    color: #636674;
    background-color: #e6e6e6;
    border-radius: 4px;

    i {
      font-size: 12px;
    }

    span {
      margin-left: 2px;
      font-size: 12px;
    }
  }
}

.result {
  width: 100%;
  margin-top: 30px;

  .box {
    margin-top: 0 !important;
    font-size: 16px;
    font-weight: 500;
    line-height: 1;
    cursor: pointer;

    .menu-icon {
      margin-right: 5px;
      font-size: 18px;
    }

    div {
      display: flex;
      align-items: center;
      justify-content: space-between;
      height: 50px;
      padding: 0 16px;
      margin-top: 8px;
      font-size: 15px;
      font-weight: 400;
      color: #4b5675;
      border-radius: 5px !important;
      transition: 0.1s;

      &.highlighted {
        background-color: #f1f1f1 !important;
      }

      .selected-icon {
        font-size: 15px;
      }
    }
  }
}
</style>