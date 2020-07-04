package team.stackoverflow.personalsite.controller;

import org.springframework.web.bind.annotation.*;
import team.stackoverflow.personalsite.service.BlogCommentService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;
import team.stackoverflow.personalsite.util.RespBean;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 23:51:21
 * @Version 1.0
 */

@RestController
@RequestMapping("/comments")
public class BlogCommentController {
	@Resource
	private BlogCommentService blogCommentService;
	
	@GetMapping("/list")
	public PageResult list(@RequestParam Map<String, Object> params) {
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return blogCommentService.getCommentsPage(pageUtil);
	}
	
	@PostMapping("/checkDone")
	public RespBean checkDone(@RequestBody Integer[] ids) {
		if (blogCommentService.checkDone(ids)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	@PostMapping("/reply")
	public RespBean checkDone(@RequestParam("commentId") Integer commentId,
	                          @RequestParam("replyBody") String replyBody) {
		if (blogCommentService.reply(commentId, replyBody)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	@PostMapping("/delete")
	public RespBean delete(@RequestBody Integer[] ids) {
		if (blogCommentService.deleteBatch(ids)) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public int countComment() {
		return blogCommentService.getTotalComments();
	}
}
