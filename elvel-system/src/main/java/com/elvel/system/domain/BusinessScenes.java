package com.elvel.system.domain;

import com.elvel.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 场景对象 business_scenes
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessScenes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 场景ID */
    private Long id;

    /** 书籍ID */
    @Excel(name = "书籍ID")
    private Long bookId;

    /** 场景名称 */
    @Excel(name = "场景名称")
    private String name;

    /** 场景描述 */
    @Excel(name = "场景描述")
    private String description;

    /** 类型：地点/事件/氛围 */
    @Excel(name = "类型：地点/事件/氛围")
    private String type;

    /** 场景详情（JSON格式） */
    @Excel(name = "场景详情", readConverterExp = "J=SON格式")
    private String details;

    /** 首次出现章节 */
    @Excel(name = "首次出现章节")
    private Long firstAppearChapter;

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

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getDetails()
    {
        return details;
    }

    public void setFirstAppearChapter(Long firstAppearChapter)
    {
        this.firstAppearChapter = firstAppearChapter;
    }

    public Long getFirstAppearChapter()
    {
        return firstAppearChapter;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookId", getBookId())
            .append("name", getName())
            .append("description", getDescription())
            .append("type", getType())
            .append("details", getDetails())
            .append("firstAppearChapter", getFirstAppearChapter())
            .append("createTime", getCreateTime())
            .toString();
    }
}
