import request from '@/utils/request'

// 查询决策点列表
export function listPoints(query) {
  return request({
    url: '/system/points/list',
    method: 'get',
    params: query
  })
}

// 查询决策点详细
export function getPoints(id) {
  return request({
    url: '/system/points/' + id,
    method: 'get'
  })
}

// 新增决策点
export function addPoints(data) {
  return request({
    url: '/system/points',
    method: 'post',
    data: data
  })
}

// 修改决策点
export function updatePoints(data) {
  return request({
    url: '/system/points',
    method: 'put',
    data: data
  })
}

// 删除决策点
export function delPoints(id) {
  return request({
    url: '/system/points/' + id,
    method: 'delete'
  })
}
