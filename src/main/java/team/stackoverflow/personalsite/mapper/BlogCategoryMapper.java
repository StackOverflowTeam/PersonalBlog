package team.stackoverflow.personalsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.stackoverflow.personalsite.pojo.BlogCategory;
import team.stackoverflow.personalsite.util.PageQueryUtil;

import java.util.List;

@Mapper
public interface BlogCategoryMapper {
	int deleteByPrimaryKey(Integer categoryId);
	
	int insert(BlogCategory record);
	
	int insertSelective(BlogCategory record);
	
	BlogCategory selectByPrimaryKey(Integer categoryId);
	
	BlogCategory selectByCategoryName(String categoryName);
	
	int updateByPrimaryKeySelective(BlogCategory record);
	
	int updateByPrimaryKey(BlogCategory record);
	
	List<BlogCategory> findCategoryList(PageQueryUtil pageUtil);
	
	List<BlogCategory> selectByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);
	
	int getTotalCategories(PageQueryUtil pageUtil);
	
	int deleteBatch(Integer[] ids);
}