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
import com.elvel.system.domain.BusinessContentGenerations;
import com.elvel.system.service.IBusinessContentGenerationsService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 内容生成记录Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/generations")
public class BusinessContentGenerationsController extends BaseController
{
    @Autowired
    private IBusinessContentGenerationsService businessContentGenerationsService;

    /**
     * 查询内容生成记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:generations:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessContentGenerations businessContentGenerations)
    {
        startPage();
        List<BusinessContentGenerations> list = businessContentGenerationsService.selectBusinessContentGenerationsList(businessContentGenerations);
        return getDataTable(list);
    }

    /**
     * 导出内容生成记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:generations:export')")
    @Log(title = "内容生成记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessContentGenerations businessContentGenerations)
    {
        List<BusinessContentGenerations> list = businessContentGenerationsService.selectBusinessContentGenerationsList(businessContentGenerations);
        ExcelUtil<BusinessContentGenerations> util = new ExcelUtil<BusinessContentGenerations>(BusinessContentGenerations.class);
        util.exportExcel(response, list, "内容生成记录数据");
    }

    /**
     * 获取内容生成记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:generations:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessContentGenerationsService.selectBusinessContentGenerationsById(id));
    }

    /**
     * 新增内容生成记录
     */
    @PreAuthorize("@ss.hasPermi('system:generations:add')")
    @Log(title = "内容生成记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessContentGenerations businessContentGenerations)
    {
        return toAjax(businessContentGenerationsService.insertBusinessContentGenerations(businessContentGenerations));
    }

    /**
     * 修改内容生成记录
     */
    @PreAuthorize("@ss.hasPermi('system:generations:edit')")
    @Log(title = "内容生成记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessContentGenerations businessContentGenerations)
    {
        return toAjax(businessContentGenerationsService.updateBusinessContentGenerations(businessContentGenerations));
    }

    /**
     * 删除内容生成记录
     */
    @PreAuthorize("@ss.hasPermi('system:generations:remove')")
    @Log(title = "内容生成记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessContentGenerationsService.deleteBusinessContentGenerationsByIds(ids));
    }
}
