package com.elvel.system.mapper;

import java.util.List;
import com.elvel.system.domain.BusinessComments;

/**
 * 评论Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface BusinessCommentsMapper
{
    /**
     * 查询评论
     *
     * @param id 评论主键
     * @return 评论
     */
    public BusinessComments selectBusinessCommentsById(Long id);

    /**
     * 查询评论列表
     *
     * @param businessComments 评论
     * @return 评论集合
     */
    public List<BusinessComments> selectBusinessCommentsList(BusinessComments businessComments);

    /**
     * 新增评论
     *
     * @param businessComments 评论
     * @return 结果
     */
    public int insertBusinessComments(BusinessComments businessComments);

    /**
     * 修改评论
     *
     * @param businessComments 评论
     * @return 结果
     */
    public int updateBusinessComments(BusinessComments businessComments);

    /**
     * 删除评论
     *
     * @param id 评论主键
     * @return 结果
     */
    public int deleteBusinessCommentsById(Long id);

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessCommentsByIds(Long[] ids);
}
