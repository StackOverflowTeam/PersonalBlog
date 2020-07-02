package team.stackoverflow.personalsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.stackoverflow.personalsite.pojo.AdminUser;

@Mapper
public interface AdminUserMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    AdminUser selectByAdminUser(AdminUser adminUser);
}