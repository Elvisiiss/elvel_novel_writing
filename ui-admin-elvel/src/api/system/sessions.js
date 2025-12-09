import request from '@/utils/request'

// 查询创作会话列表
export function listSessions(query) {
  return request({
    url: '/system/sessions/list',
    method: 'get',
    params: query
  })
}

// 查询创作会话详细
export function getSessions(id) {
  return request({
    url: '/system/sessions/' + id,
    method: 'get'
  })
}

// 新增创作会话
export function addSessions(data) {
  return request({
    url: '/system/sessions',
    method: 'post',
    data: data
  })
}

// 修改创作会话
export function updateSessions(data) {
  return request({
    url: '/system/sessions',
    method: 'put',
    data: data
  })
}

// 删除创作会话
export function delSessions(id) {
  return request({
    url: '/system/sessions/' + id,
    method: 'delete'
  })
}
