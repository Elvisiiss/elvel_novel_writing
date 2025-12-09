package com.elvel.system.mapper;

import java.util.List;
import com.elvel.system.domain.BusinessCharacters;

/**
 * 角色Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface BusinessCharactersMapper
{
    /**
     * 查询角色
     *
     * @param id 角色主键
     * @return 角色
     */
    public BusinessCharacters selectBusinessCharactersById(Long id);

    /**
     * 查询角色列表
     *
     * @param businessCharacters 角色
     * @return 角色集合
     */
    public List<BusinessCharacters> selectBusinessCharactersList(BusinessCharacters businessCharacters);

    /**
     * 新增角色
     *
     * @param businessCharacters 角色
     * @return 结果
     */
    public int insertBusinessCharacters(BusinessCharacters businessCharacters);

    /**
     * 修改角色
     *
     * @param businessCharacters 角色
     * @return 结果
     */
    public int updateBusinessCharacters(BusinessCharacters businessCharacters);

    /**
     * 删除角色
     *
     * @param id 角色主键
     * @return 结果
     */
    public int deleteBusinessCharactersById(Long id);

    /**
     * 批量删除角色
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessCharactersByIds(Long[] ids);
}
