package com.anialopata.registration.api.impl;

import com.anialopata.registration.api.ApiCategoryController;
import com.anialopata.registration.dto.CategoryDto;
import com.anialopata.registration.dto.CategoryListDto;
import com.anialopata.registration.dto.SpecialistDto;
import com.anialopata.registration.service.CategoryService;
import com.anialopata.registration.service.SpecialistService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Ania on 2018-12-03.
 */

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController implements ApiCategoryController {

    private final CategoryService categoryService;
    private final SpecialistService specialistService;

    public CategoryController(CategoryService categoryService, SpecialistService specialistService) {
        this.categoryService = categoryService;
        this.specialistService = specialistService;
    }

    @ApiOperation(value = "Get all categories")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDto getAllCategories() {
        return new CategoryListDto(categoryService.getAllCategories());
    }

    @ApiOperation(value = "Get only active categories")
    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDto getAllActiveCategories() {
        return new CategoryListDto(categoryService.getAllActiveCategories());
    }

    @ApiOperation(value = "Get category by ID value")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @ApiOperation(value = "Create new category")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createNewCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }

    @ApiOperation(value = "Update category with specify ID value")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        return categoryService.saveCategoryByDto(id, categoryDto);
    }

    @ApiOperation(value = "Deactivate category")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

    @ApiOperation(value = "Add specialist to category")
    @PostMapping("/{categoryId}/specialists")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSpecialistToCategory(@PathVariable Long categoryId, @RequestBody SpecialistDto specialistDto){
        specialistService.addSpecialistToCategory(categoryId, specialistDto);
    }

    @ApiOperation(value = "List all specialists for category")
    @GetMapping("/{categoryId}/specialists")
    public Set<SpecialistDto> findCategorySpecialists(@PathVariable Long categoryId) {
        return categoryService.findSpecialistsByCategory(categoryId);
    }
}
