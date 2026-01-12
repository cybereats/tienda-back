package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.repository.CategoryProductRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.CategoryProductService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class CategoryProductServiceImpl implements CategoryProductService {
    private final CategoryProductRepository categoryProductRepository;

    public CategoryProductServiceImpl(CategoryProductRepository categoryProductRepository) {
        this.categoryProductRepository = categoryProductRepository;
    }

    @Override
    public List<CategoryProductDto> findAll() {
        return categoryProductRepository.findAll();
    }

    @Override
    public Page<CategoryProductDto> findAll(int page, int size) {
        return categoryProductRepository.findAll(page, size);
    }

    @Override
    public CategoryProductDto getById(Long id) {
        return categoryProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryProduct not found"));
    }

    @Override
    public Optional<CategoryProductDto> findById(Long id) {
        return categoryProductRepository.findById(id);
    }

    @Override
    public CategoryProductDto getBySlug(String slug) {
        return categoryProductRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryProduct not found"));
    }

    @Override
    public Optional<CategoryProductDto> findBySlug(String slug) {
        return categoryProductRepository.findBySlug(slug);
    }

    @Override
    @Transactional
    public CategoryProductDto insert(CategoryProductDto categoryProductDto) {
        if (categoryProductRepository.findById(categoryProductDto.id()).isPresent()) {
            throw new BusinessException("CategoryProduct already exists");
        }
        return categoryProductRepository.save(categoryProductDto);
    }

    @Override
    @Transactional
    public CategoryProductDto update(CategoryProductDto categoryProductDto) {
        if (!categoryProductRepository.findById(categoryProductDto.id()).isPresent()) {
            throw new ResourceNotFoundException("CategoryProduct not found");
        }
        return categoryProductRepository.save(categoryProductDto);
    }

    @Override
    @Transactional
    public void deleteBySlug(String slug) {
        if (!categoryProductRepository.findBySlug(slug).isPresent()) {
            throw new ResourceNotFoundException("CategoryProduct not found");
        }
        categoryProductRepository.deleteBySlug(slug);
    }
}
