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
import com.elvel.system.domain.BusinessScenes;
import com.elvel.system.service.IBusinessScenesService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 场景Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/scenes")
public class BusinessScenesController extends BaseController
{
    @Autowired
    private IBusinessScenesService businessScenesService;

    /**
     * 查询场景列表
     */
    @PreAuthorize("@ss.hasPermi('system:scenes:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessScenes businessScenes)
    {
        startPage();
        List<BusinessScenes> list = businessScenesService.selectBusinessScenesList(businessScenes);
        return getDataTable(list);
    }

    /**
     * 导出场景列表
     */
    @PreAuthorize("@ss.hasPermi('system:scenes:export')")
    @Log(title = "场景", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessScenes businessScenes)
    {
        List<BusinessScenes> list = businessScenesService.selectBusinessScenesList(businessScenes);
        ExcelUtil<BusinessScenes> util = new ExcelUtil<BusinessScenes>(BusinessScenes.class);
        util.exportExcel(response, list, "场景数据");
    }

    /**
     * 获取场景详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:scenes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessScenesService.selectBusinessScenesById(id));
    }

    /**
     * 新增场景
     */
    @PreAuthorize("@ss.hasPermi('system:scenes:add')")
    @Log(title = "场景", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessScenes businessScenes)
    {
        return toAjax(businessScenesService.insertBusinessScenes(businessScenes));
    }

    /**
     * 修改场景
     */
    @PreAuthorize("@ss.hasPermi('system:scenes:edit')")
    @Log(title = "场景", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessScenes businessScenes)
    {
        return toAjax(businessScenesService.updateBusinessScenes(businessScenes));
    }

    /**
     * 删除场景
     */
    @PreAuthorize("@ss.hasPermi('system:scenes:remove')")
    @Log(title = "场景", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessScenesService.deleteBusinessScenesByIds(ids));
    }
}
