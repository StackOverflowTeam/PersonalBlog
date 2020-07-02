package team.stackoverflow.personalsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.stackoverflow.personalsite.pojo.Link;
import team.stackoverflow.personalsite.util.PageQueryUtil;

import java.util.List;

@Mapper
public interface LinkMapper {
    int deleteByPrimaryKey(Integer linkId);

    int insert(Link record);

    int insertSelective(Link record);

    Link selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKey(Link record);
    
    List<Link> findLinkList(PageQueryUtil pageUtil);
    
    int getTotalLinks(PageQueryUtil pageUtil);
    
    int deleteBatch(Integer[] ids);
}