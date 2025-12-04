package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.CategoryPCRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.CategoryPCService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;

import java.util.Optional;

public class CategoryPCServiceImpl implements CategoryPCService {
    private final CategoryPCRepository categoryPCRepository;

    public CategoryPCServiceImpl(CategoryPCRepository categoryPCRepository) {
        this.categoryPCRepository = categoryPCRepository;
    }

    @Override
    public Page<CategoryPCDto> getAll(int page, int size) {
        Page<CategoryPCDto> categoryPCDtoPage = categoryPCRepository.findAll(page, size);

        return new Page<>(
                categoryPCDtoPage.data(),
                categoryPCDtoPage.pageNumber(),
                categoryPCDtoPage.pageSize(),
                categoryPCDtoPage.totalElements()
        );
    }

    @Override
    public CategoryPCDto getById(Long id) {
        return categoryPCRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryPC not found"));
    }

    @Override
    public Optional<CategoryPCDto> findById(Long id) {
        return Optional.ofNullable(categoryPCRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found")));
    }

    @Override
    public CategoryPCDto create(CategoryPCDto categoryPCDto) {
        return categoryPCRepository.save(categoryPCDto);
    }

    @Override
    public CategoryPCDto update(CategoryPCDto categoryPCDto) {
        return categoryPCRepository.save(categoryPCDto);
    }

    @Override
    public void deleteById(Long id) {
        categoryPCRepository.deleteById(id);
    }
}
