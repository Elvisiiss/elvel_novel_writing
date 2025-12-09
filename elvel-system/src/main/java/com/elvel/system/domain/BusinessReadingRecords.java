package com.elvel.system.domain;

import java.util.Date;

import com.elvel.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 阅读记录对象 business_reading_records
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessReadingRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 阅读记录ID */
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

    /** 最后阅读的生成记录ID */
    @Excel(name = "最后阅读的生成记录ID")
    private Long lastGenerationId;

    /** 阅读模式：沉浸/创作/上帝视角 */
    @Excel(name = "阅读模式：沉浸/创作/上帝视角")
    private String readingMode;

    /** 阅读进度（0-1） */
    @Excel(name = "阅读进度", readConverterExp = "0=-1")
    private Long progress;

    /** 最后阅读时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后阅读时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastReadAt;

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

    public void setLastGenerationId(Long lastGenerationId)
    {
        this.lastGenerationId = lastGenerationId;
    }

    public Long getLastGenerationId()
    {
        return lastGenerationId;
    }

    public void setReadingMode(String readingMode)
    {
        this.readingMode = readingMode;
    }

    public String getReadingMode()
    {
        return readingMode;
    }

    public void setProgress(Long progress)
    {
        this.progress = progress;
    }

    public Long getProgress()
    {
        return progress;
    }

    public void setLastReadAt(Date lastReadAt)
    {
        this.lastReadAt = lastReadAt;
    }

    public Date getLastReadAt()
    {
        return lastReadAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("bookId", getBookId())
            .append("chapterId", getChapterId())
            .append("lastGenerationId", getLastGenerationId())
            .append("readingMode", getReadingMode())
            .append("progress", getProgress())
            .append("lastReadAt", getLastReadAt())
            .append("createTime", getCreateTime())
            .toString();
    }
}
