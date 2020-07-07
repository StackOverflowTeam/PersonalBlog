package team.stackoverflow.personalsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.stackoverflow.personalsite.pojo.BlogTag;
import team.stackoverflow.personalsite.pojo.BlogTagCount;
import team.stackoverflow.personalsite.util.PageQueryUtil;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogTagMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(BlogTag record);

    int insertSelective(BlogTag record); //zq will get

    BlogTag selectByPrimaryKey(Integer tagId);    //zq will get

    int updateByPrimaryKeySelective(BlogTag record);    //zq will get

    int updateByPrimaryKey(BlogTag record);

    List<BlogTag> findTagList(PageQueryUtil pageUtil);

    List<BlogTagCount> getTagCount();

    int getTotalTags(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

    int batchInsertBlogTag(List<BlogTag> tagList);

    BlogTag selectByTagName(String tagName);

    //zq
    int updateStatus(Map<String, Object> stateMap); //zq will get
}