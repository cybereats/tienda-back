package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.CategoryProductService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.CategoryProductMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryProductRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CategoryProductResponse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import cybereats.fpmislata.com.tiendaback.security.AllowedRoles;
import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;

@RestController
@RequestMapping("/api/category-products")
public class CategoryProductController {
    private final CategoryProductService categoryProductService;

    public CategoryProductController(CategoryProductService categoryProductService) {
        this.categoryProductService = categoryProductService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryProductResponse>> findAllCategoryProducts(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Page<CategoryProductDto> categoryProductDtoPage = categoryProductService.findAll(page, size);

        List<CategoryProductResponse> categoryProductResponses = categoryProductDtoPage.data().stream()
                .map(categoryProductDto -> CategoryProductMapper.getInstance()
                        .fromCategoryProductDtoToCategoryProductResponse(categoryProductDto))
                .toList();

        Page<CategoryProductResponse> categoryProductPage = new Page<>(
                categoryProductResponses,
                categoryProductDtoPage.pageNumber(),
                categoryProductDtoPage.pageSize(),
                categoryProductDtoPage.totalElements());

        return new ResponseEntity<>(categoryProductPage, HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<CategoryProductResponse> getCategoryProductBySlug(@PathVariable String slug) {
        CategoryProductResponse categoryProductResponse = CategoryProductMapper.getInstance()
                .fromCategoryProductDtoToCategoryProductResponse(categoryProductService.getBySlug(slug));
        return new ResponseEntity<>(categoryProductResponse, HttpStatus.OK);
    }

    @PostMapping
    @AllowedRoles(UserRole.ADMIN)
    public ResponseEntity<CategoryProductResponse> createCategoryProduct(
            @RequestBody CategoryProductRequest categoryProductRequest) {
        CategoryProductDto categoryProductDto = CategoryProductMapper.getInstance()
                .fromCategoryProductRequestToCategoryProductDto(categoryProductRequest);
        CategoryProductDto createdCategoryProduct = categoryProductService.insert(categoryProductDto);
        return new ResponseEntity<>(
                CategoryProductMapper.getInstance().fromCategoryProductDtoToCategoryProductResponse(createdCategoryProduct),
                HttpStatus.CREATED);
    }

    @PutMapping("/{slug}")
    @AllowedRoles(UserRole.ADMIN)
    public ResponseEntity<CategoryProductResponse> updateCategoryProduct(@PathVariable("slug") String slug,
            @RequestBody CategoryProductRequest categoryProductRequest) {
        if (!slug.equals(categoryProductRequest.slug())) {
            throw new IllegalArgumentException("SLUG in path and request body must match");
        }
        CategoryProductDto categoryProductDto = CategoryProductMapper.getInstance()
                .fromCategoryProductRequestToCategoryProductDto(categoryProductRequest);
        CategoryProductDto updatedCategoryProduct = categoryProductService.update(categoryProductDto);
        return new ResponseEntity<>(
                CategoryProductMapper.getInstance().fromCategoryProductDtoToCategoryProductResponse(updatedCategoryProduct),
                HttpStatus.OK);
    }

    @DeleteMapping("/{slug}")
    @AllowedRoles(UserRole.ADMIN)
    public ResponseEntity<Void> deleteCategoryProduct(@PathVariable("slug") String slug) {
        categoryProductService.deleteBySlug(slug);
        return ResponseEntity.noContent().build();
    }
}
