package team.stackoverflow.personalsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import team.stackoverflow.personalsite.pojo.Blog;
import team.stackoverflow.personalsite.service.BlogService;
import team.stackoverflow.personalsite.util.RespBean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static team.stackoverflow.personalsite.util.TimeUtil.getDateTimeString;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 22:54:22
 * @Version 1.0
 */

@Controller
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
	@RequestMapping(value = "/blogList", method = RequestMethod.POST)
	public Map<String, Object> getBlogsListByConditionPages(@RequestBody Map<String, Object> blogMap) {
		int count = blogService.getCount(blogMap);
		List<Blog> blogList = blogService.getBlogsListByConditionPages(blogMap);
		Map<String, Object> objectMap = new HashMap<>();
		objectMap.put("count", count);
		objectMap.put("list", blogList);
		return objectMap;
	}
	
	/**
	 * @return
	 * @Author 张清
	 * @Description 根据blogid返回blog
	 * @Date 2020/7/2 11:19
	 * @Param
	 **/
	@RequestMapping(value = "/getBlogByBlogId", method = RequestMethod.POST)
	public Blog getBlog(@RequestParam Long blogId) {
		return blogService.selectByPrimaryKey(blogId);
	}
	
	@RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
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
}
