package team.stackoverflow.personalsite.service;

import team.stackoverflow.personalsite.pojo.BlogTag;
import team.stackoverflow.personalsite.pojo.BlogTagCount;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 23:59:06
 * @Version 1.0
 */

public interface BlogTagService {
    /**
     * 查询标签的分页数据
     *
     * @param pageUtil
     * @return
     */
    //获取总体信息
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    int getTotalTags();

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();

    //zq
    int saveTags(BlogTag tag);

    int updateTags(BlogTag tag);

    int updateStatus(Map<String, Object> stateMap);

    BlogTag selectByPrimaryKey(Integer tagId);
}
