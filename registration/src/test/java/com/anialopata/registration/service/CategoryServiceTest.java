package com.anialopata.registration.service;

import com.anialopata.registration.dto.CategoryDto;
import com.anialopata.registration.mapper.CategoryMapper;
import com.anialopata.registration.mapper.SpecialistMapper;
import com.anialopata.registration.model.Category;
import com.anialopata.registration.repository.CategoryRepository;
import com.anialopata.registration.repository.SpecialistRepository;
import com.anialopata.registration.service.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Ania on 2018-12-13.
 */
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    private CategoryService categoryService;
    private SpecialistMapper specialistMapper = SpecialistMapper.INSTANCE;
    private SpecialistRepository specialistRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper, specialistRepository, specialistMapper);
    }

    @Test
    public void getAllCategoriesTest() throws Exception {

        Category category1= new Category();
        category1.setId(1l);
        category1.setName("DIETETYCZNA");
        category1.setDescription("kategoria abcbsbdbdbdbdbd");

        Category category2= new Category();
        category2 = new Category();
        category2.setId(2l);
        category2.setName("FARMACEUTYCZNA");
        category2.setDescription("kategoria");

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();

        assertEquals(2, categoryDtoList.size());

    }

    @Test
    public void getAllActiveCategoriesTest() throws Exception {

        Category category1= new Category();
        category1.setId(1l);
        category1.setName("DIETETYCZNA");
        category1.setDescription("kategoria abcbsbdbdbdbdbd");
        category1.setActive(true);

        Category category2= new Category();
        category2.setId(2l);
        category2.setName("FARMACEUTYCZNA");
        category2.setDescription("kategoria");
        category2.setActive(false);

        when(categoryRepository.findByIsActiveIsTrue()).thenReturn(Arrays.asList(category1));

        List<CategoryDto> categoryDtoList = categoryService.getAllActiveCategories();

        assertEquals(1, categoryDtoList.size());

    }
    @Test
    public void createNewCategoryTest() throws Exception {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("KOSMETOLOGICZNA");

        Category savedCategory = new Category();
        savedCategory.setName(categoryDto.getName());
        savedCategory.setId(1L);

        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);

        CategoryDto savedDto = categoryService.saveCategoryByDto(1L, categoryDto);

        assertEquals(categoryDto.getName(), savedDto.getName());

    }

    @Test
    public void deleteCategoryByIdTest() throws Exception {

        Long id = 1L;
        categoryRepository.deleteById(id);

        verify(categoryRepository, times(1)).deleteById(anyLong());
    }

}
