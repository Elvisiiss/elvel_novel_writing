package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessDecisionPoints;
import com.elvel.system.mapper.BusinessDecisionPointsMapper;
import com.elvel.system.service.IBusinessDecisionPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 决策点Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessDecisionPointsServiceImpl implements IBusinessDecisionPointsService
{
    @Autowired
    private BusinessDecisionPointsMapper businessDecisionPointsMapper;

    /**
     * 查询决策点
     *
     * @param id 决策点主键
     * @return 决策点
     */
    @Override
    public BusinessDecisionPoints selectBusinessDecisionPointsById(Long id)
    {
        return businessDecisionPointsMapper.selectBusinessDecisionPointsById(id);
    }

    /**
     * 查询决策点列表
     *
     * @param businessDecisionPoints 决策点
     * @return 决策点
     */
    @Override
    public List<BusinessDecisionPoints> selectBusinessDecisionPointsList(BusinessDecisionPoints businessDecisionPoints)
    {
        return businessDecisionPointsMapper.selectBusinessDecisionPointsList(businessDecisionPoints);
    }

    /**
     * 新增决策点
     *
     * @param businessDecisionPoints 决策点
     * @return 结果
     */
    @Override
    public int insertBusinessDecisionPoints(BusinessDecisionPoints businessDecisionPoints)
    {
        businessDecisionPoints.setCreateTime(DateUtils.getNowDate());
        return businessDecisionPointsMapper.insertBusinessDecisionPoints(businessDecisionPoints);
    }

    /**
     * 修改决策点
     *
     * @param businessDecisionPoints 决策点
     * @return 结果
     */
    @Override
    public int updateBusinessDecisionPoints(BusinessDecisionPoints businessDecisionPoints)
    {
        return businessDecisionPointsMapper.updateBusinessDecisionPoints(businessDecisionPoints);
    }

    /**
     * 批量删除决策点
     *
     * @param ids 需要删除的决策点主键
     * @return 结果
     */
    @Override
    public int deleteBusinessDecisionPointsByIds(Long[] ids)
    {
        return businessDecisionPointsMapper.deleteBusinessDecisionPointsByIds(ids);
    }

    /**
     * 删除决策点信息
     *
     * @param id 决策点主键
     * @return 结果
     */
    @Override
    public int deleteBusinessDecisionPointsById(Long id)
    {
        return businessDecisionPointsMapper.deleteBusinessDecisionPointsById(id);
    }
}
