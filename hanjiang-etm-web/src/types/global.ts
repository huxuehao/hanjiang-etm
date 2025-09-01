// 分页参数类型
export interface PageType {
  total: number;
  current: number;
  size: number;
}

export const initPage = ():PageType => ({
  total: 0,
  current: 1,
  size: 20
})