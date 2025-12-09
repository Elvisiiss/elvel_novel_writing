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
import com.elvel.system.domain.BusinessDecisionPoints;
import com.elvel.system.service.IBusinessDecisionPointsService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 决策点Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/points")
public class BusinessDecisionPointsController extends BaseController
{
    @Autowired
    private IBusinessDecisionPointsService businessDecisionPointsService;

    /**
     * 查询决策点列表
     */
    @PreAuthorize("@ss.hasPermi('system:points:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessDecisionPoints businessDecisionPoints)
    {
        startPage();
        List<BusinessDecisionPoints> list = businessDecisionPointsService.selectBusinessDecisionPointsList(businessDecisionPoints);
        return getDataTable(list);
    }

    /**
     * 导出决策点列表
     */
    @PreAuthorize("@ss.hasPermi('system:points:export')")
    @Log(title = "决策点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessDecisionPoints businessDecisionPoints)
    {
        List<BusinessDecisionPoints> list = businessDecisionPointsService.selectBusinessDecisionPointsList(businessDecisionPoints);
        ExcelUtil<BusinessDecisionPoints> util = new ExcelUtil<BusinessDecisionPoints>(BusinessDecisionPoints.class);
        util.exportExcel(response, list, "决策点数据");
    }

    /**
     * 获取决策点详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:points:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessDecisionPointsService.selectBusinessDecisionPointsById(id));
    }

    /**
     * 新增决策点
     */
    @PreAuthorize("@ss.hasPermi('system:points:add')")
    @Log(title = "决策点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessDecisionPoints businessDecisionPoints)
    {
        return toAjax(businessDecisionPointsService.insertBusinessDecisionPoints(businessDecisionPoints));
    }

    /**
     * 修改决策点
     */
    @PreAuthorize("@ss.hasPermi('system:points:edit')")
    @Log(title = "决策点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessDecisionPoints businessDecisionPoints)
    {
        return toAjax(businessDecisionPointsService.updateBusinessDecisionPoints(businessDecisionPoints));
    }

    /**
     * 删除决策点
     */
    @PreAuthorize("@ss.hasPermi('system:points:remove')")
    @Log(title = "决策点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessDecisionPointsService.deleteBusinessDecisionPointsByIds(ids));
    }
}
