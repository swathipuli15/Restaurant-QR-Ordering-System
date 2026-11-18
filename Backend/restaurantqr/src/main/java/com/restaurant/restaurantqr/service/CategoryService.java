package com.restaurant.restaurantqr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.restaurantqr.entity.Category;
import com.restaurant.restaurantqr.repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	public CategoryService(CategoryRepository categoryRepository ) {
		this.categoryRepository =categoryRepository;
	}

	//add category
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	//get all categories
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}

	//get category by Id
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).orElse(null);

	}

	//Update category
	public Category updateCategory(Long id,Category category) {
		Category existingCategory=categoryRepository.findById(id).orElse(null);
		if(existingCategory!=null) {
			existingCategory.setCategoryName(category.getCategoryName());
		return categoryRepository.save(existingCategory);
		}
		return null;
	}
	//Delete category
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);

	}

}
