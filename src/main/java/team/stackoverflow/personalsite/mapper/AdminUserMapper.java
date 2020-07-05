package team.stackoverflow.personalsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.stackoverflow.personalsite.pojo.AdminUser;

@Mapper
public interface AdminUserMapper {
	int deleteByPrimaryKey(Integer adminUserId);
	
	int insert(AdminUser record);
	
	int insertSelective(AdminUser record);
	
	AdminUser selectByPrimaryKey(Integer adminUserId);
	
	int updateByPrimaryKeySelective(AdminUser record);
	
	int updateByPrimaryKey(AdminUser record);
	
	/**
	 * 登陆方法
	 *
	 * @param userName
	 * @param password
	 * @return
	 */
	AdminUser login(@Param("userName") String userName, @Param("password") String password);
}