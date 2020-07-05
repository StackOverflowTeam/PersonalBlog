package team.stackoverflow.personalsite.pojo;

import java.util.Date;

public class Config {
	private String configName;
	
	private String configValue;
	
	private Date createTime;
	
	private String updateTime;
	
	public String getConfigName() {
		return configName;
	}
	
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	
	public String getConfigValue() {
		return configValue;
	}
	
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}