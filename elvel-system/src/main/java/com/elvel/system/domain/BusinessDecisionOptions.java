package com.elvel.system.domain;

import com.elvel.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 决策选项对象 business_decision_options
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessDecisionOptions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 选项ID */
    private Long id;

    /** 决策点ID */
    @Excel(name = "决策点ID")
    private Long decisionPointId;

    /** 选项描述 */
    @Excel(name = "选项描述")
    private String optionText;

    /** 是否为系统提供（否则为用户自定义） */
    @Excel(name = "是否为系统提供", readConverterExp = "否=则为用户自定义")
    private Integer isSystemProvided;

    /** 关键词数组 */
    @Excel(name = "关键词数组")
    private String keywords;

    /** 被选择次数（统计用） */
    @Excel(name = "被选择次数", readConverterExp = "统=计用")
    private Long selectedCount;

    /** 是否被创作者选中 */
    @Excel(name = "是否被创作者选中")
    private Integer isSelected;

    /** 选择后生成的内容ID */
    @Excel(name = "选择后生成的内容ID")
    private Long nextGenerationId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setDecisionPointId(Long decisionPointId)
    {
        this.decisionPointId = decisionPointId;
    }

    public Long getDecisionPointId()
    {
        return decisionPointId;
    }

    public void setOptionText(String optionText)
    {
        this.optionText = optionText;
    }

    public String getOptionText()
    {
        return optionText;
    }

    public void setIsSystemProvided(Integer isSystemProvided)
    {
        this.isSystemProvided = isSystemProvided;
    }

    public Integer getIsSystemProvided()
    {
        return isSystemProvided;
    }

    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }

    public String getKeywords()
    {
        return keywords;
    }

    public void setSelectedCount(Long selectedCount)
    {
        this.selectedCount = selectedCount;
    }

    public Long getSelectedCount()
    {
        return selectedCount;
    }

    public void setIsSelected(Integer isSelected)
    {
        this.isSelected = isSelected;
    }

    public Integer getIsSelected()
    {
        return isSelected;
    }

    public void setNextGenerationId(Long nextGenerationId)
    {
        this.nextGenerationId = nextGenerationId;
    }

    public Long getNextGenerationId()
    {
        return nextGenerationId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("decisionPointId", getDecisionPointId())
            .append("optionText", getOptionText())
            .append("isSystemProvided", getIsSystemProvided())
            .append("keywords", getKeywords())
            .append("selectedCount", getSelectedCount())
            .append("isSelected", getIsSelected())
            .append("nextGenerationId", getNextGenerationId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
