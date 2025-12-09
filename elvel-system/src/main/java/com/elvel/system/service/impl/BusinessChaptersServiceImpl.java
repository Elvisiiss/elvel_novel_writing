package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessChapters;
import com.elvel.system.mapper.BusinessChaptersMapper;
import com.elvel.system.service.IBusinessChaptersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 章节Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessChaptersServiceImpl implements IBusinessChaptersService
{
    @Autowired
    private BusinessChaptersMapper businessChaptersMapper;

    /**
     * 查询章节
     *
     * @param id 章节主键
     * @return 章节
     */
    @Override
    public BusinessChapters selectBusinessChaptersById(Long id)
    {
        return businessChaptersMapper.selectBusinessChaptersById(id);
    }

    /**
     * 查询章节列表
     *
     * @param businessChapters 章节
     * @return 章节
     */
    @Override
    public List<BusinessChapters> selectBusinessChaptersList(BusinessChapters businessChapters)
    {
        return businessChaptersMapper.selectBusinessChaptersList(businessChapters);
    }

    /**
     * 新增章节
     *
     * @param businessChapters 章节
     * @return 结果
     */
    @Override
    public int insertBusinessChapters(BusinessChapters businessChapters)
    {
        businessChapters.setCreateTime(DateUtils.getNowDate());
        return businessChaptersMapper.insertBusinessChapters(businessChapters);
    }

    /**
     * 修改章节
     *
     * @param businessChapters 章节
     * @return 结果
     */
    @Override
    public int updateBusinessChapters(BusinessChapters businessChapters)
    {
        businessChapters.setUpdateTime(DateUtils.getNowDate());
        return businessChaptersMapper.updateBusinessChapters(businessChapters);
    }

    /**
     * 批量删除章节
     *
     * @param ids 需要删除的章节主键
     * @return 结果
     */
    @Override
    public int deleteBusinessChaptersByIds(Long[] ids)
    {
        return businessChaptersMapper.deleteBusinessChaptersByIds(ids);
    }

    /**
     * 删除章节信息
     *
     * @param id 章节主键
     * @return 结果
     */
    @Override
    public int deleteBusinessChaptersById(Long id)
    {
        return businessChaptersMapper.deleteBusinessChaptersById(id);
    }
}
