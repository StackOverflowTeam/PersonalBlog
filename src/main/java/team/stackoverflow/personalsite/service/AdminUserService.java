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
    AdminUser login(AdminUser adminUser);
}
