-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('书籍大纲', '3', '1', 'outlines', 'system/outlines/index', 1, 0, 'C', '0', '0', 'system:outlines:list', '#', 'admin', sysdate(), '', null, '书籍大纲菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('书籍大纲查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:outlines:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('书籍大纲新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:outlines:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('书籍大纲修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:outlines:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('书籍大纲删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:outlines:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('书籍大纲导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:outlines:export',       '#', 'admin', sysdate(), '', null, '');