import request from '@/utils/request'

// 查询章节列表
export function listChapters(query) {
  return request({
    url: '/system/chapters/list',
    method: 'get',
    params: query
  })
}

// 查询章节详细
export function getChapters(id) {
  return request({
    url: '/system/chapters/' + id,
    method: 'get'
  })
}

// 新增章节
export function addChapters(data) {
  return request({
    url: '/system/chapters',
    method: 'post',
    data: data
  })
}

// 修改章节
export function updateChapters(data) {
  return request({
    url: '/system/chapters',
    method: 'put',
    data: data
  })
}

// 删除章节
export function delChapters(id) {
  return request({
    url: '/system/chapters/' + id,
    method: 'delete'
  })
}
