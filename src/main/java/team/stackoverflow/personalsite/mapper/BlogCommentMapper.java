package team.stackoverflow.personalsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.stackoverflow.personalsite.pojo.BlogComment;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogCommentMapper {
	int deleteByPrimaryKey(Integer commentId);
	
	int insert(BlogComment record);
	
	int insertSelective(BlogComment record);
	
	BlogComment selectByPrimaryKey(Integer commentId);
	
	int updateByPrimaryKeySelective(BlogComment record);
	
	int updateByPrimaryKey(BlogComment record);
	
	List<BlogComment> findBlogCommentList(Map map);
	
	int getTotalBlogComments(Map map);
	
	int checkDone(Integer[] ids);
	
	int deleteBatch(Integer[] ids);
}