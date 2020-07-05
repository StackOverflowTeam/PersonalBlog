package team.stackoverflow.personalsite.service;

import java.util.Map;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-03 003 00:14:11
 * @Version 1.0
 */

public interface ConfigService {
	/**
	 * 修改配置项
	 *
	 * @param configName
	 * @param configValue
	 * @return
	 */
	int updateConfig(String configName, String configValue);
	
	/**
	 * 获取所有的配置项
	 *
	 * @return
	 */
	Map<String, String> getAllConfigs();
}
