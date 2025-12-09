-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策选项', '3', '1', 'options', 'system/options/index', 1, 0, 'C', '0', '0', 'system:options:list', '#', 'admin', sysdate(), '', null, '决策选项菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策选项查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:options:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策选项新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:options:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策选项修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:options:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策选项删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:options:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策选项导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:options:export',       '#', 'admin', sysdate(), '', null, '');