package team.stackoverflow.personalsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.stackoverflow.personalsite.service.BlogCategoryService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.Result;
import team.stackoverflow.personalsite.util.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 22:57:22
 * @Version 1.0
 */

@Controller
public class BlogCategoryController {
	@Resource
	private BlogCategoryService blogCategoryService;
	
	@GetMapping("/categories")
	public String categoryPage(HttpServletRequest request) {
		request.setAttribute("path", "categories");
		return "category";
	}
	
	/**
	 * 分类列表
	 */
	@RequestMapping(value = "/categories/list", method = RequestMethod.GET)
	@ResponseBody
	public Result list(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(blogCategoryService.getBlogCategoryPage(pageUtil));
	}
	
	/**
	 * 分类添加
	 */
	@RequestMapping(value = "/categories/save", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@RequestParam("categoryName") String categoryName,
	                   @RequestParam("categoryIcon") String categoryIcon) {
		if (StringUtils.isEmpty(categoryName)) {
			return ResultGenerator.genFailResult("请输入分类名称！");
		}
		if (StringUtils.isEmpty(categoryIcon)) {
			return ResultGenerator.genFailResult("请选择分类图标！");
		}
		if (blogCategoryService.saveCategory(categoryName, categoryIcon)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("分类名称重复");
		}
	}
	
	
	/**
	 * 分类修改
	 */
	@RequestMapping(value = "/categories/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(@RequestParam("categoryId") Integer categoryId,
	                     @RequestParam("categoryName") String categoryName,
	                     @RequestParam("categoryIcon") String categoryIcon) {
		if (StringUtils.isEmpty(categoryName)) {
			return ResultGenerator.genFailResult("请输入分类名称！");
		}
		if (StringUtils.isEmpty(categoryIcon)) {
			return ResultGenerator.genFailResult("请选择分类图标！");
		}
		if (blogCategoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("分类名称重复");
		}
	}
	
	
	/**
	 * 分类删除
	 */
	@RequestMapping(value = "/categories/delete", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@RequestBody Integer[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		if (blogCategoryService.deleteBatch(ids)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("删除失败");
		}
	}
}
