package team.stackoverflow.personalsite.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import team.stackoverflow.personalsite.mapper.LinkMapper;
import team.stackoverflow.personalsite.pojo.Link;
import team.stackoverflow.personalsite.service.LinkService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-03 003 00:20:59
 * @Version 1.0
 */

@Service
public class LinkServiceImpl implements LinkService {
	@Resource
	LinkMapper linkMapper;
	
	@Override
	public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
		List<Link> links = linkMapper.findLinkList(pageUtil);
		int total = linkMapper.getTotalLinks(pageUtil);
		PageResult pageResult = new PageResult(links, total, pageUtil.getLimit(), pageUtil.getPage());
		return pageResult;
	}
	
	@Override
	public int getTotalLinks() {
		return linkMapper.getTotalLinks(null);
	}
	
	@Override
	public Boolean saveLink(Link link) {
		return linkMapper.insertSelective(link) > 0;
	}
	
	@Override
	public Link selectById(Integer id) {
		return linkMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Boolean updateLink(Link tempLink) {
		return linkMapper.updateByPrimaryKeySelective(tempLink) > 0;
	}
	
	@Override
	public Boolean deleteBatch(Integer[] ids) {
		return linkMapper.deleteBatch(ids) > 0;
	}
	
	@Override
	public Map<Byte, List<Link>> getLinksForLinkPage() {
		//获取所有链接数据
		List<Link> links = linkMapper.findLinkList(null);
		if (!CollectionUtils.isEmpty(links)) {
			//根据type进行分组
			Map<Byte, List<Link>> linksMap = links.stream().collect(Collectors.groupingBy(Link::getLinkType));
			return linksMap;
		}
		return null;
	}

	@Override
	public int updateStatus(Map<String, Object> stateMap) {
		return linkMapper.updateStatus(stateMap);
	}

	@Override
	public int savelink(Link link) {
		return linkMapper.insertSelective(link);
	}

	@Override
	public int updatelink(Link link) {
		return linkMapper.updateByPrimaryKeySelective(link);
	}
}
