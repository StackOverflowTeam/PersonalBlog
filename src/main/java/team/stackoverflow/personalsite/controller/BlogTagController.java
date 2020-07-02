package team.stackoverflow.personalsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.stackoverflow.personalsite.service.BlogTagService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.Result;
import team.stackoverflow.personalsite.util.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-03 003 00:23:52
 * @Version 1.0
 */

@Controller
public class BlogTagController {
	@Resource
	private BlogTagService tagService;
	
	@GetMapping("/tags")
	public String tagPage(HttpServletRequest request) {
		request.setAttribute("path", "tags");
		return "tag";
	}
	
	@GetMapping("/tags/list")
	@ResponseBody
	public Result list(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(tagService.getBlogTagPage(pageUtil));
	}
	
	
	@PostMapping("/tags/save")
	@ResponseBody
	public Result save(@RequestParam("tagName") String tagName) {
		if (StringUtils.isEmpty(tagName)) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		if (tagService.saveTag(tagName)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("标签名称重复");
		}
	}
	
	@PostMapping("/tags/delete")
	@ResponseBody
	public Result delete(@RequestBody Integer[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		if (tagService.deleteBatch(ids)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("有关联数据请勿强行删除");
		}
	}
}
