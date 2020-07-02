package team.stackoverflow.personalsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.stackoverflow.personalsite.pojo.Blog;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper {
    int deleteByPrimaryKey(Long blogId);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long blogId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> getBlogsListByConditionPages(Map<String, Object> blogMap);

    int getCount(Map<String, Object> blogMap);
    
    int updateBlogCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId, @Param("ids")Integer[] ids);

//    有两种状态需要更新
//    int updateStatus(Map<String,Object> statusMap);
}