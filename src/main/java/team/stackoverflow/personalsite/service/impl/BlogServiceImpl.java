package team.stackoverflow.personalsite.service.impl;

import org.springframework.stereotype.Service;
import team.stackoverflow.personalsite.mapper.BlogMapper;
import team.stackoverflow.personalsite.pojo.Blog;
import team.stackoverflow.personalsite.service.BlogService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogServiceImpl
 * @Description TODO
 * @Author ASUS
 * @Date 2020/7/2 8:35
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;
    
    @Override
    public PageResult getBlogPage(PageQueryUtil pageUtil) {
        List<Blog> blogList = blogMapper.findBlogList(pageUtil);
        int total = blogMapper.getTotalBlogs(pageUtil);
        return new PageResult(blogList, total, pageUtil.getLimit(), pageUtil.getPage());
    }

    @Override
    public Blog selectByPrimaryKey(Long blogId) {
        return blogMapper.selectByPrimaryKey(blogId);
    }

    @Override
    public int saveBlog(Blog blog) {
        return blogMapper.insertSelective(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateByPrimaryKeySelective(blog);
    }

    @Override
    public int updateState(Map<String, Object> stateMap) {
        return 0;
    }
    
    @Override
    public int getTotalBlogs() {
        return blogMapper.getTotalBlogs(null);
    }
}
