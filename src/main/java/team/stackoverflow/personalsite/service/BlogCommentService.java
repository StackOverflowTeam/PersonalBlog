package team.stackoverflow.personalsite.service;

import team.stackoverflow.personalsite.pojo.BlogComment;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 23:45:42
 * @Version 1.0
 */

public interface BlogCommentService {
	/**
	 * 添加评论
	 *
	 * @param blogComment
	 * @return
	 */
	Boolean addComment(BlogComment blogComment);
	
	/**
	 * 后台管理系统中评论分页功能
	 *
	 * @param pageUtil
	 * @return
	 */
	PageResult getCommentsPage(PageQueryUtil pageUtil);
	
	int getTotalComments();
	
	/**
	 * 批量审核
	 *
	 * @param ids
	 * @return
	 */
	Boolean checkDone(Integer[] ids);
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	Boolean deleteBatch(Integer[] ids);
	
	/**
	 * 添加回复
	 *
	 * @param commentId
	 * @param replyBody
	 * @return
	 */
	Boolean reply(Integer commentId, String replyBody);
	
	/**
	 * 根据文章id和分页参数获取文章的评论列表
	 *
	 * @param blogId
	 * @param page
	 * @return
	 */
	PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page);
}
