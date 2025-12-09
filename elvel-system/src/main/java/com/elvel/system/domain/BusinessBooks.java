package com.elvel.system.domain;

import com.elvel.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.elvel.common.annotation.Excel;

/**
 * 书籍主对象 business_books
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public class BusinessBooks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 书籍ID */
    private Long id;

    /** 书名 */
    @Excel(name = "书名")
    private String title;

    /** 封面URL */
    @Excel(name = "封面URL")
    private String coverUrl;

    /** 书籍描述 */
    @Excel(name = "书籍描述")
    private String description;

    /** 分类/体裁 */
    @Excel(name = "分类/体裁")
    private String genre;

    /** 标签数组 */
    @Excel(name = "标签数组")
    private String tags;

    /** 作者用户ID */
    @Excel(name = "作者用户ID")
    private Long userId;

    /** 基于的版本ID（衍生作品来源） */
    @Excel(name = "基于的版本ID", readConverterExp = "衍=生作品来源")
    private Long baseVersionId;

    /** 版本号 */
    @Excel(name = "版本号")
    private String versionCode;

    /** 状态：草稿/连载中/已完成/废弃 */
    @Excel(name = "状态：草稿/连载中/已完成/废弃")
    private String status;

    /** 衍生权限：开放/需授权/关闭 */
    @Excel(name = "衍生权限：开放/需授权/关闭")
    private String derivativePermission;

    /** 总字数 */
    @Excel(name = "总字数")
    private Long wordCount;

    /** 章节数 */
    @Excel(name = "章节数")
    private Long chapterCount;

    /** 阅读次数 */
    @Excel(name = "阅读次数")
    private Long readCount;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likeCount;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setCoverUrl(String coverUrl)
    {
        this.coverUrl = coverUrl;
    }

    public String getCoverUrl()
    {
        return coverUrl;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getTags()
    {
        return tags;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setBaseVersionId(Long baseVersionId)
    {
        this.baseVersionId = baseVersionId;
    }

    public Long getBaseVersionId()
    {
        return baseVersionId;
    }

    public void setVersionCode(String versionCode)
    {
        this.versionCode = versionCode;
    }

    public String getVersionCode()
    {
        return versionCode;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setDerivativePermission(String derivativePermission)
    {
        this.derivativePermission = derivativePermission;
    }

    public String getDerivativePermission()
    {
        return derivativePermission;
    }

    public void setWordCount(Long wordCount)
    {
        this.wordCount = wordCount;
    }

    public Long getWordCount()
    {
        return wordCount;
    }

    public void setChapterCount(Long chapterCount)
    {
        this.chapterCount = chapterCount;
    }

    public Long getChapterCount()
    {
        return chapterCount;
    }

    public void setReadCount(Long readCount)
    {
        this.readCount = readCount;
    }

    public Long getReadCount()
    {
        return readCount;
    }

    public void setLikeCount(Long likeCount)
    {
        this.likeCount = likeCount;
    }

    public Long getLikeCount()
    {
        return likeCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("coverUrl", getCoverUrl())
            .append("description", getDescription())
            .append("genre", getGenre())
            .append("tags", getTags())
            .append("userId", getUserId())
            .append("baseVersionId", getBaseVersionId())
            .append("versionCode", getVersionCode())
            .append("status", getStatus())
            .append("derivativePermission", getDerivativePermission())
            .append("wordCount", getWordCount())
            .append("chapterCount", getChapterCount())
            .append("readCount", getReadCount())
            .append("likeCount", getLikeCount())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
