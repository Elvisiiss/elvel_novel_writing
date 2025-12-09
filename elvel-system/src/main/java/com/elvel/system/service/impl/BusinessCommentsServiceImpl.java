package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessComments;
import com.elvel.system.mapper.BusinessCommentsMapper;
import com.elvel.system.service.IBusinessCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 评论Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessCommentsServiceImpl implements IBusinessCommentsService
{
    @Autowired
    private BusinessCommentsMapper businessCommentsMapper;

    /**
     * 查询评论
     *
     * @param id 评论主键
     * @return 评论
     */
    @Override
    public BusinessComments selectBusinessCommentsById(Long id)
    {
        return businessCommentsMapper.selectBusinessCommentsById(id);
    }

    /**
     * 查询评论列表
     *
     * @param businessComments 评论
     * @return 评论
     */
    @Override
    public List<BusinessComments> selectBusinessCommentsList(BusinessComments businessComments)
    {
        return businessCommentsMapper.selectBusinessCommentsList(businessComments);
    }

    /**
     * 新增评论
     *
     * @param businessComments 评论
     * @return 结果
     */
    @Override
    public int insertBusinessComments(BusinessComments businessComments)
    {
        businessComments.setCreateTime(DateUtils.getNowDate());
        return businessCommentsMapper.insertBusinessComments(businessComments);
    }

    /**
     * 修改评论
     *
     * @param businessComments 评论
     * @return 结果
     */
    @Override
    public int updateBusinessComments(BusinessComments businessComments)
    {
        return businessCommentsMapper.updateBusinessComments(businessComments);
    }

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的评论主键
     * @return 结果
     */
    @Override
    public int deleteBusinessCommentsByIds(Long[] ids)
    {
        return businessCommentsMapper.deleteBusinessCommentsByIds(ids);
    }

    /**
     * 删除评论信息
     *
     * @param id 评论主键
     * @return 结果
     */
    @Override
    public int deleteBusinessCommentsById(Long id)
    {
        return businessCommentsMapper.deleteBusinessCommentsById(id);
    }
}
