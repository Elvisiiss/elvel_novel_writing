package com.elvel.system.mapper;

import java.util.List;
import com.elvel.system.domain.BusinessReadingRecords;

/**
 * 阅读记录Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface BusinessReadingRecordsMapper
{
    /**
     * 查询阅读记录
     *
     * @param id 阅读记录主键
     * @return 阅读记录
     */
    public BusinessReadingRecords selectBusinessReadingRecordsById(Long id);

    /**
     * 查询阅读记录列表
     *
     * @param businessReadingRecords 阅读记录
     * @return 阅读记录集合
     */
    public List<BusinessReadingRecords> selectBusinessReadingRecordsList(BusinessReadingRecords businessReadingRecords);

    /**
     * 新增阅读记录
     *
     * @param businessReadingRecords 阅读记录
     * @return 结果
     */
    public int insertBusinessReadingRecords(BusinessReadingRecords businessReadingRecords);

    /**
     * 修改阅读记录
     *
     * @param businessReadingRecords 阅读记录
     * @return 结果
     */
    public int updateBusinessReadingRecords(BusinessReadingRecords businessReadingRecords);

    /**
     * 删除阅读记录
     *
     * @param id 阅读记录主键
     * @return 结果
     */
    public int deleteBusinessReadingRecordsById(Long id);

    /**
     * 批量删除阅读记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessReadingRecordsByIds(Long[] ids);
}
