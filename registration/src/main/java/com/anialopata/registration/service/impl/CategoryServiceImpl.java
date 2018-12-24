package com.anialopata.registration.service.impl;

import com.anialopata.registration.dto.CategoryDto;
import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.exception.UserNotFoundException;
import com.anialopata.registration.mapper.CategoryMapper;
import com.anialopata.registration.mapper.SpecialistMapper;
import com.anialopata.registration.model.Category;
import com.anialopata.registration.repository.CategoryRepository;
import com.anialopata.registration.repository.SpecialistRepository;
import com.anialopata.registration.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ania on 2018-12-03.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final SpecialistRepository specialistRepository;
    private final SpecialistMapper specialistMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, SpecialistRepository specialistRepository, SpecialistMapper specialistMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.specialistRepository = specialistRepository;
        this.specialistMapper = specialistMapper;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(category -> {
                    CategoryDto categoryDto= categoryMapper.categoryToCategoryDto(category);
                    return categoryDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllActiveCategories() {
        return categoryRepository
                .findByIsActiveIsTrue()
                .stream()
                .map(category -> {
                    CategoryDto categoryDto= categoryMapper.categoryToCategoryDto(category);
                    return categoryDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryRepository
                .findById(id)
                .map(categoryMapper::categoryToCategoryDto)
                .orElseThrow(() -> new UserNotFoundException("Category doesn't exist"));
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        Category saved = categoryRepository.save(category);
        CategoryDto returned = categoryMapper.categoryToCategoryDto(saved);
        returned.setCategoryUrl("/api/v1/categories/" + saved.getId());
        return returned;
    }

    private CategoryDto saveAndReturnDto(Category category) {
        Category saved = categoryRepository.save(category);
        CategoryDto returnedDto = categoryMapper.categoryToCategoryDto(category);
        returnedDto.setCategoryUrl("/api/v1/categories/" + saved.getId());
        return returnedDto;
    }

    @Override
    public CategoryDto saveCategoryByDto(Long id, CategoryDto categoryDto) {
        Category savedCategory = categoryMapper.categoryDtoToCategory(categoryDto);
        savedCategory.setId(id);
        return saveAndReturnDto(savedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent())throw new UserNotFoundException("Category doesn't exist");
        categoryRepository.deleteById(id);
    }

    @Override
    public Set<SpecialistDto> findSpecialistsByCategory(Long id) {
        return specialistRepository.findByCategoryId(id).stream()
                .map(specialistMapper::specialistToSpecialistDto)
                .collect(Collectors.toSet());
    }

}
