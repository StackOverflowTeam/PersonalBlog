package team.stackoverflow.personalsite.controller;

import org.springframework.web.bind.annotation.*;
import team.stackoverflow.personalsite.pojo.BlogTagRelation;
import team.stackoverflow.personalsite.pojo.Link;
import team.stackoverflow.personalsite.service.LinkService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;
import team.stackoverflow.personalsite.util.RespBean;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-03 003 00:31:01
 * @Version 1.0
 */

@RestController
@RequestMapping("/links")
public class LinkController {
	@Resource
	private LinkService linkService;
	
	@GetMapping("/list")
	public PageResult list(@RequestParam Map<String, Object> params) {
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return linkService.getBlogLinkPage(pageUtil);
	}
	
	//友链添加
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public RespBean save(@RequestParam("linkType") Integer linkType,
	                     @RequestParam("linkName") String linkName,
	                     @RequestParam("linkUrl") String linkUrl,
	                     @RequestParam("linkRank") Integer linkRank,
	                     @RequestParam("linkDescription") String linkDescription) {
		Link link = new Link();
		link.setLinkType(linkType.byteValue());
		link.setLinkRank(linkRank);
		link.setLinkName(linkName);
		link.setLinkUrl(linkUrl);
		link.setLinkDescription(linkDescription);
		if (linkService.saveLink(link)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	//详情
	@GetMapping("/info/{id}")
	public Link info(@PathVariable("id") Integer id) {
		return linkService.selectById(id);
	}
	
	//友链修改
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public RespBean update(@RequestParam("linkId") Integer linkId,
	                       @RequestParam("linkType") Integer linkType,
	                       @RequestParam("linkName") String linkName,
	                       @RequestParam("linkUrl") String linkUrl,
	                       @RequestParam("linkRank") Integer linkRank,
	                       @RequestParam("linkDescription") String linkDescription) {
		Link tempLink = linkService.selectById(linkId);
		tempLink.setLinkType(linkType.byteValue());
		tempLink.setLinkRank(linkRank);
		tempLink.setLinkName(linkName);
		tempLink.setLinkUrl(linkUrl);
		tempLink.setLinkDescription(linkDescription);
		if (linkService.updateLink(tempLink)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	//友链删除
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public RespBean delete(@RequestBody Integer[] ids) {
		if (linkService.deleteBatch(ids)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}

	//获取数量
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public int countLink() {
		return linkService.getTotalLinks();
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public RespBean updataStatus(Map<String, Object> stateMap) {
		int result = linkService.updateStatus(stateMap);
		if (result > 0) {
			return new RespBean("success", "success");
		}
		return new RespBean("error", "error");
	}

	@RequestMapping(value = "/linkSave", method = RequestMethod.POST)
	public RespBean saveLink(@RequestBody Link link) {
		int result = 0;
		if (link.getLinkId() == null) {
			result = linkService.savelink(link);
		} else {
			result = linkService.updatelink(link);
		}
		if (result > 0) {
			return new RespBean("success", "success");
		}
		return new RespBean("error", "error");
	}
}
