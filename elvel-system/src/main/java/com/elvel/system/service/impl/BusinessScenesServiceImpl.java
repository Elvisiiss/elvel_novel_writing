package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessScenes;
import com.elvel.system.mapper.BusinessScenesMapper;
import com.elvel.system.service.IBusinessScenesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 场景Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessScenesServiceImpl implements IBusinessScenesService
{
    @Autowired
    private BusinessScenesMapper businessScenesMapper;

    /**
     * 查询场景
     *
     * @param id 场景主键
     * @return 场景
     */
    @Override
    public BusinessScenes selectBusinessScenesById(Long id)
    {
        return businessScenesMapper.selectBusinessScenesById(id);
    }

    /**
     * 查询场景列表
     *
     * @param businessScenes 场景
     * @return 场景
     */
    @Override
    public List<BusinessScenes> selectBusinessScenesList(BusinessScenes businessScenes)
    {
        return businessScenesMapper.selectBusinessScenesList(businessScenes);
    }

    /**
     * 新增场景
     *
     * @param businessScenes 场景
     * @return 结果
     */
    @Override
    public int insertBusinessScenes(BusinessScenes businessScenes)
    {
        businessScenes.setCreateTime(DateUtils.getNowDate());
        return businessScenesMapper.insertBusinessScenes(businessScenes);
    }

    /**
     * 修改场景
     *
     * @param businessScenes 场景
     * @return 结果
     */
    @Override
    public int updateBusinessScenes(BusinessScenes businessScenes)
    {
        return businessScenesMapper.updateBusinessScenes(businessScenes);
    }

    /**
     * 批量删除场景
     *
     * @param ids 需要删除的场景主键
     * @return 结果
     */
    @Override
    public int deleteBusinessScenesByIds(Long[] ids)
    {
        return businessScenesMapper.deleteBusinessScenesByIds(ids);
    }

    /**
     * 删除场景信息
     *
     * @param id 场景主键
     * @return 结果
     */
    @Override
    public int deleteBusinessScenesById(Long id)
    {
        return businessScenesMapper.deleteBusinessScenesById(id);
    }
}
