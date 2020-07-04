package team.stackoverflow.personalsite.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.stackoverflow.personalsite.pojo.Blog;
import team.stackoverflow.personalsite.service.BlogService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;
import team.stackoverflow.personalsite.util.RespBean;

import javax.annotation.Resource;
import java.util.Map;

import static team.stackoverflow.personalsite.util.TimeUtil.getDateTimeString;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 22:54:22
 * @Version 1.0
 */

@RestController
@RequestMapping("/blogs")
public class BlogController {
	@Resource
	private BlogService blogService;
	
	/**
	 * @return
	 * @Author 张清
	 * @Description 分页显示
	 * @Date 2020/7/2 10:19
	 * @Param
	 **/
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public PageResult list(@RequestParam Map<String, Object> params) {
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return blogService.getBlogPage(pageUtil);
	}
	
	/**
	 * @return
	 * @Author 张清
	 * @Description 根据blogid返回blog
	 * @Date 2020/7/2 11:19
	 * @Param
	 **/
	@RequestMapping(value = "/id", method = RequestMethod.POST)
	public Blog getBlog(@RequestParam Long blogId) {
		return blogService.selectByPrimaryKey(blogId);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public RespBean saveBlog(@RequestParam Blog blog) {
		int result;
		if (blog.getBlogId() == null) {
			blog.setCreateTime(getDateTimeString());
			result = blogService.saveBlog(blog);
		} else {
			result = blogService.updateBlog(blog);
		}
		if (result > 0) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	//获取数量
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public int countBlog() {
		return blogService.getTotalBlogs();
	}
}
