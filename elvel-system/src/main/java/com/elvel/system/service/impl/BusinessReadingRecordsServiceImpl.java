package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessReadingRecords;
import com.elvel.system.mapper.BusinessReadingRecordsMapper;
import com.elvel.system.service.IBusinessReadingRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 阅读记录Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessReadingRecordsServiceImpl implements IBusinessReadingRecordsService
{
    @Autowired
    private BusinessReadingRecordsMapper businessReadingRecordsMapper;

    /**
     * 查询阅读记录
     *
     * @param id 阅读记录主键
     * @return 阅读记录
     */
    @Override
    public BusinessReadingRecords selectBusinessReadingRecordsById(Long id)
    {
        return businessReadingRecordsMapper.selectBusinessReadingRecordsById(id);
    }

    /**
     * 查询阅读记录列表
     *
     * @param businessReadingRecords 阅读记录
     * @return 阅读记录
     */
    @Override
    public List<BusinessReadingRecords> selectBusinessReadingRecordsList(BusinessReadingRecords businessReadingRecords)
    {
        return businessReadingRecordsMapper.selectBusinessReadingRecordsList(businessReadingRecords);
    }

    /**
     * 新增阅读记录
     *
     * @param businessReadingRecords 阅读记录
     * @return 结果
     */
    @Override
    public int insertBusinessReadingRecords(BusinessReadingRecords businessReadingRecords)
    {
        businessReadingRecords.setCreateTime(DateUtils.getNowDate());
        return businessReadingRecordsMapper.insertBusinessReadingRecords(businessReadingRecords);
    }

    /**
     * 修改阅读记录
     *
     * @param businessReadingRecords 阅读记录
     * @return 结果
     */
    @Override
    public int updateBusinessReadingRecords(BusinessReadingRecords businessReadingRecords)
    {
        return businessReadingRecordsMapper.updateBusinessReadingRecords(businessReadingRecords);
    }

    /**
     * 批量删除阅读记录
     *
     * @param ids 需要删除的阅读记录主键
     * @return 结果
     */
    @Override
    public int deleteBusinessReadingRecordsByIds(Long[] ids)
    {
        return businessReadingRecordsMapper.deleteBusinessReadingRecordsByIds(ids);
    }

    /**
     * 删除阅读记录信息
     *
     * @param id 阅读记录主键
     * @return 结果
     */
    @Override
    public int deleteBusinessReadingRecordsById(Long id)
    {
        return businessReadingRecordsMapper.deleteBusinessReadingRecordsById(id);
    }
}
