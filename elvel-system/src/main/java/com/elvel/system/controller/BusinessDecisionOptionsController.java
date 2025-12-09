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
import com.elvel.system.domain.BusinessDecisionOptions;
import com.elvel.system.service.IBusinessDecisionOptionsService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 决策选项Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/options")
public class BusinessDecisionOptionsController extends BaseController
{
    @Autowired
    private IBusinessDecisionOptionsService businessDecisionOptionsService;

    /**
     * 查询决策选项列表
     */
    @PreAuthorize("@ss.hasPermi('system:options:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessDecisionOptions businessDecisionOptions)
    {
        startPage();
        List<BusinessDecisionOptions> list = businessDecisionOptionsService.selectBusinessDecisionOptionsList(businessDecisionOptions);
        return getDataTable(list);
    }

    /**
     * 导出决策选项列表
     */
    @PreAuthorize("@ss.hasPermi('system:options:export')")
    @Log(title = "决策选项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessDecisionOptions businessDecisionOptions)
    {
        List<BusinessDecisionOptions> list = businessDecisionOptionsService.selectBusinessDecisionOptionsList(businessDecisionOptions);
        ExcelUtil<BusinessDecisionOptions> util = new ExcelUtil<BusinessDecisionOptions>(BusinessDecisionOptions.class);
        util.exportExcel(response, list, "决策选项数据");
    }

    /**
     * 获取决策选项详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:options:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessDecisionOptionsService.selectBusinessDecisionOptionsById(id));
    }

    /**
     * 新增决策选项
     */
    @PreAuthorize("@ss.hasPermi('system:options:add')")
    @Log(title = "决策选项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessDecisionOptions businessDecisionOptions)
    {
        return toAjax(businessDecisionOptionsService.insertBusinessDecisionOptions(businessDecisionOptions));
    }

    /**
     * 修改决策选项
     */
    @PreAuthorize("@ss.hasPermi('system:options:edit')")
    @Log(title = "决策选项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessDecisionOptions businessDecisionOptions)
    {
        return toAjax(businessDecisionOptionsService.updateBusinessDecisionOptions(businessDecisionOptions));
    }

    /**
     * 删除决策选项
     */
    @PreAuthorize("@ss.hasPermi('system:options:remove')")
    @Log(title = "决策选项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessDecisionOptionsService.deleteBusinessDecisionOptionsByIds(ids));
    }
}
