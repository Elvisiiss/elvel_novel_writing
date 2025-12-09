package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessCharacters;
import com.elvel.system.mapper.BusinessCharactersMapper;
import com.elvel.system.service.IBusinessCharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 角色Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessCharactersServiceImpl implements IBusinessCharactersService
{
    @Autowired
    private BusinessCharactersMapper businessCharactersMapper;

    /**
     * 查询角色
     *
     * @param id 角色主键
     * @return 角色
     */
    @Override
    public BusinessCharacters selectBusinessCharactersById(Long id)
    {
        return businessCharactersMapper.selectBusinessCharactersById(id);
    }

    /**
     * 查询角色列表
     *
     * @param businessCharacters 角色
     * @return 角色
     */
    @Override
    public List<BusinessCharacters> selectBusinessCharactersList(BusinessCharacters businessCharacters)
    {
        return businessCharactersMapper.selectBusinessCharactersList(businessCharacters);
    }

    /**
     * 新增角色
     *
     * @param businessCharacters 角色
     * @return 结果
     */
    @Override
    public int insertBusinessCharacters(BusinessCharacters businessCharacters)
    {
        businessCharacters.setCreateTime(DateUtils.getNowDate());
        return businessCharactersMapper.insertBusinessCharacters(businessCharacters);
    }

    /**
     * 修改角色
     *
     * @param businessCharacters 角色
     * @return 结果
     */
    @Override
    public int updateBusinessCharacters(BusinessCharacters businessCharacters)
    {
        businessCharacters.setUpdateTime(DateUtils.getNowDate());
        return businessCharactersMapper.updateBusinessCharacters(businessCharacters);
    }

    /**
     * 批量删除角色
     *
     * @param ids 需要删除的角色主键
     * @return 结果
     */
    @Override
    public int deleteBusinessCharactersByIds(Long[] ids)
    {
        return businessCharactersMapper.deleteBusinessCharactersByIds(ids);
    }

    /**
     * 删除角色信息
     *
     * @param id 角色主键
     * @return 结果
     */
    @Override
    public int deleteBusinessCharactersById(Long id)
    {
        return businessCharactersMapper.deleteBusinessCharactersById(id);
    }
}
