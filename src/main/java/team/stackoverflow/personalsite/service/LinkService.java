package team.stackoverflow.personalsite.service;

import team.stackoverflow.personalsite.pojo.Link;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-03 003 00:20:05
 * @Version 1.0
 */

public interface LinkService {
	/**
	 * 查询友链的分页数据
	 *
	 * @param pageUtil
	 * @return
	 */
	PageResult getBlogLinkPage(PageQueryUtil pageUtil);
	
	int getTotalLinks();
	
	Boolean saveLink(Link link);
	
	Link selectById(Integer id);
	
	Boolean updateLink(Link tempLink);
	
	Boolean deleteBatch(Integer[] ids);
	
	/**
	 * 返回友链页面所需的所有数据
	 *
	 * @return
	 */
	Map<Byte, List<Link>> getLinksForLinkPage();
}
