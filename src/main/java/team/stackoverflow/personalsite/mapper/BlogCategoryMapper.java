package team.stackoverflow.personalsite.mapper;

import team.stackoverflow.personalsite.pojo.BlogCategory;

public interface BlogCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    BlogCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);
}