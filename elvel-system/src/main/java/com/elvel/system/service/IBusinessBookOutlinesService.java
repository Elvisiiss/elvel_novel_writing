package com.elvel.system.service;

import java.util.List;
import com.elvel.system.domain.BusinessBookOutlines;

/**
 * 书籍大纲Service接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface IBusinessBookOutlinesService
{
    /**
     * 查询书籍大纲
     *
     * @param id 书籍大纲主键
     * @return 书籍大纲
     */
    public BusinessBookOutlines selectBusinessBookOutlinesById(Long id);

    /**
     * 查询书籍大纲列表
     *
     * @param businessBookOutlines 书籍大纲
     * @return 书籍大纲集合
     */
    public List<BusinessBookOutlines> selectBusinessBookOutlinesList(BusinessBookOutlines businessBookOutlines);

    /**
     * 新增书籍大纲
     *
     * @param businessBookOutlines 书籍大纲
     * @return 结果
     */
    public int insertBusinessBookOutlines(BusinessBookOutlines businessBookOutlines);

    /**
     * 修改书籍大纲
     *
     * @param businessBookOutlines 书籍大纲
     * @return 结果
     */
    public int updateBusinessBookOutlines(BusinessBookOutlines businessBookOutlines);

    /**
     * 批量删除书籍大纲
     *
     * @param ids 需要删除的书籍大纲主键集合
     * @return 结果
     */
    public int deleteBusinessBookOutlinesByIds(Long[] ids);

    /**
     * 删除书籍大纲信息
     *
     * @param id 书籍大纲主键
     * @return 结果
     */
    public int deleteBusinessBookOutlinesById(Long id);
}
