package team.stackoverflow.personalsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.stackoverflow.personalsite.pojo.Blog;
import team.stackoverflow.personalsite.util.PageQueryUtil;

import java.util.List;

@Mapper
public interface BlogMapper {
    int deleteByPrimaryKey(Long blogId);

    int insert(Blog record);
    
    List<Blog> findBlogList(PageQueryUtil pageUtil);

    int insertSelective(Blog record);
    
    int getTotalBlogs(PageQueryUtil pageUtil);

    Blog selectByPrimaryKey(Long blogId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);
    
    int updateBlogCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId, @Param("ids")Integer[] ids);
    
    int deleteBatch(Integer[] ids);

//    有两种状态需要更新
//    int updateStatus(Map<String,Object> statusMap);
}