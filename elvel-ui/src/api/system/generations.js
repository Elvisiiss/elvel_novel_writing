import request from '@/utils/request'

// 查询内容生成记录列表
export function listGenerations(query) {
  return request({
    url: '/system/generations/list',
    method: 'get',
    params: query
  })
}

// 查询内容生成记录详细
export function getGenerations(id) {
  return request({
    url: '/system/generations/' + id,
    method: 'get'
  })
}

// 新增内容生成记录
export function addGenerations(data) {
  return request({
    url: '/system/generations',
    method: 'post',
    data: data
  })
}

// 修改内容生成记录
export function updateGenerations(data) {
  return request({
    url: '/system/generations',
    method: 'put',
    data: data
  })
}

// 删除内容生成记录
export function delGenerations(id) {
  return request({
    url: '/system/generations/' + id,
    method: 'delete'
  })
}
