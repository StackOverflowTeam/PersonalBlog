package team.stackoverflow.personalsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.stackoverflow.personalsite.pojo.BlogTag;
import team.stackoverflow.personalsite.pojo.BlogTagCount;
import team.stackoverflow.personalsite.util.PageQueryUtil;

import java.util.List;

@Mapper
public interface BlogTagMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);
    
    List<BlogTag> findTagList(PageQueryUtil pageUtil);
    
    List<BlogTagCount> getTagCount();
    
    int getTotalTags(PageQueryUtil pageUtil);
    
    int deleteBatch(Integer[] ids);
    
    int batchInsertBlogTag(List<BlogTag> tagList);
    
    BlogTag selectByTagName(String tagName);
}