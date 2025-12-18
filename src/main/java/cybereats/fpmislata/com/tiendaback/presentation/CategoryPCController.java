package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.CategoryPCService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.CategoryPCMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.CategoryPCRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.CategoryPCResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories-pc")
public class CategoryPCController {
        private final CategoryPCService categoryPCService;

        public CategoryPCController(CategoryPCService categoryPCService) {
                this.categoryPCService = categoryPCService;
        }

        @GetMapping
        public ResponseEntity<Page<CategoryPCResponse>> findAllCategoryPCs(
                        @RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int size) {
                Page<CategoryPCDto> categoryPCDtoPage = categoryPCService.findAll(page, size);

                List<CategoryPCResponse> categoryPCResponses = categoryPCDtoPage.data().stream()
                                .map(categoryPCDto -> CategoryPCMapper.getInstance()
                                                .fromCategoryPCDtoToCategoryPCResponse(categoryPCDto))
                                .toList();

                Page<CategoryPCResponse> categoryPCPage = new Page<>(
                                categoryPCResponses,
                                categoryPCDtoPage.pageNumber(),
                                categoryPCDtoPage.pageSize(),
                                categoryPCDtoPage.totalElements());

                return new ResponseEntity<>(categoryPCPage, HttpStatus.OK);
        }

        @GetMapping("/{id:\\d+}")
        public ResponseEntity<CategoryPCResponse> getCategoryPCById(@PathVariable Long id) {
                CategoryPCResponse categoryPCResponse = CategoryPCMapper.getInstance()
                                .fromCategoryPCDtoToCategoryPCResponse(categoryPCService.getById(id));
                return new ResponseEntity<>(categoryPCResponse, HttpStatus.OK);
        }

        @GetMapping("/{slug:[a-zA-Z][a-zA-Z0-9-]*}")
        public ResponseEntity<CategoryPCResponse> getCategoryPCBySlug(@PathVariable String slug) {
                CategoryPCResponse categoryPCResponse = CategoryPCMapper.getInstance()
                                .fromCategoryPCDtoToCategoryPCResponse(categoryPCService.getBySlug(slug));
                return new ResponseEntity<>(categoryPCResponse, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<CategoryPCResponse> createCategoryPC(@RequestBody CategoryPCRequest categoryPCRequest) {
                CategoryPCDto categoryPCDto = CategoryPCMapper.getInstance()
                                .fromCategoryPCRequestToCategoryPCDto(categoryPCRequest);
                DtoValidator.validate(categoryPCDto);
                CategoryPCDto createdCategoryPC = categoryPCService.create(categoryPCDto);
                return new ResponseEntity<>(
                                CategoryPCMapper.getInstance().fromCategoryPCDtoToCategoryPCResponse(createdCategoryPC),
                                HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<CategoryPCResponse> updateCategoryPC(@PathVariable("id") Long id,
                        @RequestBody CategoryPCRequest categoryPCRequest) {
                if (!id.equals(categoryPCRequest.id())) {
                        throw new IllegalArgumentException("ID in path and request body must match");
                }
                CategoryPCDto categoryPCDto = CategoryPCMapper.getInstance()
                                .fromCategoryPCRequestToCategoryPCDto(categoryPCRequest);
                DtoValidator.validate(categoryPCDto);
                CategoryPCDto updatedCategoryPC = categoryPCService.update(categoryPCDto);
                return new ResponseEntity<>(
                                CategoryPCMapper.getInstance().fromCategoryPCDtoToCategoryPCResponse(updatedCategoryPC),
                                HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCategoryPC(@PathVariable("id") Long id) {
                categoryPCService.deleteById(id);
                return ResponseEntity.noContent().build();
        }
}
