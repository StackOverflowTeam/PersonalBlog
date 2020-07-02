package team.stackoverflow.personalsite.service.impl;

import org.springframework.stereotype.Service;
import team.stackoverflow.personalsite.mapper.AdminUserMapper;
import team.stackoverflow.personalsite.pojo.AdminUser;
import team.stackoverflow.personalsite.service.AdminUserService;
import team.stackoverflow.personalsite.util.MD5Util;

import javax.annotation.Resource;

/**
 * @ClassName AdminUserServiceImpl
 * @Description TODO
 * @Author 张清
 * @Date 2020/7/1 16:24
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(AdminUser adminUser) {
        String passwordMds5 = MD5Util.MD5Encode(adminUser.getLoginPassword(), "UTF-8");
        adminUser.setLoginPassword(passwordMds5);
        return adminUserMapper.selectByAdminUser(adminUser);
    }
}
