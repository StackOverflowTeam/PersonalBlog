package team.stackoverflow.personalsite.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.stackoverflow.personalsite.service.ConfigService;
import team.stackoverflow.personalsite.util.RespBean;

import javax.annotation.Resource;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-03 003 00:25:04
 * @Version 1.0
 */

@RestController
@RequestMapping("/configurations")
public class ConfigController {
	@Resource
	private ConfigService configService;
	
	@PostMapping("/website")
	public RespBean website(@RequestParam(value = "websiteName", required = false) String websiteName,
	                        @RequestParam(value = "websiteDescription", required = false) String websiteDescription,
	                        @RequestParam(value = "websiteLogo", required = false) String websiteLogo,
	                        @RequestParam(value = "websiteIcon", required = false) String websiteIcon) {
		int updateResult = 0;
		if (!StringUtils.isEmpty(websiteName)) {
			updateResult += configService.updateConfig("websiteName", websiteName);
		}
		if (!StringUtils.isEmpty(websiteDescription)) {
			updateResult += configService.updateConfig("websiteDescription", websiteDescription);
		}
		if (!StringUtils.isEmpty(websiteLogo)) {
			updateResult += configService.updateConfig("websiteLogo", websiteLogo);
		}
		if (!StringUtils.isEmpty(websiteIcon)) {
			updateResult += configService.updateConfig("websiteIcon", websiteIcon);
		}
		if (updateResult > 0) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	@PostMapping("/userInfo")
	public RespBean userInfo(@RequestParam(value = "yourAvatar", required = false) String yourAvatar,
	                         @RequestParam(value = "yourName", required = false) String yourName,
	                         @RequestParam(value = "yourEmail", required = false) String yourEmail) {
		int updateResult = 0;
		if (!StringUtils.isEmpty(yourAvatar)) {
			updateResult += configService.updateConfig("yourAvatar", yourAvatar);
		}
		if (!StringUtils.isEmpty(yourName)) {
			updateResult += configService.updateConfig("yourName", yourName);
		}
		if (!StringUtils.isEmpty(yourEmail)) {
			updateResult += configService.updateConfig("yourEmail", yourEmail);
		}
		if (updateResult > 0) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
	
	@PostMapping("/footer")
	public RespBean footer(@RequestParam(value = "footerAbout", required = false) String footerAbout,
	                       @RequestParam(value = "footerICP", required = false) String footerICP,
	                       @RequestParam(value = "footerCopyRight", required = false) String footerCopyRight,
	                       @RequestParam(value = "footerPoweredBy", required = false) String footerPoweredBy,
	                       @RequestParam(value = "footerPoweredByURL", required = false) String footerPoweredByURL) {
		int updateResult = 0;
		if (!StringUtils.isEmpty(footerAbout)) {
			updateResult += configService.updateConfig("footerAbout", footerAbout);
		}
		if (!StringUtils.isEmpty(footerICP)) {
			updateResult += configService.updateConfig("footerICP", footerICP);
		}
		if (!StringUtils.isEmpty(footerCopyRight)) {
			updateResult += configService.updateConfig("footerCopyRight", footerCopyRight);
		}
		if (!StringUtils.isEmpty(footerPoweredBy)) {
			updateResult += configService.updateConfig("footerPoweredBy", footerPoweredBy);
		}
		if (!StringUtils.isEmpty(footerPoweredByURL)) {
			updateResult += configService.updateConfig("footerPoweredByURL", footerPoweredByURL);
		}
		if (updateResult > 0) {
			return new RespBean("success", "success");
		} else {
			return new RespBean("error", "failure");
		}
	}
}
