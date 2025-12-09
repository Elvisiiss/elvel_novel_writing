package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessCreationSessions;
import com.elvel.system.mapper.BusinessCreationSessionsMapper;
import com.elvel.system.service.IBusinessCreationSessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 创作会话Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessCreationSessionsServiceImpl implements IBusinessCreationSessionsService
{
    @Autowired
    private BusinessCreationSessionsMapper businessCreationSessionsMapper;

    /**
     * 查询创作会话
     *
     * @param id 创作会话主键
     * @return 创作会话
     */
    @Override
    public BusinessCreationSessions selectBusinessCreationSessionsById(Long id)
    {
        return businessCreationSessionsMapper.selectBusinessCreationSessionsById(id);
    }

    /**
     * 查询创作会话列表
     *
     * @param businessCreationSessions 创作会话
     * @return 创作会话
     */
    @Override
    public List<BusinessCreationSessions> selectBusinessCreationSessionsList(BusinessCreationSessions businessCreationSessions)
    {
        return businessCreationSessionsMapper.selectBusinessCreationSessionsList(businessCreationSessions);
    }

    /**
     * 新增创作会话
     *
     * @param businessCreationSessions 创作会话
     * @return 结果
     */
    @Override
    public int insertBusinessCreationSessions(BusinessCreationSessions businessCreationSessions)
    {
        businessCreationSessions.setCreateTime(DateUtils.getNowDate());
        return businessCreationSessionsMapper.insertBusinessCreationSessions(businessCreationSessions);
    }

    /**
     * 修改创作会话
     *
     * @param businessCreationSessions 创作会话
     * @return 结果
     */
    @Override
    public int updateBusinessCreationSessions(BusinessCreationSessions businessCreationSessions)
    {
        return businessCreationSessionsMapper.updateBusinessCreationSessions(businessCreationSessions);
    }

    /**
     * 批量删除创作会话
     *
     * @param ids 需要删除的创作会话主键
     * @return 结果
     */
    @Override
    public int deleteBusinessCreationSessionsByIds(Long[] ids)
    {
        return businessCreationSessionsMapper.deleteBusinessCreationSessionsByIds(ids);
    }

    /**
     * 删除创作会话信息
     *
     * @param id 创作会话主键
     * @return 结果
     */
    @Override
    public int deleteBusinessCreationSessionsById(Long id)
    {
        return businessCreationSessionsMapper.deleteBusinessCreationSessionsById(id);
    }
}
