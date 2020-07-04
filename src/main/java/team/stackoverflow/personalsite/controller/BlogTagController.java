package team.stackoverflow.personalsite.controller;

import org.springframework.web.bind.annotation.*;
import team.stackoverflow.personalsite.service.BlogTagService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;
import team.stackoverflow.personalsite.util.RespBean;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-03 003 00:23:52
 * @Version 1.0
 */

@RestController
@RequestMapping("/tags")
public class BlogTagController {
	@Resource
	private BlogTagService tagService;
	
	@GetMapping("/list")
	public PageResult list(@RequestParam Map<String, Object> params) {
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return tagService.getBlogTagPage(pageUtil);
	}
	
	
	@PostMapping("/save")
	public RespBean save(@RequestParam("tagName") String tagName) {
		if (tagService.saveTag(tagName)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	@PostMapping("/delete")
	public RespBean delete(@RequestBody Integer[] ids) {
		if (tagService.deleteBatch(ids)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public int countTag() {
		return tagService.getTotalTags();
	}
}
