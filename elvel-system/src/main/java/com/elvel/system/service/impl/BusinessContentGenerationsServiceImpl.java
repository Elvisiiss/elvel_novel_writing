package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessContentGenerations;
import com.elvel.system.mapper.BusinessContentGenerationsMapper;
import com.elvel.system.service.IBusinessContentGenerationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 内容生成记录Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessContentGenerationsServiceImpl implements IBusinessContentGenerationsService
{
    @Autowired
    private BusinessContentGenerationsMapper businessContentGenerationsMapper;

    /**
     * 查询内容生成记录
     *
     * @param id 内容生成记录主键
     * @return 内容生成记录
     */
    @Override
    public BusinessContentGenerations selectBusinessContentGenerationsById(Long id)
    {
        return businessContentGenerationsMapper.selectBusinessContentGenerationsById(id);
    }

    /**
     * 查询内容生成记录列表
     *
     * @param businessContentGenerations 内容生成记录
     * @return 内容生成记录
     */
    @Override
    public List<BusinessContentGenerations> selectBusinessContentGenerationsList(BusinessContentGenerations businessContentGenerations)
    {
        return businessContentGenerationsMapper.selectBusinessContentGenerationsList(businessContentGenerations);
    }

    /**
     * 新增内容生成记录
     *
     * @param businessContentGenerations 内容生成记录
     * @return 结果
     */
    @Override
    public int insertBusinessContentGenerations(BusinessContentGenerations businessContentGenerations)
    {
        businessContentGenerations.setCreateTime(DateUtils.getNowDate());
        return businessContentGenerationsMapper.insertBusinessContentGenerations(businessContentGenerations);
    }

    /**
     * 修改内容生成记录
     *
     * @param businessContentGenerations 内容生成记录
     * @return 结果
     */
    @Override
    public int updateBusinessContentGenerations(BusinessContentGenerations businessContentGenerations)
    {
        return businessContentGenerationsMapper.updateBusinessContentGenerations(businessContentGenerations);
    }

    /**
     * 批量删除内容生成记录
     *
     * @param ids 需要删除的内容生成记录主键
     * @return 结果
     */
    @Override
    public int deleteBusinessContentGenerationsByIds(Long[] ids)
    {
        return businessContentGenerationsMapper.deleteBusinessContentGenerationsByIds(ids);
    }

    /**
     * 删除内容生成记录信息
     *
     * @param id 内容生成记录主键
     * @return 结果
     */
    @Override
    public int deleteBusinessContentGenerationsById(Long id)
    {
        return businessContentGenerationsMapper.deleteBusinessContentGenerationsById(id);
    }
}
