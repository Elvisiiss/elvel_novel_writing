package com.elvel.system.domain;

import com.elvel.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 章节对象 business_chapters
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessChapters extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 章节ID */
    private Long id;

    /** 书籍ID */
    @Excel(name = "书籍ID")
    private Long bookId;

    /** 章节序号 */
    @Excel(name = "章节序号")
    private Long chapterNumber;

    /** 章节标题 */
    @Excel(name = "章节标题")
    private String title;

    /** 章节摘要（AI生成） */
    @Excel(name = "章节摘要", readConverterExp = "A=I生成")
    private String contentSummary;

    /** 章节字数 */
    @Excel(name = "章节字数")
    private Long wordCount;

    /** 生成次数 */
    @Excel(name = "生成次数")
    private Long generationCount;

    /** 是否已完成 */
    @Excel(name = "是否已完成")
    private Integer isCompleted;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setBookId(Long bookId)
    {
        this.bookId = bookId;
    }

    public Long getBookId()
    {
        return bookId;
    }

    public void setChapterNumber(Long chapterNumber)
    {
        this.chapterNumber = chapterNumber;
    }

    public Long getChapterNumber()
    {
        return chapterNumber;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setContentSummary(String contentSummary)
    {
        this.contentSummary = contentSummary;
    }

    public String getContentSummary()
    {
        return contentSummary;
    }

    public void setWordCount(Long wordCount)
    {
        this.wordCount = wordCount;
    }

    public Long getWordCount()
    {
        return wordCount;
    }

    public void setGenerationCount(Long generationCount)
    {
        this.generationCount = generationCount;
    }

    public Long getGenerationCount()
    {
        return generationCount;
    }

    public void setIsCompleted(Integer isCompleted)
    {
        this.isCompleted = isCompleted;
    }

    public Integer getIsCompleted()
    {
        return isCompleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookId", getBookId())
            .append("chapterNumber", getChapterNumber())
            .append("title", getTitle())
            .append("contentSummary", getContentSummary())
            .append("wordCount", getWordCount())
            .append("generationCount", getGenerationCount())
            .append("isCompleted", getIsCompleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
