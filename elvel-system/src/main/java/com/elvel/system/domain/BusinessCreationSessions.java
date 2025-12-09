package com.elvel.system.domain;

import java.util.Date;

import com.elvel.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 创作会话对象 business_creation_sessions
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessCreationSessions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会话ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 书籍ID */
    @Excel(name = "书籍ID")
    private Long bookId;

    /** 章节ID */
    @Excel(name = "章节ID")
    private Long chapterId;

    /** 当前生成记录ID */
    @Excel(name = "当前生成记录ID")
    private Long currentGenerationId;

    /** 待处理决策点ID */
    @Excel(name = "待处理决策点ID")
    private Long pendingDecisionPointId;

    /** 会话状态数据（JSON格式） */
    @Excel(name = "会话状态数据", readConverterExp = "J=SON格式")
    private String sessionData;

    /** 是否活跃 */
    @Excel(name = "是否活跃")
    private Integer isActive;

    /** 最后活动时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后活动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastActivityAt;

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

    public void setChapterId(Long chapterId)
    {
        this.chapterId = chapterId;
    }

    public Long getChapterId()
    {
        return chapterId;
    }

    public void setCurrentGenerationId(Long currentGenerationId)
    {
        this.currentGenerationId = currentGenerationId;
    }

    public Long getCurrentGenerationId()
    {
        return currentGenerationId;
    }

    public void setPendingDecisionPointId(Long pendingDecisionPointId)
    {
        this.pendingDecisionPointId = pendingDecisionPointId;
    }

    public Long getPendingDecisionPointId()
    {
        return pendingDecisionPointId;
    }

    public void setSessionData(String sessionData)
    {
        this.sessionData = sessionData;
    }

    public String getSessionData()
    {
        return sessionData;
    }

    public void setIsActive(Integer isActive)
    {
        this.isActive = isActive;
    }

    public Integer getIsActive()
    {
        return isActive;
    }

    public void setLastActivityAt(Date lastActivityAt)
    {
        this.lastActivityAt = lastActivityAt;
    }

    public Date getLastActivityAt()
    {
        return lastActivityAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("bookId", getBookId())
            .append("chapterId", getChapterId())
            .append("currentGenerationId", getCurrentGenerationId())
            .append("pendingDecisionPointId", getPendingDecisionPointId())
            .append("sessionData", getSessionData())
            .append("isActive", getIsActive())
            .append("createTime", getCreateTime())
            .append("lastActivityAt", getLastActivityAt())
            .toString();
    }
}
