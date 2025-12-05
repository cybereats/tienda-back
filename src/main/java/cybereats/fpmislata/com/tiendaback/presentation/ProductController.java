package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.ProductService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ProductDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.ProductMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.ProductRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAllProducts(@RequestParam(required = false, defaultValue = "1") int page,
                                                       @RequestParam(required = false, defaultValue = "10") int size) {
        Page<ProductDto> productDtoPage = productService.findAll(page, size);

        List<ProductResponse> productResponses = productDtoPage.data().stream()
                .map(productDto -> ProductMapper.getInstance().fromProductDtoToProductResponse(productDto))
                .toList();

        Page<ProductResponse> productPage = new Page<>(
                productResponses,
                productDtoPage.pageNumber(),
                productDtoPage.pageSize(),
                productDtoPage.totalElements()
        );

        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProductResponse> getProductBySlug(@PathVariable String slug) {
        ProductResponse productResponse = ProductMapper.getInstance().fromProductDtoToProductResponse(productService.getBySlug(slug));
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductDto productDto = ProductMapper.getInstance().fromProductRequestToProductDto(productRequest);
        DtoValidator.validate(productDto);
        ProductDto createdProduct = productService.create(productDto);
        return new ResponseEntity<>(ProductMapper.getInstance().fromProductDtoToProductResponse(createdProduct), HttpStatus.CREATED);
    }

    @PutMapping("/{slug}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("slug") String slug, @RequestBody ProductRequest productRequest) {
        if (!slug.equals(productRequest.slug())) {
            throw new IllegalArgumentException("SLUG in path and request body must match");
        }
        ProductDto productDto = ProductMapper.getInstance().fromProductRequestToProductDto(productRequest);
        DtoValidator.validate(productDto);
        ProductDto updatedProduct = productService.update(productDto);
        return new ResponseEntity<>(ProductMapper.getInstance().fromProductDtoToProductResponse(updatedProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("slug") String slug) {
        productService.deleteBySlug(slug);
        return ResponseEntity.noContent().build();
    }
}
