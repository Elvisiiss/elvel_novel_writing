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
import com.elvel.system.domain.BusinessCreationSessions;
import com.elvel.system.service.IBusinessCreationSessionsService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 创作会话Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/sessions")
public class BusinessCreationSessionsController extends BaseController
{
    @Autowired
    private IBusinessCreationSessionsService businessCreationSessionsService;

    /**
     * 查询创作会话列表
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessCreationSessions businessCreationSessions)
    {
        startPage();
        List<BusinessCreationSessions> list = businessCreationSessionsService.selectBusinessCreationSessionsList(businessCreationSessions);
        return getDataTable(list);
    }

    /**
     * 导出创作会话列表
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:export')")
    @Log(title = "创作会话", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessCreationSessions businessCreationSessions)
    {
        List<BusinessCreationSessions> list = businessCreationSessionsService.selectBusinessCreationSessionsList(businessCreationSessions);
        ExcelUtil<BusinessCreationSessions> util = new ExcelUtil<BusinessCreationSessions>(BusinessCreationSessions.class);
        util.exportExcel(response, list, "创作会话数据");
    }

    /**
     * 获取创作会话详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessCreationSessionsService.selectBusinessCreationSessionsById(id));
    }

    /**
     * 新增创作会话
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:add')")
    @Log(title = "创作会话", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessCreationSessions businessCreationSessions)
    {
        return toAjax(businessCreationSessionsService.insertBusinessCreationSessions(businessCreationSessions));
    }

    /**
     * 修改创作会话
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:edit')")
    @Log(title = "创作会话", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessCreationSessions businessCreationSessions)
    {
        return toAjax(businessCreationSessionsService.updateBusinessCreationSessions(businessCreationSessions));
    }

    /**
     * 删除创作会话
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:remove')")
    @Log(title = "创作会话", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessCreationSessionsService.deleteBusinessCreationSessionsByIds(ids));
    }
}
