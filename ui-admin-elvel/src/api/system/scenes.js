import request from '@/utils/request'

// 查询场景列表
export function listScenes(query) {
  return request({
    url: '/system/scenes/list',
    method: 'get',
    params: query
  })
}

// 查询场景详细
export function getScenes(id) {
  return request({
    url: '/system/scenes/' + id,
    method: 'get'
  })
}

// 新增场景
export function addScenes(data) {
  return request({
    url: '/system/scenes',
    method: 'post',
    data: data
  })
}

// 修改场景
export function updateScenes(data) {
  return request({
    url: '/system/scenes',
    method: 'put',
    data: data
  })
}

// 删除场景
export function delScenes(id) {
  return request({
    url: '/system/scenes/' + id,
    method: 'delete'
  })
}
