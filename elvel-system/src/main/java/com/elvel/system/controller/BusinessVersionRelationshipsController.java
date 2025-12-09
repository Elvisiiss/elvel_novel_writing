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
import com.elvel.system.domain.BusinessVersionRelationships;
import com.elvel.system.service.IBusinessVersionRelationshipsService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 版本关系Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/relationships")
public class BusinessVersionRelationshipsController extends BaseController
{
    @Autowired
    private IBusinessVersionRelationshipsService businessVersionRelationshipsService;

    /**
     * 查询版本关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:relationships:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessVersionRelationships businessVersionRelationships)
    {
        startPage();
        List<BusinessVersionRelationships> list = businessVersionRelationshipsService.selectBusinessVersionRelationshipsList(businessVersionRelationships);
        return getDataTable(list);
    }

    /**
     * 导出版本关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:relationships:export')")
    @Log(title = "版本关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessVersionRelationships businessVersionRelationships)
    {
        List<BusinessVersionRelationships> list = businessVersionRelationshipsService.selectBusinessVersionRelationshipsList(businessVersionRelationships);
        ExcelUtil<BusinessVersionRelationships> util = new ExcelUtil<BusinessVersionRelationships>(BusinessVersionRelationships.class);
        util.exportExcel(response, list, "版本关系数据");
    }

    /**
     * 获取版本关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:relationships:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessVersionRelationshipsService.selectBusinessVersionRelationshipsById(id));
    }

    /**
     * 新增版本关系
     */
    @PreAuthorize("@ss.hasPermi('system:relationships:add')")
    @Log(title = "版本关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessVersionRelationships businessVersionRelationships)
    {
        return toAjax(businessVersionRelationshipsService.insertBusinessVersionRelationships(businessVersionRelationships));
    }

    /**
     * 修改版本关系
     */
    @PreAuthorize("@ss.hasPermi('system:relationships:edit')")
    @Log(title = "版本关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessVersionRelationships businessVersionRelationships)
    {
        return toAjax(businessVersionRelationshipsService.updateBusinessVersionRelationships(businessVersionRelationships));
    }

    /**
     * 删除版本关系
     */
    @PreAuthorize("@ss.hasPermi('system:relationships:remove')")
    @Log(title = "版本关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessVersionRelationshipsService.deleteBusinessVersionRelationshipsByIds(ids));
    }
}
