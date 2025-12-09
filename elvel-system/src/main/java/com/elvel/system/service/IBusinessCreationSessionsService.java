package com.elvel.system.service;

import java.util.List;
import com.elvel.system.domain.BusinessCreationSessions;

/**
 * 创作会话Service接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface IBusinessCreationSessionsService
{
    /**
     * 查询创作会话
     *
     * @param id 创作会话主键
     * @return 创作会话
     */
    public BusinessCreationSessions selectBusinessCreationSessionsById(Long id);

    /**
     * 查询创作会话列表
     *
     * @param businessCreationSessions 创作会话
     * @return 创作会话集合
     */
    public List<BusinessCreationSessions> selectBusinessCreationSessionsList(BusinessCreationSessions businessCreationSessions);

    /**
     * 新增创作会话
     *
     * @param businessCreationSessions 创作会话
     * @return 结果
     */
    public int insertBusinessCreationSessions(BusinessCreationSessions businessCreationSessions);

    /**
     * 修改创作会话
     *
     * @param businessCreationSessions 创作会话
     * @return 结果
     */
    public int updateBusinessCreationSessions(BusinessCreationSessions businessCreationSessions);

    /**
     * 批量删除创作会话
     *
     * @param ids 需要删除的创作会话主键集合
     * @return 结果
     */
    public int deleteBusinessCreationSessionsByIds(Long[] ids);

    /**
     * 删除创作会话信息
     *
     * @param id 创作会话主键
     * @return 结果
     */
    public int deleteBusinessCreationSessionsById(Long id);
}
