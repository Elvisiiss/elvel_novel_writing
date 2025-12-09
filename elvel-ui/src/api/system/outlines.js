import request from '@/utils/request'

// 查询书籍大纲列表
export function listOutlines(query) {
  return request({
    url: '/system/outlines/list',
    method: 'get',
    params: query
  })
}

// 查询书籍大纲详细
export function getOutlines(id) {
  return request({
    url: '/system/outlines/' + id,
    method: 'get'
  })
}

// 新增书籍大纲
export function addOutlines(data) {
  return request({
    url: '/system/outlines',
    method: 'post',
    data: data
  })
}

// 修改书籍大纲
export function updateOutlines(data) {
  return request({
    url: '/system/outlines',
    method: 'put',
    data: data
  })
}

// 删除书籍大纲
export function delOutlines(id) {
  return request({
    url: '/system/outlines/' + id,
    method: 'delete'
  })
}
