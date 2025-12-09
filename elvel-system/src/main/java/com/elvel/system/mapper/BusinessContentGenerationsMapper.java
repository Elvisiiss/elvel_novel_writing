package com.elvel.system.mapper;

import java.util.List;
import com.elvel.system.domain.BusinessContentGenerations;

/**
 * 内容生成记录Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface BusinessContentGenerationsMapper
{
    /**
     * 查询内容生成记录
     *
     * @param id 内容生成记录主键
     * @return 内容生成记录
     */
    public BusinessContentGenerations selectBusinessContentGenerationsById(Long id);

    /**
     * 查询内容生成记录列表
     *
     * @param businessContentGenerations 内容生成记录
     * @return 内容生成记录集合
     */
    public List<BusinessContentGenerations> selectBusinessContentGenerationsList(BusinessContentGenerations businessContentGenerations);

    /**
     * 新增内容生成记录
     *
     * @param businessContentGenerations 内容生成记录
     * @return 结果
     */
    public int insertBusinessContentGenerations(BusinessContentGenerations businessContentGenerations);

    /**
     * 修改内容生成记录
     *
     * @param businessContentGenerations 内容生成记录
     * @return 结果
     */
    public int updateBusinessContentGenerations(BusinessContentGenerations businessContentGenerations);

    /**
     * 删除内容生成记录
     *
     * @param id 内容生成记录主键
     * @return 结果
     */
    public int deleteBusinessContentGenerationsById(Long id);

    /**
     * 批量删除内容生成记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessContentGenerationsByIds(Long[] ids);
}
