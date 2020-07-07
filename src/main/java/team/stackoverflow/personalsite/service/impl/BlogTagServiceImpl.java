package team.stackoverflow.personalsite.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import team.stackoverflow.personalsite.mapper.BlogTagMapper;
import team.stackoverflow.personalsite.mapper.BlogTagRelationMapper;
import team.stackoverflow.personalsite.pojo.BlogTag;
import team.stackoverflow.personalsite.pojo.BlogTagCount;
import team.stackoverflow.personalsite.service.BlogTagService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-03 003 00:00:53
 * @Version 1.0
 */

@Service
public class BlogTagServiceImpl implements BlogTagService {
	@Resource
	BlogTagMapper blogTagMapper;
	@Resource
	BlogTagRelationMapper relationMapper;
	
	@Override
	public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
		List<BlogTag> tags = blogTagMapper.findTagList(pageUtil);
		int total = blogTagMapper.getTotalTags(pageUtil);
		PageResult pageResult = new PageResult(tags, total, pageUtil.getLimit(), pageUtil.getPage());
		return pageResult;
	}
	
	@Override
	public int getTotalTags() {
		return blogTagMapper.getTotalTags(null);
	}
	
	@Override
	public Boolean saveTag(String tagName) {
		BlogTag temp = blogTagMapper.selectByTagName(tagName);
		if (temp == null) {
			BlogTag blogTag = new BlogTag();
			blogTag.setTagName(tagName);
			return blogTagMapper.insertSelective(blogTag) > 0;
		}
		return false;
	}
	
	@Override
	public Boolean deleteBatch(Integer[] ids) {
		//已存在关联关系不删除
		List<Long> relations = relationMapper.selectDistinctTagIds(ids);
		if (!CollectionUtils.isEmpty(relations)) {
            return false;
        }
        //删除tag
        return blogTagMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        return blogTagMapper.getTagCount();
    }

    @Override
    public int saveTags(BlogTag tag) {
        return blogTagMapper.insertSelective(tag);
    }

    @Override
    public int updateTags(BlogTag tag) {
        return blogTagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public int updateStatus(Map<String, Object> stateMap) {
        return blogTagMapper.updateStatus(stateMap);
    }

    @Override
    public BlogTag selectByPrimaryKey(Integer tagId) {
        return blogTagMapper.selectByPrimaryKey(tagId);
    }
}
