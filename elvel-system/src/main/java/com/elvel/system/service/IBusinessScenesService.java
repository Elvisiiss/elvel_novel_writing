package com.elvel.system.service;

import java.util.List;
import com.elvel.system.domain.BusinessScenes;

/**
 * 场景Service接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface IBusinessScenesService
{
    /**
     * 查询场景
     *
     * @param id 场景主键
     * @return 场景
     */
    public BusinessScenes selectBusinessScenesById(Long id);

    /**
     * 查询场景列表
     *
     * @param businessScenes 场景
     * @return 场景集合
     */
    public List<BusinessScenes> selectBusinessScenesList(BusinessScenes businessScenes);

    /**
     * 新增场景
     *
     * @param businessScenes 场景
     * @return 结果
     */
    public int insertBusinessScenes(BusinessScenes businessScenes);

    /**
     * 修改场景
     *
     * @param businessScenes 场景
     * @return 结果
     */
    public int updateBusinessScenes(BusinessScenes businessScenes);

    /**
     * 批量删除场景
     *
     * @param ids 需要删除的场景主键集合
     * @return 结果
     */
    public int deleteBusinessScenesByIds(Long[] ids);

    /**
     * 删除场景信息
     *
     * @param id 场景主键
     * @return 结果
     */
    public int deleteBusinessScenesById(Long id);
}
