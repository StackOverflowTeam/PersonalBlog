package team.stackoverflow.personalsite.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.stackoverflow.personalsite.pojo.Blog;
import team.stackoverflow.personalsite.service.BlogCategoryService;
import team.stackoverflow.personalsite.service.BlogService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;
import team.stackoverflow.personalsite.util.RespBean;
import team.stackoverflow.personalsite.util.TimeUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Random;

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
	@Resource
	private BlogCategoryService blogCategoryService;
	
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
	
	//保存文章
	@PostMapping("/save")
	public RespBean save(@RequestParam("blogTitle") String blogTitle,
	                     @RequestParam(name = "blogSubUrl", required = false) String blogSubUrl,
	                     @RequestParam("blogCategoryId") Integer blogCategoryId,
	                     @RequestParam("blogTags") String blogTags,
	                     @RequestParam("blogContent") String blogContent,
	                     @RequestParam("blogCoverImage") String blogCoverImage,
	                     @RequestParam("blogStatus") Byte blogStatus,
	                     @RequestParam("enableComment") Byte enableComment) {
		Blog blog = new Blog();
		blog.setBlogTitle(blogTitle);
		blog.setBlogSubUrl(blogSubUrl);
		blog.setBlogCategoryId(blogCategoryId);
		blog.setBlogTags(blogTags);
		blog.setBlogContent(blogContent);
		blog.setBlogCoverImage(blogCoverImage);
		blog.setBlogStatus(blogStatus);
		blog.setEnableComment(enableComment);
		if (blogService.saveBlog(blog) > 0) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	//修改文章
	@PostMapping("/update")
	public RespBean update(@RequestParam("blogId") Long blogId,
	                       @RequestParam("blogTitle") String blogTitle,
	                       @RequestParam(name = "blogSubUrl", required = false) String blogSubUrl,
	                       @RequestParam("blogCategoryId") Integer blogCategoryId,
	                       @RequestParam("blogTags") String blogTags,
	                       @RequestParam("blogContent") String blogContent,
	                       @RequestParam("blogCoverImage") String blogCoverImage,
	                       @RequestParam("blogStatus") Byte blogStatus,
	                       @RequestParam("enableComment") Byte enableComment) {
		Blog blog = new Blog();
		blog.setBlogId(blogId);
		blog.setBlogTitle(blogTitle);
		blog.setBlogSubUrl(blogSubUrl);
		blog.setBlogCategoryId(blogCategoryId);
		blog.setBlogTags(blogTags);
		blog.setBlogContent(blogContent);
		blog.setBlogCoverImage(blogCoverImage);
		blog.setBlogStatus(blogStatus);
		blog.setEnableComment(enableComment);
		if (blogService.updateBlog(blog) > 0) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	//上传文件
	@PostMapping("/upload")
	public void upload(HttpServletRequest request,
	                   HttpServletResponse response,
	                   @RequestParam(name = "editor-image-file", required = true)
			                   MultipartFile file) throws IOException, URISyntaxException {
		String fileName = file.getOriginalFilename();
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		//生成文件名称通用方法
		Random r = new Random();
		String newFileName = TimeUtil.getDateTimeString() + r.nextInt(100) + suffixName;
		//创建文件
		File destFile = new File("src/assets/images/upload/" + newFileName);
		String fileUrl = "http://localhost:8080/img/" + newFileName;
		File fileDirectory = new File("src/assets/images/upload/");
		try {
			if (!fileDirectory.exists()) {
				if (!fileDirectory.mkdir()) {
					throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
				}
			}
			file.transferTo(destFile);
			request.setCharacterEncoding("utf-8");
			response.setHeader("Content-Type", "text/html");
			response.getWriter().write("{\"success\": 1, \"message\":\"success\",\"url\":\"" + fileUrl + "\"}");
		} catch (IOException e) {
			response.getWriter().write("{\"success\":0}");
		}
	}
	
	//删除文章
	@PostMapping("/delete")
	@ResponseBody
	public RespBean delete(@RequestBody Integer[] ids) {
		if (blogService.deleteBatch(ids)) {
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
