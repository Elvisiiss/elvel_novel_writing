package com.elvel.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elvel.common.annotation.Log;
import com.elvel.common.core.controller.BaseController;
import com.elvel.common.core.domain.AjaxResult;
import com.elvel.common.enums.BusinessType;
import com.elvel.system.domain.BusinessBooks;
import com.elvel.system.service.IBusinessBooksService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 书籍主Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/books")
public class BusinessBooksController extends BaseController
{
    @Autowired
    private IBusinessBooksService businessBooksService;

    /**
     * 查询书籍主列表
     */
    @PreAuthorize("@ss.hasPermi('system:books:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessBooks businessBooks)
    {
        startPage();
        List<BusinessBooks> list = businessBooksService.selectBusinessBooksList(businessBooks);
        return getDataTable(list);
    }

    /**
     * 导出书籍主列表
     */
    @PreAuthorize("@ss.hasPermi('system:books:export')")
    @Log(title = "书籍主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessBooks businessBooks)
    {
        List<BusinessBooks> list = businessBooksService.selectBusinessBooksList(businessBooks);
        ExcelUtil<BusinessBooks> util = new ExcelUtil<BusinessBooks>(BusinessBooks.class);
        util.exportExcel(response, list, "书籍主数据");
    }

    /**
     * 获取书籍主详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:books:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessBooksService.selectBusinessBooksById(id));
    }

    /**
     * 新增书籍主
     */
    @PreAuthorize("@ss.hasPermi('system:books:add')")
    @Log(title = "书籍主", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessBooks businessBooks)
    {
        return toAjax(businessBooksService.insertBusinessBooks(businessBooks));
    }

    /**
     * 修改书籍主
     */
    @PreAuthorize("@ss.hasPermi('system:books:edit')")
    @Log(title = "书籍主", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessBooks businessBooks)
    {
        return toAjax(businessBooksService.updateBusinessBooks(businessBooks));
    }

    /**
     * 删除书籍主
     */
    @PreAuthorize("@ss.hasPermi('system:books:remove')")
    @Log(title = "书籍主", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessBooksService.deleteBusinessBooksByIds(ids));
    }
}
