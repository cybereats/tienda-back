package cybereats.fpmislata.com.tiendaback.persistence.repository;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.LogRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.LogJpaDao;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.LogJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.repository.mapper.LogMapper;

import java.util.List;
import java.util.Optional;

public class LogRepositoryImpl implements LogRepository {
    private final LogJpaDao logJpaDao;

    public LogRepositoryImpl(LogJpaDao logJpaDao) {
        this.logJpaDao = logJpaDao;
    }

    @Override
    public Page<LogDto> findAll(int page, int size) {
        List<LogDto> content = logJpaDao.findAll(page, size).stream()
                .map(logJpaEntity -> LogMapper.getInstance().logJpaEntityToLogDto(logJpaEntity))
                .toList();
        long totalElements = logJpaDao.count();
        return new Page<>(content, page, size, totalElements);
    }

    @Override
    public Optional<LogDto> findById(Long id) {
        return logJpaDao.findById(id)
                .map(logJpaEntity -> LogMapper.getInstance().logJpaEntityToLogDto(logJpaEntity));
    }

    @Override
    public LogDto save(LogDto logDto) {
        LogJpaEntity logJpaEntity = LogMapper.getInstance().logDtoToLogJpaEntity(logDto);

        if(logDto.id() == null) {
            return LogMapper.getInstance().logJpaEntityToLogDto(logJpaDao.insert(logJpaEntity));
        }

        return LogMapper.getInstance().logJpaEntityToLogDto(logJpaDao.update(logJpaEntity));
    }

    @Override
    public void deleteById(Long id) {
        logJpaDao.deleteById(id);
    }
}
