package com.elvel.system.domain;

import com.elvel.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 内容生成记录对象 business_content_generations
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessContentGenerations extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 生成记录ID */
    private Long id;

    /** 章节ID */
    @Excel(name = "章节ID")
    private Long chapterId;

    /** 生成序号（在章节中的顺序） */
    @Excel(name = "生成序号", readConverterExp = "在=章节中的顺序")
    private Long generationIndex;

    /** 生成内容 */
    @Excel(name = "生成内容")
    private String content;

    /** 使用的提示词 */
    @Excel(name = "使用的提示词")
    private String prompt;

    /** 基于的决策点ID */
    @Excel(name = "基于的决策点ID")
    private Long decisionPointId;

    /** 使用的AI模型 */
    @Excel(name = "使用的AI模型")
    private String aiModel;

    /** 消耗的token数 */
    @Excel(name = "消耗的token数")
    private Long tokenCount;

    /** 是否重写过 */
    @Excel(name = "是否重写过")
    private Integer isRewritten;

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

    public void setGenerationIndex(Long generationIndex)
    {
        this.generationIndex = generationIndex;
    }

    public Long getGenerationIndex()
    {
        return generationIndex;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setPrompt(String prompt)
    {
        this.prompt = prompt;
    }

    public String getPrompt()
    {
        return prompt;
    }

    public void setDecisionPointId(Long decisionPointId)
    {
        this.decisionPointId = decisionPointId;
    }

    public Long getDecisionPointId()
    {
        return decisionPointId;
    }

    public void setAiModel(String aiModel)
    {
        this.aiModel = aiModel;
    }

    public String getAiModel()
    {
        return aiModel;
    }

    public void setTokenCount(Long tokenCount)
    {
        this.tokenCount = tokenCount;
    }

    public Long getTokenCount()
    {
        return tokenCount;
    }

    public void setIsRewritten(Integer isRewritten)
    {
        this.isRewritten = isRewritten;
    }

    public Integer getIsRewritten()
    {
        return isRewritten;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("chapterId", getChapterId())
            .append("generationIndex", getGenerationIndex())
            .append("content", getContent())
            .append("prompt", getPrompt())
            .append("decisionPointId", getDecisionPointId())
            .append("aiModel", getAiModel())
            .append("tokenCount", getTokenCount())
            .append("isRewritten", getIsRewritten())
            .append("createTime", getCreateTime())
            .toString();
    }
}
