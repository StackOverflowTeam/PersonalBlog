package team.stackoverflow.personalsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.stackoverflow.personalsite.service.BlogCommentService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.Result;
import team.stackoverflow.personalsite.util.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 23:51:21
 * @Version 1.0
 */

@Controller
public class BlogCommentController {
	@Resource
	private BlogCommentService blogCommentService;
	
	@GetMapping("/comments/list")
	@ResponseBody
	public Result list(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(blogCommentService.getCommentsPage(pageUtil));
	}
	
	@PostMapping("/comments/checkDone")
	@ResponseBody
	public Result checkDone(@RequestBody Integer[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		if (blogCommentService.checkDone(ids)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("审核失败");
		}
	}
	
	@PostMapping("/comments/reply")
	@ResponseBody
	public Result checkDone(@RequestParam("commentId") Integer commentId,
	                        @RequestParam("replyBody") String replyBody) {
		if (commentId == null || commentId < 1 || StringUtils.isEmpty(replyBody)) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		if (blogCommentService.reply(commentId, replyBody)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("回复失败");
		}
	}
	
	@PostMapping("/comments/delete")
	@ResponseBody
	public Result delete(@RequestBody Integer[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("参数异常！");
		}
		if (blogCommentService.deleteBatch(ids)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("刪除失败");
		}
	}
	
	@GetMapping("/comments")
	public String list(HttpServletRequest request) {
		request.setAttribute("path", "comments");
		return "comment";
	}
}
