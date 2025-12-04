package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.PCRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.PCJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.PCMapper;

import java.util.List;
import java.util.Optional;

public class PCRepositoryImpl implements PCRepository {
    private final PCJpaDao pcJpaDao;

    public PCRepositoryImpl(PCJpaDao pcJpaDao) {
        this.pcJpaDao = pcJpaDao;
    }

    @Override
    public Page<PCDto> findAll(int page, int size) {
        List<PCDto> content = pcJpaDao.findAll(page, size).stream()
                .map(pcJpaEntity -> PCMapper.getInstance().pcJpaEntityToPCDto(pcJpaEntity))
                .toList();
        long totalElements = pcJpaDao.count();
        return new Page<>(content, page, size, totalElements);
    }

    @Override
    public Optional<PCDto> findById(Long id) {
        return pcJpaDao.findById(id)
                .map(pcJpaEntity -> PCMapper.getInstance().pcJpaEntityToPCDto(pcJpaEntity));
    }

    @Override
    public PCDto save(PCDto pcDto) {
        PCJpaEntity pcJpaEntity = PCMapper.getInstance().pcDtoToPCJpaEntity(pcDto);

        if(pcDto.id() == null) {
            return PCMapper.getInstance().pcJpaEntityToPCDto(pcJpaDao.insert(pcJpaEntity));
        }

        return PCMapper.getInstance().pcJpaEntityToPCDto(pcJpaDao.update(pcJpaEntity));
    }

    @Override
    public void deleteById(Long id) {
        pcJpaDao.deleteById(id);
    }

    @Override
    public void deleteBySlug(String slug) {
        pcJpaDao.deleteBySlug(slug);
    }

    @Override
    public Optional<PCDto> findBySlug(String slug) {
        return pcJpaDao.findBySlug(slug)
                .map(pcJpaEntity -> PCMapper.getInstance().pcJpaEntityToPCDto(pcJpaEntity));
    }
}
