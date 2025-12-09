import request from '@/utils/request'

// 查询决策选项列表
export function listOptions(query) {
  return request({
    url: '/system/options/list',
    method: 'get',
    params: query
  })
}

// 查询决策选项详细
export function getOptions(id) {
  return request({
    url: '/system/options/' + id,
    method: 'get'
  })
}

// 新增决策选项
export function addOptions(data) {
  return request({
    url: '/system/options',
    method: 'post',
    data: data
  })
}

// 修改决策选项
export function updateOptions(data) {
  return request({
    url: '/system/options',
    method: 'put',
    data: data
  })
}

// 删除决策选项
export function delOptions(id) {
  return request({
    url: '/system/options/' + id,
    method: 'delete'
  })
}
