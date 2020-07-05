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
    public AdminUser login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.login(userName, passwordMd5);
    }
    
    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }
    
    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //比较原密码是否正确
            if (originalPasswordMd5.equals(adminUser.getLoginPassword())) {
                //设置新密码并修改
                adminUser.setLoginPassword(newPasswordMd5);
                //修改成功则返回true
                return adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0;
            }
        }
        return false;
    }
    
    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //设置新密码并修改
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            //修改成功则返回true
            return adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0;
        }
        return false;
    }
}
