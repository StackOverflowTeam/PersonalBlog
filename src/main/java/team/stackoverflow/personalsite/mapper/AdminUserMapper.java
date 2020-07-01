package team.stackoverflow.personalsite.mapper;

import team.stackoverflow.personalsite.pojo.AdminUser;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}