package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessDecisionOptions;
import com.elvel.system.mapper.BusinessDecisionOptionsMapper;
import com.elvel.system.service.IBusinessDecisionOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 决策选项Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessDecisionOptionsServiceImpl implements IBusinessDecisionOptionsService
{
    @Autowired
    private BusinessDecisionOptionsMapper businessDecisionOptionsMapper;

    /**
     * 查询决策选项
     *
     * @param id 决策选项主键
     * @return 决策选项
     */
    @Override
    public BusinessDecisionOptions selectBusinessDecisionOptionsById(Long id)
    {
        return businessDecisionOptionsMapper.selectBusinessDecisionOptionsById(id);
    }

    /**
     * 查询决策选项列表
     *
     * @param businessDecisionOptions 决策选项
     * @return 决策选项
     */
    @Override
    public List<BusinessDecisionOptions> selectBusinessDecisionOptionsList(BusinessDecisionOptions businessDecisionOptions)
    {
        return businessDecisionOptionsMapper.selectBusinessDecisionOptionsList(businessDecisionOptions);
    }

    /**
     * 新增决策选项
     *
     * @param businessDecisionOptions 决策选项
     * @return 结果
     */
    @Override
    public int insertBusinessDecisionOptions(BusinessDecisionOptions businessDecisionOptions)
    {
        businessDecisionOptions.setCreateTime(DateUtils.getNowDate());
        return businessDecisionOptionsMapper.insertBusinessDecisionOptions(businessDecisionOptions);
    }

    /**
     * 修改决策选项
     *
     * @param businessDecisionOptions 决策选项
     * @return 结果
     */
    @Override
    public int updateBusinessDecisionOptions(BusinessDecisionOptions businessDecisionOptions)
    {
        return businessDecisionOptionsMapper.updateBusinessDecisionOptions(businessDecisionOptions);
    }

    /**
     * 批量删除决策选项
     *
     * @param ids 需要删除的决策选项主键
     * @return 结果
     */
    @Override
    public int deleteBusinessDecisionOptionsByIds(Long[] ids)
    {
        return businessDecisionOptionsMapper.deleteBusinessDecisionOptionsByIds(ids);
    }

    /**
     * 删除决策选项信息
     *
     * @param id 决策选项主键
     * @return 结果
     */
    @Override
    public int deleteBusinessDecisionOptionsById(Long id)
    {
        return businessDecisionOptionsMapper.deleteBusinessDecisionOptionsById(id);
    }
}
