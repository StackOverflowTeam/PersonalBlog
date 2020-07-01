package team.stackoverflow.personalsite.mapper;

import team.stackoverflow.personalsite.pojo.BlogComment;

public interface BlogCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(BlogComment record);

    int insertSelective(BlogComment record);

    BlogComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKey(BlogComment record);
}