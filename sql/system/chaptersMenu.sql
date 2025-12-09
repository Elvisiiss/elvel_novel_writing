-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('章节', '3', '1', 'chapters', 'system/chapters/index', 1, 0, 'C', '0', '0', 'system:chapters:list', '#', 'admin', sysdate(), '', null, '章节菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('章节查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:chapters:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('章节新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:chapters:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('章节修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:chapters:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('章节删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:chapters:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('章节导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:chapters:export',       '#', 'admin', sysdate(), '', null, '');