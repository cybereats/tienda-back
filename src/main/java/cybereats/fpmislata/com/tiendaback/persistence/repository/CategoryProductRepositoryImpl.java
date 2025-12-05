package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.CategoryProductRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryProductDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.CategoryProductJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryProductJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.CategoryProductMapper;

import java.util.List;
import java.util.Optional;

public class CategoryProductRepositoryImpl implements CategoryProductRepository {

    private final CategoryProductJpaDao categoryProductJpaDao;

    public CategoryProductRepositoryImpl(CategoryProductJpaDao categoryProductJpaDao) {
        this.categoryProductJpaDao = categoryProductJpaDao;
    }

    @Override
    public Page<CategoryProductDto> findAll(int page, int size) {
        List<CategoryProductDto> categoryProductDtoList = categoryProductJpaDao.findAll(page, size).stream()
                .map(CategoryProductMapper.getInstance()::fromCategoryProductJpaEntityToCategoryProductDto).toList();
        Long totalElements = categoryProductJpaDao.count();
        return new Page<>(categoryProductDtoList, page, size, totalElements);
    }

    @Override
    public Optional<CategoryProductDto> findById(Long id) {
        Optional<CategoryProductJpaEntity> categoryProductJpaEntityOptional = categoryProductJpaDao.findById(id);
        return categoryProductJpaEntityOptional
                .map(CategoryProductMapper.getInstance()::fromCategoryProductJpaEntityToCategoryProductDto);
    }

    @Override
    public Optional<CategoryProductDto> findBySlug(String slug) {
        Optional<CategoryProductJpaEntity> categoryProductJpaEntityOptional = categoryProductJpaDao.findBySlug(slug);
        return categoryProductJpaEntityOptional
                .map(CategoryProductMapper.getInstance()::fromCategoryProductJpaEntityToCategoryProductDto);
    }

    @Override
    public CategoryProductDto save(CategoryProductDto categoryProductDto) {
        if (categoryProductDto.id() == null) {
            CategoryProductJpaEntity categoryProductJpaEntity = CategoryProductMapper.getInstance()
                    .fromCategoryProductDtoToCategoryProductJpaEntity(categoryProductDto);
            return CategoryProductMapper.getInstance().fromCategoryProductJpaEntityToCategoryProductDto(
                    categoryProductJpaDao.insert(categoryProductJpaEntity));
        } else {
            CategoryProductJpaEntity categoryProductJpaEntity = CategoryProductMapper.getInstance()
                    .fromCategoryProductDtoToCategoryProductJpaEntity(categoryProductDto);
            return CategoryProductMapper.getInstance().fromCategoryProductJpaEntityToCategoryProductDto(
                    categoryProductJpaDao.update(categoryProductJpaEntity));
        }
    }

    @Override
    public void deleteBySlug(String slug) {
        categoryProductJpaDao.deleteBySlug(slug);
    }
}
