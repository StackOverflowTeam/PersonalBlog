package team.stackoverflow.personalsite.controller;

import org.springframework.web.bind.annotation.*;
import team.stackoverflow.personalsite.pojo.BlogTag;
import team.stackoverflow.personalsite.service.BlogTagService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;
import team.stackoverflow.personalsite.util.RespBean;

import javax.annotation.Resource;
import java.util.Date;
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
//		public RespBean save(@RequestBody String tagName) {
//			System.out.println(tagName);
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

	//	这下面增加的 有些没用上 先写着再说吧
	//新增&更新
	@RequestMapping(value = "/saveTag", method = RequestMethod.POST)
	public RespBean saveTag(@RequestBody BlogTag tag) {
		int result = 0;
		if (tag.getTagId() == null) {
			tag.setCreateTime(new Date());
			result = tagService.saveTags(tag);
		} else {
			result = tagService.updateTags(tag);
		}
		if (result > 0) {
			return new RespBean("success", "success");
		}
		return new RespBean("error", "error");
	}

	//获取单个信息
	@RequestMapping(value = "/getTagById", method = RequestMethod.POST)
	public BlogTag getId(@RequestParam Integer tagId) {
		return tagService.selectByPrimaryKey(tagId);
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public RespBean updateStatus(@RequestBody Map<String, Object> stateMap) {
//        System.out.println(stateMap.size());
		int result = 0;
		result = tagService.updateStatus(stateMap);
//		System.out.println(tagService.updateStatus(stateMap));
		if (result > 0) {
			return new RespBean("success", "success");
		}
		return new RespBean("error", "error");
	}

}
