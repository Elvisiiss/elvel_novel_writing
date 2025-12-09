package com.elvel.system.mapper;

import java.util.List;
import com.elvel.system.domain.BusinessChapters;

/**
 * 章节Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface BusinessChaptersMapper
{
    /**
     * 查询章节
     *
     * @param id 章节主键
     * @return 章节
     */
    public BusinessChapters selectBusinessChaptersById(Long id);

    /**
     * 查询章节列表
     *
     * @param businessChapters 章节
     * @return 章节集合
     */
    public List<BusinessChapters> selectBusinessChaptersList(BusinessChapters businessChapters);

    /**
     * 新增章节
     *
     * @param businessChapters 章节
     * @return 结果
     */
    public int insertBusinessChapters(BusinessChapters businessChapters);

    /**
     * 修改章节
     *
     * @param businessChapters 章节
     * @return 结果
     */
    public int updateBusinessChapters(BusinessChapters businessChapters);

    /**
     * 删除章节
     *
     * @param id 章节主键
     * @return 结果
     */
    public int deleteBusinessChaptersById(Long id);

    /**
     * 批量删除章节
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessChaptersByIds(Long[] ids);
}
