
package team.stackoverflow.personalsite.controller;

import org.springframework.web.bind.annotation.*;
import team.stackoverflow.personalsite.service.BlogCategoryService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;
import team.stackoverflow.personalsite.util.RespBean;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 22:57:22
 * @Version 1.0
 */

@RestController
@RequestMapping("/categories")
public class BlogCategoryController {
	@Resource
	private BlogCategoryService blogCategoryService;
	
	//分类列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public PageResult list(@RequestParam Map<String, Object> params) {
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return blogCategoryService.getBlogCategoryPage(pageUtil);
	}
	
	//分类添加
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public RespBean save(@RequestParam("categoryName") String categoryName,
	                     @RequestParam("categoryIcon") String categoryIcon) {
		if (blogCategoryService.saveCategory(categoryName, categoryIcon)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	
	//分类修改
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public RespBean update(@RequestParam("categoryId") Integer categoryId,
	                       @RequestParam("categoryName") String categoryName,
	                       @RequestParam("categoryIcon") String categoryIcon) {
		if (blogCategoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	
	//分类删除
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
	public RespBean delete(@RequestBody Map<String, Object> jsonParam) {
		Integer[] ids = (Integer[]) jsonParam.get("ids");
		if (blogCategoryService.deleteBatch(ids)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}

	//分类数量
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public int countCategory() {
		return blogCategoryService.getTotalCategories();
	}

	//	单个删除 zq
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public RespBean updateStatus(Map<String, Object> stateMap) {
		int result = blogCategoryService.updateStatus(stateMap);
		if (result > 0) {
			return new RespBean("success", "success");
		}
		return new RespBean("error", "error");
	}
}
