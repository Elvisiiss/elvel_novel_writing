package com.elvel.system.service;

import java.util.List;
import com.elvel.system.domain.BusinessDecisionOptions;

/**
 * 决策选项Service接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface IBusinessDecisionOptionsService
{
    /**
     * 查询决策选项
     *
     * @param id 决策选项主键
     * @return 决策选项
     */
    public BusinessDecisionOptions selectBusinessDecisionOptionsById(Long id);

    /**
     * 查询决策选项列表
     *
     * @param businessDecisionOptions 决策选项
     * @return 决策选项集合
     */
    public List<BusinessDecisionOptions> selectBusinessDecisionOptionsList(BusinessDecisionOptions businessDecisionOptions);

    /**
     * 新增决策选项
     *
     * @param businessDecisionOptions 决策选项
     * @return 结果
     */
    public int insertBusinessDecisionOptions(BusinessDecisionOptions businessDecisionOptions);

    /**
     * 修改决策选项
     *
     * @param businessDecisionOptions 决策选项
     * @return 结果
     */
    public int updateBusinessDecisionOptions(BusinessDecisionOptions businessDecisionOptions);

    /**
     * 批量删除决策选项
     *
     * @param ids 需要删除的决策选项主键集合
     * @return 结果
     */
    public int deleteBusinessDecisionOptionsByIds(Long[] ids);

    /**
     * 删除决策选项信息
     *
     * @param id 决策选项主键
     * @return 结果
     */
    public int deleteBusinessDecisionOptionsById(Long id);
}
