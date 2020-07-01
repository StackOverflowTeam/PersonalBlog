package team.stackoverflow.personalsite.mapper;

import team.stackoverflow.personalsite.pojo.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(String configName);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(String configName);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}