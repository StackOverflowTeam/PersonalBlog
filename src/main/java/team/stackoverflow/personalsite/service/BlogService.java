package team.stackoverflow.personalsite.service;

import team.stackoverflow.personalsite.pojo.Blog;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;

import java.util.Map;

/**
 * @ClassName BlogService
 * @Description TODO
 * @Author ASUS
 * @Date 2020/7/2 8:35
 */

public interface BlogService {
    PageResult getBlogPage(PageQueryUtil pageUtil);

    Blog selectByPrimaryKey(Long blogId);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int updateState(Map<String, Object> stateMap);
    
    int getTotalBlogs();
}
