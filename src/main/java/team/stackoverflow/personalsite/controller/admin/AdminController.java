package team.stackoverflow.personalsite.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.stackoverflow.personalsite.pojo.AdminUser;
import team.stackoverflow.personalsite.pojo.Blog;
import team.stackoverflow.personalsite.service.AdminUserService;
import team.stackoverflow.personalsite.service.BlogService;
import team.stackoverflow.personalsite.util.RespBean;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static team.stackoverflow.personalsite.util.TimeUtil.getDateTimeString;

/**
 * @ClassName AdminController
 * @Description 后台相关接口
 * @Author ASUS
 * @Date 2020/7/1 16:19
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private BlogService blogService;

    /**
     * @return
     * @Author 张清
     * @Description 登录接口
     * @Date 2020/7/2 8:13
     * @Param
     **/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AdminUser login(@RequestBody AdminUser adminUser) {
        return adminUserService.login(adminUser);
    }

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
        int result = 0;
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
