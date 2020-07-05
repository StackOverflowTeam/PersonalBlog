package team.stackoverflow.personalsite.service;

import team.stackoverflow.personalsite.pojo.BlogCategory;
import team.stackoverflow.personalsite.util.PageQueryUtil;
import team.stackoverflow.personalsite.util.PageResult;

import java.util.List;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 23:14:58
 * @Version 1.0
 */

public interface BlogCategoryService {
	/**
	 * 查询分类的分页数据
	 *
	 * @param pageUtil
	 * @return
	 */
	PageResult getBlogCategoryPage(PageQueryUtil pageUtil);
	
	int getTotalCategories();
	
	/**
	 * 添加分类数据
	 *
	 * @param categoryName
	 * @param categoryIcon
	 * @return
	 */
	Boolean saveCategory(String categoryName, String categoryIcon);
	
	Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);
	
	Boolean deleteBatch(Integer[] ids);
	
	List<BlogCategory> getAllCategories();
}
