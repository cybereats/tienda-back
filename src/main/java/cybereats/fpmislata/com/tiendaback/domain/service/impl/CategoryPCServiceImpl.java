package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.CategoryPCRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.CategoryPCService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Optional;

public class CategoryPCServiceImpl implements CategoryPCService {
    private final CategoryPCRepository categoryPCRepository;

    public CategoryPCServiceImpl(CategoryPCRepository categoryPCRepository) {
        this.categoryPCRepository = categoryPCRepository;
    }

    @Override
    public Page<CategoryPCDto> findAll(int page, int size) {
        Page<CategoryPCDto> categoryPCDtoPage = categoryPCRepository.findAll(page, size);

        return new Page<>(
                categoryPCDtoPage.data(),
                categoryPCDtoPage.pageNumber(),
                categoryPCDtoPage.pageSize(),
                categoryPCDtoPage.totalElements());
    }

    @Override
    public CategoryPCDto getById(Long id) {
        return categoryPCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryPC not found"));
    }

    @Override
    public Optional<CategoryPCDto> findById(Long id) {
        return Optional.ofNullable(categoryPCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found")));
    }

    @Override
    @Transactional
    public CategoryPCDto create(CategoryPCDto categoryPCDto) {
        Optional<CategoryPCDto> categoryPCDtoOptional = categoryPCRepository.findById(categoryPCDto.id());
        if (categoryPCDtoOptional.isPresent()) {
            throw new BusinessException("CategoryPC already exists");
        }
        return categoryPCRepository.save(categoryPCDto);
    }

    @Override
    public CategoryPCDto getBySlug(String slug) {
        return categoryPCRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryPC not found"));
    }

    @Override
    public Optional<CategoryPCDto> findBySlug(String slug) {
        return categoryPCRepository.findBySlug(slug);
    }

    @Override
    @Transactional
    public CategoryPCDto update(CategoryPCDto categoryPCDto) {
        Optional<CategoryPCDto> categoryPCDtoOptional = categoryPCRepository.findById(categoryPCDto.id());
        if (categoryPCDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("CategoryPC not found");
        }
        return categoryPCRepository.save(categoryPCDto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<CategoryPCDto> categoryPCDtoOptional = categoryPCRepository.findById(id);
        if (categoryPCDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("CategoryPC not found");
        }
        categoryPCRepository.deleteById(id);
    }
}
