import request from '@/utils/request'

// 查询版本关系列表
export function listRelationships(query) {
  return request({
    url: '/system/relationships/list',
    method: 'get',
    params: query
  })
}

// 查询版本关系详细
export function getRelationships(id) {
  return request({
    url: '/system/relationships/' + id,
    method: 'get'
  })
}

// 新增版本关系
export function addRelationships(data) {
  return request({
    url: '/system/relationships',
    method: 'post',
    data: data
  })
}

// 修改版本关系
export function updateRelationships(data) {
  return request({
    url: '/system/relationships',
    method: 'put',
    data: data
  })
}

// 删除版本关系
export function delRelationships(id) {
  return request({
    url: '/system/relationships/' + id,
    method: 'delete'
  })
}
