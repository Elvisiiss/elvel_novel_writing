package com.elvel.system.service.impl;

import java.util.List;
import com.elvel.common.utils.DateUtils;
import com.elvel.system.domain.BusinessBooks;
import com.elvel.system.mapper.BusinessBooksMapper;
import com.elvel.system.service.IBusinessBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 书籍主Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@Service
public class BusinessBooksServiceImpl implements IBusinessBooksService
{
    @Autowired
    private BusinessBooksMapper businessBooksMapper;

    /**
     * 查询书籍主
     *
     * @param id 书籍主主键
     * @return 书籍主
     */
    @Override
    public BusinessBooks selectBusinessBooksById(Long id)
    {
        return businessBooksMapper.selectBusinessBooksById(id);
    }

    /**
     * 查询书籍主列表
     *
     * @param businessBooks 书籍主
     * @return 书籍主
     */
    @Override
    public List<BusinessBooks> selectBusinessBooksList(BusinessBooks businessBooks)
    {
        return businessBooksMapper.selectBusinessBooksList(businessBooks);
    }

    /**
     * 新增书籍主
     *
     * @param businessBooks 书籍主
     * @return 结果
     */
    @Override
    public int insertBusinessBooks(BusinessBooks businessBooks)
    {
        businessBooks.setCreateTime(DateUtils.getNowDate());
        return businessBooksMapper.insertBusinessBooks(businessBooks);
    }

    /**
     * 修改书籍主
     *
     * @param businessBooks 书籍主
     * @return 结果
     */
    @Override
    public int updateBusinessBooks(BusinessBooks businessBooks)
    {
        businessBooks.setUpdateTime(DateUtils.getNowDate());
        return businessBooksMapper.updateBusinessBooks(businessBooks);
    }

    /**
     * 批量删除书籍主
     *
     * @param ids 需要删除的书籍主主键
     * @return 结果
     */
    @Override
    public int deleteBusinessBooksByIds(Long[] ids)
    {
        return businessBooksMapper.deleteBusinessBooksByIds(ids);
    }

    /**
     * 删除书籍主信息
     *
     * @param id 书籍主主键
     * @return 结果
     */
    @Override
    public int deleteBusinessBooksById(Long id)
    {
        return businessBooksMapper.deleteBusinessBooksById(id);
    }
}
