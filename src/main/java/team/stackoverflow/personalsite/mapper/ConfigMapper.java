package team.stackoverflow.personalsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.stackoverflow.personalsite.pojo.Config;

import java.util.List;

@Mapper
public interface ConfigMapper {
    int deleteByPrimaryKey(String configName);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(String configName);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
    
    List<Config> selectAll();
}