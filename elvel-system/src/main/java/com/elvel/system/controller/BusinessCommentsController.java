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
import com.elvel.system.domain.BusinessComments;
import com.elvel.system.service.IBusinessCommentsService;
import com.elvel.common.utils.poi.ExcelUtil;
import com.elvel.common.core.page.TableDataInfo;

/**
 * 评论Controller
 *
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/system/comments")
public class BusinessCommentsController extends BaseController
{
    @Autowired
    private IBusinessCommentsService businessCommentsService;

    /**
     * 查询评论列表
     */
    @PreAuthorize("@ss.hasPermi('system:comments:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusinessComments businessComments)
    {
        startPage();
        List<BusinessComments> list = businessCommentsService.selectBusinessCommentsList(businessComments);
        return getDataTable(list);
    }

    /**
     * 导出评论列表
     */
    @PreAuthorize("@ss.hasPermi('system:comments:export')")
    @Log(title = "评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessComments businessComments)
    {
        List<BusinessComments> list = businessCommentsService.selectBusinessCommentsList(businessComments);
        ExcelUtil<BusinessComments> util = new ExcelUtil<BusinessComments>(BusinessComments.class);
        util.exportExcel(response, list, "评论数据");
    }

    /**
     * 获取评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:comments:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(businessCommentsService.selectBusinessCommentsById(id));
    }

    /**
     * 新增评论
     */
    @PreAuthorize("@ss.hasPermi('system:comments:add')")
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessComments businessComments)
    {
        return toAjax(businessCommentsService.insertBusinessComments(businessComments));
    }

    /**
     * 修改评论
     */
    @PreAuthorize("@ss.hasPermi('system:comments:edit')")
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessComments businessComments)
    {
        return toAjax(businessCommentsService.updateBusinessComments(businessComments));
    }

    /**
     * 删除评论
     */
    @PreAuthorize("@ss.hasPermi('system:comments:remove')")
    @Log(title = "评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(businessCommentsService.deleteBusinessCommentsByIds(ids));
    }
}
