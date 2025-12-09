package com.elvel.system.domain;

import com.elvel.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 书籍大纲对象 business_book_outlines
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessBookOutlines extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 大纲ID */
    private Long id;

    /** 书籍ID */
    @Excel(name = "书籍ID")
    private Long bookId;

    /** 大纲内容 */
    @Excel(name = "大纲内容")
    private String outlineText;

    /** 大纲结构（JSON格式） */
    @Excel(name = "大纲结构", readConverterExp = "J=SON格式")
    private String structure;

    /** 大纲版本 */
    @Excel(name = "大纲版本")
    private Long version;

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

    public void setOutlineText(String outlineText)
    {
        this.outlineText = outlineText;
    }

    public String getOutlineText()
    {
        return outlineText;
    }

    public void setStructure(String structure)
    {
        this.structure = structure;
    }

    public String getStructure()
    {
        return structure;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }

    public Long getVersion()
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookId", getBookId())
            .append("outlineText", getOutlineText())
            .append("structure", getStructure())
            .append("version", getVersion())
            .append("createTime", getCreateTime())
            .toString();
    }
}
