package com.anialopata.registration.mapper;

import com.anialopata.registration.dto.CategoryDto;
import com.anialopata.registration.model.Category;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ania on 2018-12-13.
 */
public class CategoryMapperTest {

    public static final String NAME ="DIETETYCZNA";
    public static final String DESCRIPTION = "porada shshsh aaaaaa";

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        Category category= new Category();
        category.setName(NAME);
        category.setDescription(DESCRIPTION);

        CategoryDto categoryDTO = categoryMapper.categoryToCategoryDto(category);

        assertEquals(NAME, categoryDTO.getName());
        assertEquals(DESCRIPTION, categoryDTO.getDescription());
    }

    @Test
    public void categoryDTOToCategory() throws Exception {

        CategoryDto categoryDTO = new CategoryDto();
        categoryDTO.setName(NAME);
        categoryDTO.setDescription(DESCRIPTION);

        Category category = categoryMapper.categoryDtoToCategory(categoryDTO);

        assertEquals(NAME, category.getName());
        assertEquals(DESCRIPTION, category.getDescription());

    }
}