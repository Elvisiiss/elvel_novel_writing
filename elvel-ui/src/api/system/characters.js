import request from '@/utils/request'

// 查询角色列表
export function listCharacters(query) {
  return request({
    url: '/system/characters/list',
    method: 'get',
    params: query
  })
}

// 查询角色详细
export function getCharacters(id) {
  return request({
    url: '/system/characters/' + id,
    method: 'get'
  })
}

// 新增角色
export function addCharacters(data) {
  return request({
    url: '/system/characters',
    method: 'post',
    data: data
  })
}

// 修改角色
export function updateCharacters(data) {
  return request({
    url: '/system/characters',
    method: 'put',
    data: data
  })
}

// 删除角色
export function delCharacters(id) {
  return request({
    url: '/system/characters/' + id,
    method: 'delete'
  })
}
