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
import com.elvel.system.domain.BusinessReadingRecords;
import com.elvel.system.service.IBusinessReadingRecordsService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 阅读记录Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/records")
public class BusinessReadingRecordsController extends BaseController
{
    @Autowired
    private IBusinessReadingRecordsService businessReadingRecordsService;

    /**
     * 查询阅读记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessReadingRecords businessReadingRecords)
    {
        startPage();
        List<BusinessReadingRecords> list = businessReadingRecordsService.selectBusinessReadingRecordsList(businessReadingRecords);
        return getDataTable(list);
    }

    /**
     * 导出阅读记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:records:export')")
    @Log(title = "阅读记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessReadingRecords businessReadingRecords)
    {
        List<BusinessReadingRecords> list = businessReadingRecordsService.selectBusinessReadingRecordsList(businessReadingRecords);
        ExcelUtil<BusinessReadingRecords> util = new ExcelUtil<BusinessReadingRecords>(BusinessReadingRecords.class);
        util.exportExcel(response, list, "阅读记录数据");
    }

    /**
     * 获取阅读记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:records:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessReadingRecordsService.selectBusinessReadingRecordsById(id));
    }

    /**
     * 新增阅读记录
     */
    @PreAuthorize("@ss.hasPermi('system:records:add')")
    @Log(title = "阅读记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessReadingRecords businessReadingRecords)
    {
        return toAjax(businessReadingRecordsService.insertBusinessReadingRecords(businessReadingRecords));
    }

    /**
     * 修改阅读记录
     */
    @PreAuthorize("@ss.hasPermi('system:records:edit')")
    @Log(title = "阅读记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessReadingRecords businessReadingRecords)
    {
        return toAjax(businessReadingRecordsService.updateBusinessReadingRecords(businessReadingRecords));
    }

    /**
     * 删除阅读记录
     */
    @PreAuthorize("@ss.hasPermi('system:records:remove')")
    @Log(title = "阅读记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessReadingRecordsService.deleteBusinessReadingRecordsByIds(ids));
    }
}
