-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('版本关系', '3', '1', 'relationships', 'system/relationships/index', 1, 0, 'C', '0', '0', 'system:relationships:list', '#', 'admin', sysdate(), '', null, '版本关系菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('版本关系查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:relationships:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('版本关系新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:relationships:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('版本关系修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:relationships:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('版本关系删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:relationships:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('版本关系导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:relationships:export',       '#', 'admin', sysdate(), '', null, '');