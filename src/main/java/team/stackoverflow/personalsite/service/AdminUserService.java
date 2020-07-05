package team.stackoverflow.personalsite.service;

import team.stackoverflow.personalsite.pojo.AdminUser;

/**
 * @ClassName AdminUserService
 * @Description TODO
 * @Author 张清
 * @Date 2020/7/1 16:18
 */

public interface AdminUserService {
	/**
	 * @return
	 * @Author 张清
	 * @Description 登录
	 * @Date 2020/7/1 16:21
	 * @Param AdminUser
	 **/
	AdminUser login(String userName, String password);
	
	/**
	 * 获取用户信息
	 *
	 * @param loginUserId
	 * @return
	 */
	AdminUser getUserDetailById(Integer loginUserId);
	
	/**
	 * 修改当前登录用户的密码
	 *
	 * @param loginUserId
	 * @param originalPassword
	 * @param newPassword
	 * @return
	 */
	Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);
	
	/**
	 * 修改当前登录用户的名称信息
	 *
	 * @param loginUserId
	 * @param loginUserName
	 * @param nickName
	 * @return
	 */
	Boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
