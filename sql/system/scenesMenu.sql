-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('场景', '3', '1', 'scenes', 'system/scenes/index', 1, 0, 'C', '0', '0', 'system:scenes:list', '#', 'admin', sysdate(), '', null, '场景菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('场景查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:scenes:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('场景新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:scenes:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('场景修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:scenes:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('场景删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:scenes:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('场景导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:scenes:export',       '#', 'admin', sysdate(), '', null, '');