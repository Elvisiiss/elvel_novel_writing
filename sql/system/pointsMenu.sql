-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策点', '3', '1', 'points', 'system/points/index', 1, 0, 'C', '0', '0', 'system:points:list', '#', 'admin', sysdate(), '', null, '决策点菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策点查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:points:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策点新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:points:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策点修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:points:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策点删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:points:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('决策点导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:points:export',       '#', 'admin', sysdate(), '', null, '');