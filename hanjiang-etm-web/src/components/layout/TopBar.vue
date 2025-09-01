<script lang="ts" setup>
import PageHeader from './PageHeader.vue';
import Fold from '@/components/tools/Fold.vue';
import Refresh from '@/components/tools/Refresh.vue';
import GoHome from '@/components/tools/GoHome.vue';
import Search from '@/components/tools/Search.vue';
import Setting from '@/components/tools/Setting.vue';
import ScreenFull from '@/components/tools/Screenfull.vue';

</script>

<template>
  <div class="bar-main">
    <el-row :gutter="0">
      <el-col :span="12">
        <div style="display: flex;align-items: center;">
          <Fold></Fold>
          <GoHome></GoHome>
          <Refresh></Refresh>
          <PageHeader></PageHeader>
        </div>
      </el-col>
      <el-col :span="12">
        <div style="display: flex;align-items: center; justify-content: right;">
          <Search></Search>
          <ScreenFull></ScreenFull>
          <Setting></Setting>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.bar-main {
  width: 100%;
  padding: 8px 0;
}
// .icon-btn {
//   display: inline-block;
//   cursor: pointer;
//   padding: 6px;
//   font-size: 20px;
//   border-radius: 5px;
//   margin: 0 10px 0 0;
//   color: #78829D;
//   background-color: #FFFFFF;
//   transition: 0.1s;
// }

// .icon-btn:hover {
//   background-color: #F0F4F8;
//   color: #383f54;
// }
</style>
<style>
/* .rotate:hover {
  animation: rotate 0.3s linear forwards;
}

@keyframes rotate {
    from {
        transform: rotate(0deg);
    }
    to {
        transform: rotate(180deg);
    }
}

.swing:hover {
  animation: swing 0.3s linear forwards;
}
@keyframes swing {
    0% {
        transform: rotate(0deg);
    }
    25% {
        transform: rotate(15deg);
    }
    50% {
        transform: rotate(-15deg);
    }
    75% {
        transform: rotate(15deg);
    }
    100% {
        transform: rotate(0deg);
    }
}

.jump:hover {
  animation: jump 0.3s linear forwards;
}
@keyframes jump {
    0% {
        top: 0;
    }
    50% {
        top: -3px;
    }
    100% {
        top: 0;
    }
}

.scale:hover {
  animation: scaleAnimation 0.3s linear forwards;
}
@keyframes scaleAnimation {
    0% {
        transform: scale(1);
    }
    50% {
        transform: scale(1.15);
    }
    100% {
        transform: scale(1);
    }
} */
</style>