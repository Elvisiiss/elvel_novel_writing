package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessVersionRelationships;
import com.elvel.system.mapper.BusinessVersionRelationshipsMapper;
import com.elvel.system.service.IBusinessVersionRelationshipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 版本关系Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessVersionRelationshipsServiceImpl implements IBusinessVersionRelationshipsService
{
    @Autowired
    private BusinessVersionRelationshipsMapper businessVersionRelationshipsMapper;

    /**
     * 查询版本关系
     *
     * @param id 版本关系主键
     * @return 版本关系
     */
    @Override
    public BusinessVersionRelationships selectBusinessVersionRelationshipsById(Long id)
    {
        return businessVersionRelationshipsMapper.selectBusinessVersionRelationshipsById(id);
    }

    /**
     * 查询版本关系列表
     *
     * @param businessVersionRelationships 版本关系
     * @return 版本关系
     */
    @Override
    public List<BusinessVersionRelationships> selectBusinessVersionRelationshipsList(BusinessVersionRelationships businessVersionRelationships)
    {
        return businessVersionRelationshipsMapper.selectBusinessVersionRelationshipsList(businessVersionRelationships);
    }

    /**
     * 新增版本关系
     *
     * @param businessVersionRelationships 版本关系
     * @return 结果
     */
    @Override
    public int insertBusinessVersionRelationships(BusinessVersionRelationships businessVersionRelationships)
    {
        businessVersionRelationships.setCreateTime(DateUtils.getNowDate());
        return businessVersionRelationshipsMapper.insertBusinessVersionRelationships(businessVersionRelationships);
    }

    /**
     * 修改版本关系
     *
     * @param businessVersionRelationships 版本关系
     * @return 结果
     */
    @Override
    public int updateBusinessVersionRelationships(BusinessVersionRelationships businessVersionRelationships)
    {
        return businessVersionRelationshipsMapper.updateBusinessVersionRelationships(businessVersionRelationships);
    }

    /**
     * 批量删除版本关系
     *
     * @param ids 需要删除的版本关系主键
     * @return 结果
     */
    @Override
    public int deleteBusinessVersionRelationshipsByIds(Long[] ids)
    {
        return businessVersionRelationshipsMapper.deleteBusinessVersionRelationshipsByIds(ids);
    }

    /**
     * 删除版本关系信息
     *
     * @param id 版本关系主键
     * @return 结果
     */
    @Override
    public int deleteBusinessVersionRelationshipsById(Long id)
    {
        return businessVersionRelationshipsMapper.deleteBusinessVersionRelationshipsById(id);
    }
}
