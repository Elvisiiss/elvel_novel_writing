package com.elvel.system.mapper;

import java.util.List;
import com.elvel.system.domain.BusinessScenes;

/**
 * 场景Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface BusinessScenesMapper
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
     * 删除场景
     *
     * @param id 场景主键
     * @return 结果
     */
    public int deleteBusinessScenesById(Long id);

    /**
     * 批量删除场景
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessScenesByIds(Long[] ids);
}
