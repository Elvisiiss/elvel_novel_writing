package com.elvel.system.domain;

import com.elvel.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 角色对象 business_characters
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessCharacters extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private Long id;

    /** 书籍ID */
    @Excel(name = "书籍ID")
    private Long bookId;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String name;

    /** 角色描述 */
    @Excel(name = "角色描述")
    private String description;

    /** 角色特征（JSON格式） */
    @Excel(name = "角色特征", readConverterExp = "J=SON格式")
    private String traits;

    /** 外貌描述 */
    @Excel(name = "外貌描述")
    private String appearance;

    /** 重要性：主角/配角/次要角色 */
    @Excel(name = "重要性：主角/配角/次要角色")
    private String importance;

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

    public void setTraits(String traits)
    {
        this.traits = traits;
    }

    public String getTraits()
    {
        return traits;
    }

    public void setAppearance(String appearance)
    {
        this.appearance = appearance;
    }

    public String getAppearance()
    {
        return appearance;
    }

    public void setImportance(String importance)
    {
        this.importance = importance;
    }

    public String getImportance()
    {
        return importance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookId", getBookId())
            .append("name", getName())
            .append("description", getDescription())
            .append("traits", getTraits())
            .append("appearance", getAppearance())
            .append("importance", getImportance())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
