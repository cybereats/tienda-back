package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.CategoryPCRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CategoryPCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.CategoryPCMapper;

import java.util.List;
import java.util.Optional;

public class CategoryPCRepositoryImpl implements CategoryPCRepository {
    private final CategoryPCJpaDao categoryPCJpaDao;

    public CategoryPCRepositoryImpl(CategoryPCJpaDao categoryPCJpaDao) {
        this.categoryPCJpaDao = categoryPCJpaDao;
    }

    @Override
    public List<CategoryPCDto> findAll() {
        return categoryPCJpaDao.findAll().stream()
                .map(categoryPCJpaEntity -> CategoryPCMapper.getInstance()
                        .fromCategoryPCJpaEntityToCategoryPCDto(categoryPCJpaEntity))
                .toList();
    }

    @Override
    public Page<CategoryPCDto> findAll(int page, int size) {
        List<CategoryPCDto> content = categoryPCJpaDao.findAll(page, size).stream()
                .map(categoryPCJpaEntity -> CategoryPCMapper.getInstance()
                        .fromCategoryPCJpaEntityToCategoryPCDto(categoryPCJpaEntity))
                .toList();
        long totalElements = categoryPCJpaDao.count();
        return new Page<>(content, page, size, totalElements);
    }

    @Override
    public Optional<CategoryPCDto> findById(Long id) {
        return categoryPCJpaDao.findById(id)
                .map(categoryPCJpaEntity -> CategoryPCMapper.getInstance()
                        .fromCategoryPCJpaEntityToCategoryPCDto(categoryPCJpaEntity));
    }

    @Override
    public Optional<CategoryPCDto> findBySlug(String slug) {
        return categoryPCJpaDao.findBySlug(slug)
                .map(categoryPCJpaEntity -> CategoryPCMapper.getInstance()
                        .fromCategoryPCJpaEntityToCategoryPCDto(categoryPCJpaEntity));
    }

    @Override
    public CategoryPCDto save(CategoryPCDto categoryPCDto) {
        CategoryPCJpaEntity categoryPCJpaEntity = CategoryPCMapper.getInstance()
                .fromCategoryPCDtoToCategoryPCJpaEntity(categoryPCDto);

        if (categoryPCDto.id() == null) {
            return CategoryPCMapper.getInstance()
                    .fromCategoryPCJpaEntityToCategoryPCDto(categoryPCJpaDao.insert(categoryPCJpaEntity));
        }

        return CategoryPCMapper.getInstance()
                .fromCategoryPCJpaEntityToCategoryPCDto(categoryPCJpaDao.update(categoryPCJpaEntity));
    }

    @Override
    public void deleteById(Long id) {
        categoryPCJpaDao.deleteById(id);
    }
}
