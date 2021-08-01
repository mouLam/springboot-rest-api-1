package com.moulam.springbootrestapi1.Services;

import com.moulam.springbootrestapi1.Exceptions.EtBadRequestException;
import com.moulam.springbootrestapi1.Exceptions.EtResourceNotFoundException;
import com.moulam.springbootrestapi1.Model.Category;
import com.moulam.springbootrestapi1.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl  implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> findAllCategories(Integer userId) {
        return categoryRepository.findAll(userId);
    }

    @Override
    public Category findCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        return categoryRepository.findById(userId, categoryId);
    }

    @Override
    public Category addCategory(Integer userId, String title, String description) throws EtBadRequestException {
        int categoryId = categoryRepository.create(userId, title, description);
        return categoryRepository.findById(userId, categoryId);
    }

    @Override
    public void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {

    }

    @Override
    public void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException {

    }
}
