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
import com.elvel.system.domain.BusinessChapters;
import com.elvel.system.service.IBusinessChaptersService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 章节Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/chapters")
public class BusinessChaptersController extends BaseController
{
    @Autowired
    private IBusinessChaptersService businessChaptersService;

    /**
     * 查询章节列表
     */
    @PreAuthorize("@ss.hasPermi('system:chapters:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessChapters businessChapters)
    {
        startPage();
        List<BusinessChapters> list = businessChaptersService.selectBusinessChaptersList(businessChapters);
        return getDataTable(list);
    }

    /**
     * 导出章节列表
     */
    @PreAuthorize("@ss.hasPermi('system:chapters:export')")
    @Log(title = "章节", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessChapters businessChapters)
    {
        List<BusinessChapters> list = businessChaptersService.selectBusinessChaptersList(businessChapters);
        ExcelUtil<BusinessChapters> util = new ExcelUtil<BusinessChapters>(BusinessChapters.class);
        util.exportExcel(response, list, "章节数据");
    }

    /**
     * 获取章节详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:chapters:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessChaptersService.selectBusinessChaptersById(id));
    }

    /**
     * 新增章节
     */
    @PreAuthorize("@ss.hasPermi('system:chapters:add')")
    @Log(title = "章节", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessChapters businessChapters)
    {
        return toAjax(businessChaptersService.insertBusinessChapters(businessChapters));
    }

    /**
     * 修改章节
     */
    @PreAuthorize("@ss.hasPermi('system:chapters:edit')")
    @Log(title = "章节", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessChapters businessChapters)
    {
        return toAjax(businessChaptersService.updateBusinessChapters(businessChapters));
    }

    /**
     * 删除章节
     */
    @PreAuthorize("@ss.hasPermi('system:chapters:remove')")
    @Log(title = "章节", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessChaptersService.deleteBusinessChaptersByIds(ids));
    }
}
