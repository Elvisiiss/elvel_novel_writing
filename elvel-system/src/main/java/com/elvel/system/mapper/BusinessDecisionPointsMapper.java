package com.elvel.system.mapper;

import java.util.List;
import com.elvel.system.domain.BusinessDecisionPoints;

/**
 * 决策点Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface BusinessDecisionPointsMapper
{
    /**
     * 查询决策点
     *
     * @param id 决策点主键
     * @return 决策点
     */
    public BusinessDecisionPoints selectBusinessDecisionPointsById(Long id);

    /**
     * 查询决策点列表
     *
     * @param businessDecisionPoints 决策点
     * @return 决策点集合
     */
    public List<BusinessDecisionPoints> selectBusinessDecisionPointsList(BusinessDecisionPoints businessDecisionPoints);

    /**
     * 新增决策点
     *
     * @param businessDecisionPoints 决策点
     * @return 结果
     */
    public int insertBusinessDecisionPoints(BusinessDecisionPoints businessDecisionPoints);

    /**
     * 修改决策点
     *
     * @param businessDecisionPoints 决策点
     * @return 结果
     */
    public int updateBusinessDecisionPoints(BusinessDecisionPoints businessDecisionPoints);

    /**
     * 删除决策点
     *
     * @param id 决策点主键
     * @return 结果
     */
    public int deleteBusinessDecisionPointsById(Long id);

    /**
     * 批量删除决策点
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessDecisionPointsByIds(Long[] ids);
}
