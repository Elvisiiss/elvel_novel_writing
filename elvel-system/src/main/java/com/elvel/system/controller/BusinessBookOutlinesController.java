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
import com.elvel.system.domain.BusinessBookOutlines;
import com.elvel.system.service.IBusinessBookOutlinesService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 书籍大纲Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/outlines")
public class BusinessBookOutlinesController extends BaseController
{
    @Autowired
    private IBusinessBookOutlinesService businessBookOutlinesService;

    /**
     * 查询书籍大纲列表
     */
    @PreAuthorize("@ss.hasPermi('system:outlines:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessBookOutlines businessBookOutlines)
    {
        startPage();
        List<BusinessBookOutlines> list = businessBookOutlinesService.selectBusinessBookOutlinesList(businessBookOutlines);
        return getDataTable(list);
    }

    /**
     * 导出书籍大纲列表
     */
    @PreAuthorize("@ss.hasPermi('system:outlines:export')")
    @Log(title = "书籍大纲", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessBookOutlines businessBookOutlines)
    {
        List<BusinessBookOutlines> list = businessBookOutlinesService.selectBusinessBookOutlinesList(businessBookOutlines);
        ExcelUtil<BusinessBookOutlines> util = new ExcelUtil<BusinessBookOutlines>(BusinessBookOutlines.class);
        util.exportExcel(response, list, "书籍大纲数据");
    }

    /**
     * 获取书籍大纲详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:outlines:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessBookOutlinesService.selectBusinessBookOutlinesById(id));
    }

    /**
     * 新增书籍大纲
     */
    @PreAuthorize("@ss.hasPermi('system:outlines:add')")
    @Log(title = "书籍大纲", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessBookOutlines businessBookOutlines)
    {
        return toAjax(businessBookOutlinesService.insertBusinessBookOutlines(businessBookOutlines));
    }

    /**
     * 修改书籍大纲
     */
    @PreAuthorize("@ss.hasPermi('system:outlines:edit')")
    @Log(title = "书籍大纲", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessBookOutlines businessBookOutlines)
    {
        return toAjax(businessBookOutlinesService.updateBusinessBookOutlines(businessBookOutlines));
    }

    /**
     * 删除书籍大纲
     */
    @PreAuthorize("@ss.hasPermi('system:outlines:remove')")
    @Log(title = "书籍大纲", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessBookOutlinesService.deleteBusinessBookOutlinesByIds(ids));
    }
}
