package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessBookOutlines;
import com.elvel.system.mapper.BusinessBookOutlinesMapper;
import com.elvel.system.service.IBusinessBookOutlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 书籍大纲Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessBookOutlinesServiceImpl implements IBusinessBookOutlinesService
{
    @Autowired
    private BusinessBookOutlinesMapper businessBookOutlinesMapper;

    /**
     * 查询书籍大纲
     *
     * @param id 书籍大纲主键
     * @return 书籍大纲
     */
    @Override
    public BusinessBookOutlines selectBusinessBookOutlinesById(Long id)
    {
        return businessBookOutlinesMapper.selectBusinessBookOutlinesById(id);
    }

    /**
     * 查询书籍大纲列表
     *
     * @param businessBookOutlines 书籍大纲
     * @return 书籍大纲
     */
    @Override
    public List<BusinessBookOutlines> selectBusinessBookOutlinesList(BusinessBookOutlines businessBookOutlines)
    {
        return businessBookOutlinesMapper.selectBusinessBookOutlinesList(businessBookOutlines);
    }

    /**
     * 新增书籍大纲
     *
     * @param businessBookOutlines 书籍大纲
     * @return 结果
     */
    @Override
    public int insertBusinessBookOutlines(BusinessBookOutlines businessBookOutlines)
    {
        businessBookOutlines.setCreateTime(DateUtils.getNowDate());
        return businessBookOutlinesMapper.insertBusinessBookOutlines(businessBookOutlines);
    }

    /**
     * 修改书籍大纲
     *
     * @param businessBookOutlines 书籍大纲
     * @return 结果
     */
    @Override
    public int updateBusinessBookOutlines(BusinessBookOutlines businessBookOutlines)
    {
        return businessBookOutlinesMapper.updateBusinessBookOutlines(businessBookOutlines);
    }

    /**
     * 批量删除书籍大纲
     *
     * @param ids 需要删除的书籍大纲主键
     * @return 结果
     */
    @Override
    public int deleteBusinessBookOutlinesByIds(Long[] ids)
    {
        return businessBookOutlinesMapper.deleteBusinessBookOutlinesByIds(ids);
    }

    /**
     * 删除书籍大纲信息
     *
     * @param id 书籍大纲主键
     * @return 结果
     */
    @Override
    public int deleteBusinessBookOutlinesById(Long id)
    {
        return businessBookOutlinesMapper.deleteBusinessBookOutlinesById(id);
    }
}
