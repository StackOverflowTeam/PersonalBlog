package team.stackoverflow.personalsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.stackoverflow.personalsite.mapper.BlogCategoryMapper;
import team.stackoverflow.personalsite.mapper.BlogMapper;
import team.stackoverflow.personalsite.pojo.BlogCategory;
import team.stackoverflow.personalsite.service.BlogCategoryService;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 23:17:43
 * @Version 1.0
 */

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {
	@Resource
	BlogCategoryMapper blogCategoryMapper;
	@Resource
	BlogMapper blogMapper;
	
	@Override
	public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
		List<BlogCategory> categoryList = blogCategoryMapper.findCategoryList(pageUtil);
		int total = blogCategoryMapper.getTotalCategories(pageUtil);
		PageResult pageResult = new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
		return pageResult;
	}
	
	@Override
	public int getTotalCategories() {
		return blogCategoryMapper.getTotalCategories(null);
	}
	
	@Override
	public Boolean saveCategory(String categoryName, String categoryIcon) {
		BlogCategory temp = blogCategoryMapper.selectByCategoryName(categoryName);
		if (temp == null) {
			BlogCategory blogCategory = new BlogCategory();
			blogCategory.setCategoryName(categoryName);
			blogCategory.setCategoryIcon(categoryIcon);
			return blogCategoryMapper.insertSelective(blogCategory) > 0;
		}
		return false;
	}
	
	@Override
	@Transactional
	public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
		BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(categoryId);
		if (blogCategory != null) {
			blogCategory.setCategoryIcon(categoryIcon);
			blogCategory.setCategoryName(categoryName);
			//修改分类实体
			blogMapper.updateBlogCategorys(categoryName, blogCategory.getCategoryId(), new Integer[]{categoryId});
			return blogCategoryMapper.updateByPrimaryKeySelective(blogCategory) > 0;
		}
		return false;
	}
	
	@Override
	@Transactional
	public Boolean deleteBatch(Integer[] ids) {
		if (ids.length < 1) {
			return false;
		}
		//修改tb_blog表
		blogMapper.updateBlogCategorys("默认分类", 0, ids);
		//删除分类数据
		return blogCategoryMapper.deleteBatch(ids) > 0;
	}
	
	@Override
	public List<BlogCategory> getAllCategories() {
		return blogCategoryMapper.findCategoryList(null);
	}
}
