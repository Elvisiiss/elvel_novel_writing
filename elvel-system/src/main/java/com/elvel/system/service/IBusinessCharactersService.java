package com.elvel.system.service;

import java.util.List;
import com.elvel.system.domain.BusinessCharacters;

/**
 * 角色Service接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface IBusinessCharactersService
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
     * 批量删除角色
     *
     * @param ids 需要删除的角色主键集合
     * @return 结果
     */
    public int deleteBusinessCharactersByIds(Long[] ids);

    /**
     * 删除角色信息
     *
     * @param id 角色主键
     * @return 结果
     */
    public int deleteBusinessCharactersById(Long id);
}
