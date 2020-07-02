package team.stackoverflow.personalsite.service;

import team.stackoverflow.personalsite.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogService
 * @Description TODO
 * @Author ASUS
 * @Date 2020/7/2 8:35
 */
public interface BlogService {

    List<Blog> getBlogsListByConditionPages(Map<String, Object> blogMap);

    int getCount(Map<String, Object> blogMap);

    Blog selectByPrimaryKey(Long blogId);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int updateState(Map<String, Object> stateMap);
}
