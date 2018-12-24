package com.anialopata.registration.service;

import com.anialopata.registration.dto.CategoryDto;
import com.anialopata.registration.dto.SpecialistDto;

import java.util.List;
import java.util.Set;

/**
 * Created by Ania on 2018-12-03.
 */
public interface CategoryService {

    /**
     *
     * @return list of categories
     */
    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllActiveCategories();

    /**
     * @param  id - ID number of the category
     * @return category instance with specified ID number
     */
    CategoryDto getCategoryById(Long id);

    /**
     *
     * @param categoryDto - saved category
     * @return created CategoryDto instance
     */
    CategoryDto createCategory(CategoryDto categoryDto);

    /**
     *
     * @param id - category ID number
     * @param categoryDto - CategoryDto which will be converted into Category type instance
     * @return CategoryDto instance
     */
    CategoryDto saveCategoryByDto(Long id, CategoryDto categoryDto);

    /**
     *
     * @param id - ID number of category to delete
     */
    void deleteCategory(Long id);

    Set<SpecialistDto> findSpecialistsByCategory(Long id);

}
