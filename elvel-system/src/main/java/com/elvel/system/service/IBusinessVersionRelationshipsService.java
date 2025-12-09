package com.elvel.system.service;

import java.util.List;
import com.elvel.system.domain.BusinessVersionRelationships;

/**
 * 版本关系Service接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface IBusinessVersionRelationshipsService
{
    /**
     * 查询版本关系
     *
     * @param id 版本关系主键
     * @return 版本关系
     */
    public BusinessVersionRelationships selectBusinessVersionRelationshipsById(Long id);

    /**
     * 查询版本关系列表
     *
     * @param businessVersionRelationships 版本关系
     * @return 版本关系集合
     */
    public List<BusinessVersionRelationships> selectBusinessVersionRelationshipsList(BusinessVersionRelationships businessVersionRelationships);

    /**
     * 新增版本关系
     *
     * @param businessVersionRelationships 版本关系
     * @return 结果
     */
    public int insertBusinessVersionRelationships(BusinessVersionRelationships businessVersionRelationships);

    /**
     * 修改版本关系
     *
     * @param businessVersionRelationships 版本关系
     * @return 结果
     */
    public int updateBusinessVersionRelationships(BusinessVersionRelationships businessVersionRelationships);

    /**
     * 批量删除版本关系
     *
     * @param ids 需要删除的版本关系主键集合
     * @return 结果
     */
    public int deleteBusinessVersionRelationshipsByIds(Long[] ids);

    /**
     * 删除版本关系信息
     *
     * @param id 版本关系主键
     * @return 结果
     */
    public int deleteBusinessVersionRelationshipsById(Long id);
}
