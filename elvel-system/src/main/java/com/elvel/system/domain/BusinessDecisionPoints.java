package com.elvel.system.domain;

import com.elvel.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 决策点对象 business_decision_points
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessDecisionPoints extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 决策点ID */
    private Long id;

    /** 章节ID */
    @Excel(name = "章节ID")
    private Long chapterId;

    /** 生成记录ID（在哪个生成后出现） */
    @Excel(name = "生成记录ID", readConverterExp = "在=哪个生成后出现")
    private Long generationId;

    /** 在章节中的位置 */
    @Excel(name = "在章节中的位置")
    private Long position;

    /** 决策问题 */
    @Excel(name = "决策问题")
    private String question;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setChapterId(Long chapterId)
    {
        this.chapterId = chapterId;
    }

    public Long getChapterId()
    {
        return chapterId;
    }

    public void setGenerationId(Long generationId)
    {
        this.generationId = generationId;
    }

    public Long getGenerationId()
    {
        return generationId;
    }

    public void setPosition(Long position)
    {
        this.position = position;
    }

    public Long getPosition()
    {
        return position;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getQuestion()
    {
        return question;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("chapterId", getChapterId())
            .append("generationId", getGenerationId())
            .append("position", getPosition())
            .append("question", getQuestion())
            .append("createTime", getCreateTime())
            .toString();
    }
}
