package com.elvel.system.domain;

import com.elvel.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 评论对象 business_comments
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessComments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 书籍ID */
    @Excel(name = "书籍ID")
    private Long bookId;

    /** 评论目标类型：书籍/章节/生成内容 */
    @Excel(name = "评论目标类型：书籍/章节/生成内容")
    private String targetType;

    /** 目标ID */
    @Excel(name = "目标ID")
    private Long targetId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 父评论ID */
    @Excel(name = "父评论ID")
    private Long parentCommentId;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likeCount;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setBookId(Long bookId)
    {
        this.bookId = bookId;
    }

    public Long getBookId()
    {
        return bookId;
    }

    public void setTargetType(String targetType)
    {
        this.targetType = targetType;
    }

    public String getTargetType()
    {
        return targetType;
    }

    public void setTargetId(Long targetId)
    {
        this.targetId = targetId;
    }

    public Long getTargetId()
    {
        return targetId;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setParentCommentId(Long parentCommentId)
    {
        this.parentCommentId = parentCommentId;
    }

    public Long getParentCommentId()
    {
        return parentCommentId;
    }

    public void setLikeCount(Long likeCount)
    {
        this.likeCount = likeCount;
    }

    public Long getLikeCount()
    {
        return likeCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("bookId", getBookId())
            .append("targetType", getTargetType())
            .append("targetId", getTargetId())
            .append("content", getContent())
            .append("parentCommentId", getParentCommentId())
            .append("likeCount", getLikeCount())
            .append("createTime", getCreateTime())
            .toString();
    }
}
