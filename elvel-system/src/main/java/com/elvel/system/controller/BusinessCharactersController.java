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
import com.elvel.system.domain.BusinessCharacters;
import com.elvel.system.service.IBusinessCharactersService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 角色Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/characters")
public class BusinessCharactersController extends BaseController
{
    @Autowired
    private IBusinessCharactersService businessCharactersService;

    /**
     * 查询角色列表
     */
    @PreAuthorize("@ss.hasPermi('system:characters:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessCharacters businessCharacters)
    {
        startPage();
        List<BusinessCharacters> list = businessCharactersService.selectBusinessCharactersList(businessCharacters);
        return getDataTable(list);
    }

    /**
     * 导出角色列表
     */
    @PreAuthorize("@ss.hasPermi('system:characters:export')")
    @Log(title = "角色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessCharacters businessCharacters)
    {
        List<BusinessCharacters> list = businessCharactersService.selectBusinessCharactersList(businessCharacters);
        ExcelUtil<BusinessCharacters> util = new ExcelUtil<BusinessCharacters>(BusinessCharacters.class);
        util.exportExcel(response, list, "角色数据");
    }

    /**
     * 获取角色详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:characters:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessCharactersService.selectBusinessCharactersById(id));
    }

    /**
     * 新增角色
     */
    @PreAuthorize("@ss.hasPermi('system:characters:add')")
    @Log(title = "角色", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessCharacters businessCharacters)
    {
        return toAjax(businessCharactersService.insertBusinessCharacters(businessCharacters));
    }

    /**
     * 修改角色
     */
    @PreAuthorize("@ss.hasPermi('system:characters:edit')")
    @Log(title = "角色", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessCharacters businessCharacters)
    {
        return toAjax(businessCharactersService.updateBusinessCharacters(businessCharacters));
    }

    /**
     * 删除角色
     */
    @PreAuthorize("@ss.hasPermi('system:characters:remove')")
    @Log(title = "角色", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessCharactersService.deleteBusinessCharactersByIds(ids));
    }
}
