package com.elvel.system.domain;

import com.elvel.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 版本关系对象 business_version_relationships
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessVersionRelationships extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关系ID */
    private Long id;

    /** 原始书籍ID */
    @Excel(name = "原始书籍ID")
    private Long originalBookId;

    /** 衍生书籍ID */
    @Excel(name = "衍生书籍ID")
    private Long derivativeBookId;

    /** 分叉来源章节ID */
    @Excel(name = "分叉来源章节ID")
    private Long forkFromChapterId;

    /** 分叉来源生成记录ID */
    @Excel(name = "分叉来源生成记录ID")
    private Long forkFromGenerationId;

    /** 关系类型：直接/间接 */
    @Excel(name = "关系类型：直接/间接")
    private String relationshipType;

    /** 是否获得授权 */
    @Excel(name = "是否获得授权")
    private Integer permissionGranted;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setOriginalBookId(Long originalBookId)
    {
        this.originalBookId = originalBookId;
    }

    public Long getOriginalBookId()
    {
        return originalBookId;
    }

    public void setDerivativeBookId(Long derivativeBookId)
    {
        this.derivativeBookId = derivativeBookId;
    }

    public Long getDerivativeBookId()
    {
        return derivativeBookId;
    }

    public void setForkFromChapterId(Long forkFromChapterId)
    {
        this.forkFromChapterId = forkFromChapterId;
    }

    public Long getForkFromChapterId()
    {
        return forkFromChapterId;
    }

    public void setForkFromGenerationId(Long forkFromGenerationId)
    {
        this.forkFromGenerationId = forkFromGenerationId;
    }

    public Long getForkFromGenerationId()
    {
        return forkFromGenerationId;
    }

    public void setRelationshipType(String relationshipType)
    {
        this.relationshipType = relationshipType;
    }

    public String getRelationshipType()
    {
        return relationshipType;
    }

    public void setPermissionGranted(Integer permissionGranted)
    {
        this.permissionGranted = permissionGranted;
    }

    public Integer getPermissionGranted()
    {
        return permissionGranted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("originalBookId", getOriginalBookId())
            .append("derivativeBookId", getDerivativeBookId())
            .append("forkFromChapterId", getForkFromChapterId())
            .append("forkFromGenerationId", getForkFromGenerationId())
            .append("relationshipType", getRelationshipType())
            .append("permissionGranted", getPermissionGranted())
            .append("createTime", getCreateTime())
            .toString();
    }
}
