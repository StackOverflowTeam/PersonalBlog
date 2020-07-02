package team.stackoverflow.personalsite.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team.stackoverflow.personalsite.pojo.AdminUser;
import team.stackoverflow.personalsite.service.AdminUserService;

import javax.annotation.Resource;

/**
 * @ClassName AdminController
 * @Description 后台相关接口
 * @Author ASUS
 * @Date 2020/7/1 16:19
 */

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Resource
    private AdminUserService adminUserService;

    /**
     * @return
     * @Author 张清
     * @Description 登录接口
     * @Date 2020/7/2 8:13
     * @Param
     **/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AdminUser login(@RequestBody AdminUser adminUser) {
        return adminUserService.login(adminUser);
    }
}
