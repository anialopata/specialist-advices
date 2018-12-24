package com.anialopata.registration.api;

import com.anialopata.registration.dto.CategoryDto;
import com.anialopata.registration.dto.CategoryListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by Ania on 2018-12-04.
 */
@Api(description = "Category Controller")
public interface ApiCategoryController {

    @ResponseStatus(OK)
    CategoryListDto getAllCategories();

    @ResponseStatus(OK)
    CategoryListDto getAllActiveCategories();

    @ResponseStatus(OK)
    @ApiOperation( value = "Get all categories")
    CategoryDto getCategoryById(@PathVariable Long id);

    @ResponseStatus(OK)
    CategoryDto createNewCategory(@RequestBody CategoryDto categoryDto);

    @ResponseStatus(OK)
    CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto);

    @ResponseStatus(OK)
    void deleteCategory(@PathVariable Long id);
}
