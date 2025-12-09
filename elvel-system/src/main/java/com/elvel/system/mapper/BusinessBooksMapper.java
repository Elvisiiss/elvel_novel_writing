package com.elvel.system.mapper;

import java.util.List;
import com.elvel.system.domain.BusinessBooks;

/**
 * 书籍主Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-09
 */
public interface BusinessBooksMapper
{
    /**
     * 查询书籍主
     *
     * @param id 书籍主主键
     * @return 书籍主
     */
    public BusinessBooks selectBusinessBooksById(Long id);

    /**
     * 查询书籍主列表
     *
     * @param businessBooks 书籍主
     * @return 书籍主集合
     */
    public List<BusinessBooks> selectBusinessBooksList(BusinessBooks businessBooks);

    /**
     * 新增书籍主
     *
     * @param businessBooks 书籍主
     * @return 结果
     */
    public int insertBusinessBooks(BusinessBooks businessBooks);

    /**
     * 修改书籍主
     *
     * @param businessBooks 书籍主
     * @return 结果
     */
    public int updateBusinessBooks(BusinessBooks businessBooks);

    /**
     * 删除书籍主
     *
     * @param id 书籍主主键
     * @return 结果
     */
    public int deleteBusinessBooksById(Long id);

    /**
     * 批量删除书籍主
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessBooksByIds(Long[] ids);
}
