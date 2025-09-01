<script setup>
import { ref, computed, onBeforeUnmount } from "vue";

const props = defineProps({
  // 支持水平和垂直切割
  direction: {
    type: String,
    default: "horizontal",
    validator: (v) => ["horizontal", "vertical"].includes(v),
  },
  // 初始宽度/高度（单位px）
  initialSize: {
    type: Number,
    required: true,
  },
  // 最小宽度/高度（单位px）
  minSize: {
    type: Number,
    default: 0,
  },
  // 最大宽度/高度（单位px）
  maxSize: {
    type: Number,
    default: null,
  },
  // 是否支持拖拽
  move: {
    type: Boolean,
    default: true,
  },
});

const size = ref(props.initialSize);
const isDragging = ref(false);
const startPosition = ref(0);
const startSize = ref(0);

const canDrag = computed(() => {
  return (
      props.maxSize === null ||
      props.maxSize !== props.initialSize ||
      props.minSize !== props.initialSize
  );
});

const firstPaneStyle = computed(() => {
  const style = {};
  const dimension = props.direction === "horizontal" ? "width" : "height";
  const capitalized = dimension.replace(/^\w/, (c) => c.toUpperCase());

  style[dimension] = `${size.value}px`;
  style[`min${capitalized}`] = `${props.minSize}px`;

  if (props.maxSize !== null) {
    style[`max${capitalized}`] = `${props.maxSize}px`;
  }
  if (!props.move) {
    style["transition"] = "0.3s";
  }
  return style;
});

const secondPaneStyle = computed(() => ({
  flex: 1,
  overflow: "hidden",
}));

const triggerFold = (isFold = false) => {
  if(isFold) {
    size.value = props.minSize
  } else {
    size.value = props.maxSize
  }
};

let cleanup = null;

function setCursor(e) {
  if (!canDrag.value) return;
  if (!props.move) return;
  e.target.style.cursor =
      props.direction === "horizontal" ? "col-resize" : "row-resize";
}

function resetCursor(e) {
  e.target.style.cursor = "default";
}

function startDrag(e) {
  if (!canDrag.value) return;
  if (!props.move) return;

  isDragging.value = true;
  startPosition.value = props.direction === "horizontal" ? e.clientX : e.clientY;
  startSize.value = size.value;

  const getCurrentPosition = (e) =>
      props.direction === "horizontal" ? e.clientX : e.clientY;

  const moveHandler = (e) => {
    if (!isDragging.value) return;
    const current = getCurrentPosition(e);
    const delta = current - startPosition.value;
    let newSize = startSize.value + delta;

    // 应用双边界限制
    if (newSize < props.minSize) {
      newSize = props.minSize;
    } else if (props.maxSize !== null && newSize > props.maxSize) {
      newSize = props.maxSize;
    }

    size.value = newSize;
  };

  const upHandler = () => {
    isDragging.value = false;
    document.removeEventListener("mousemove", moveHandler);
    document.removeEventListener("mouseup", upHandler);
    document.body.style.removeProperty("user-select");
    cleanup = null;
  };

  document.addEventListener("mousemove", moveHandler);
  document.addEventListener("mouseup", upHandler);
  document.body.style.userSelect = "none";
  cleanup = upHandler;
}

onBeforeUnmount(() => {
  if (cleanup) {
    cleanup();
  }
});

defineExpose({triggerFold})
</script>

<template>
  <div class="resize-container" :class="direction">
    <div class="first-pane" :style="firstPaneStyle">
      <slot name="first"></slot>
    </div>
    <div
        class="resize-trigger"
        :class="move ? 'bar' : ''"
        @mousedown.prevent="startDrag"
        @mouseenter="setCursor"
        @mouseleave="resetCursor"
    ></div>
    <div class="second-pane" :style="secondPaneStyle">
      <slot name="second"></slot>
    </div>
  </div>
</template>

<style scoped lang="scss">
.resize-container {
  display: flex;
  position: relative;
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.horizontal {
  flex-direction: row;
}

.vertical {
  flex-direction: column;
}

.resize-trigger {
  position: relative;
  flex-shrink: 0;
  z-index: 10;
  background: transparent;
  transition: background-color 0.2s;
}

/* 水平布局样式 */
.horizontal .resize-trigger {
  width: 10px;  /* 总热区宽度 */
  margin: 0 -0px; /* 扩展热区 */

}

.horizontal .bar {
  cursor: col-resize;
}

/* 垂直布局样式 */
.vertical .resize-trigger {
  height: 10px;  /* 总热区高度 */
  margin: -0px 0; /* 扩展热区 */
}

.vertical .bar {
  cursor: col-resize;
}

/* 拖拽条视觉元素（实际可见部分） */
.bar::after {
  content: '';
  position: absolute;
  background: #e7e7e7;
  transition: all 0.2s;
}

.horizontal .resize-trigger::after {
  width: 4px;
  height: 60px;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  border-radius: 2px;
}

.vertical .resize-trigger::after {
  height: 4px;
  width: 60px;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  border-radius: 2px;
}

/* 悬停效果 */
.resize-trigger:hover::after {
  background: #c4c4c4;
}

.first-pane {
  overflow: hidden;
  flex-shrink: 0;
}

.second-pane {
  flex: 1;
  min-width: 0;
  min-height: 0;
  overflow: hidden;
}

/* 创建视觉间隔 */
.horizontal .first-pane {
  margin-right: 0;  /* 间隔尺寸 */
}
.horizontal .second-pane {
  margin-left: 0;  /* 间隔尺寸 */
}

.vertical .first-pane {
  margin-bottom: 0;  /* 间隔尺寸 */
}
.vertical .second-pane {
  margin-top: 0;  /* 间隔尺寸 */
}
</style>
