package team.stackoverflow.personalsite.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.stackoverflow.personalsite.pojo.AdminUser;
import team.stackoverflow.personalsite.service.AdminUserService;
import team.stackoverflow.personalsite.util.RespBean;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

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
    @Resource
    private DefaultKaptcha captchaProducer;

//    /**
//     * @return
//     * @Author 张清
//     * @Description 登录接口
//     * @Date 2020/7/2 8:13
//     * @Param
//     **/
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public AdminUser login(@RequestBody AdminUser adminUser) {
//        return adminUserService.login(adminUser);
//    }
    
    //验证码
    @GetMapping("/captcha")
    public void defaultCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaOutputStream;
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String verifyCode = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
            BufferedImage challenge = captchaProducer.createImage(verifyCode);
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
    
    //带session的登录接口
    @PostMapping(value = "/login")
    public RespBean login(@RequestParam("userName") String userName,
                          @RequestParam("password") String password,
                          @RequestParam("verifyCode") String verifyCode,
                          HttpSession session) {
        String captchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(captchaCode) || !verifyCode.equals(captchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return new RespBean("error", "failure");
        }
        AdminUser adminUser = adminUserService.login(userName, password);
        if (adminUser != null) {
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            //session过期时间设置为7200秒 即两小时
            session.setMaxInactiveInterval(60 * 60 * 2);
            return new RespBean("success", "success");
        } else {
            session.setAttribute("errorMsg", "登陆失败");
            return new RespBean("error", "failure");
        }
    }
    
    @GetMapping("/profile")
    public RespBean profile(HttpServletRequest request) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null) {
            return new RespBean("error", "failure");
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return new RespBean("success", "success");
    }
    
    //修改密码
    @PostMapping("/profile/password")
    public RespBean passwordUpdate(HttpServletRequest request,
                                   @RequestParam("originalPassword") String originalPassword,
                                   @RequestParam("newPassword") String newPassword) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassword(loginUserId, originalPassword, newPassword)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return new RespBean("success", "success");
        } else {
            return new RespBean("error", "failure");
        }
    }
    
    @PostMapping("/profile/name")
    public RespBean nameUpdate(HttpServletRequest request,
                               @RequestParam("loginUserName") String loginUserName,
                               @RequestParam("nickName") String nickName) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
            return new RespBean("success", "success");
        } else {
            return new RespBean("error", "failure");
        }
    }
    
    @GetMapping("/logout")
    public RespBean logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return new RespBean("success", "success");
    }
}
