import request from '@/utils/request'

// 查询书籍主列表
export function listBooks(query) {
  return request({
    url: '/system/books/list',
    method: 'get',
    params: query
  })
}

// 查询书籍主详细
export function getBooks(id) {
  return request({
    url: '/system/books/' + id,
    method: 'get'
  })
}

// 新增书籍主
export function addBooks(data) {
  return request({
    url: '/system/books',
    method: 'post',
    data: data
  })
}

// 修改书籍主
export function updateBooks(data) {
  return request({
    url: '/system/books',
    method: 'put',
    data: data
  })
}

// 删除书籍主
export function delBooks(id) {
  return request({
    url: '/system/books/' + id,
    method: 'delete'
  })
}
