package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.LogRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.LogService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

import java.util.Optional;

public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Page<LogDto> findAll(int page, int size) {
        Page<LogDto> logDtoPage = logRepository.findAll(page, size);

        return new Page<>(
                logDtoPage.data(),
                logDtoPage.pageNumber(),
                logDtoPage.pageSize(),
                logDtoPage.totalElements());
    }

    @Override
    public LogDto getById(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }

    @Override
    public Optional<LogDto> findById(Long id) {
        return Optional.ofNullable(
                logRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Log not found")));
    }

    @Override
    public LogDto create(LogDto logDto) {
        Optional<LogDto> logDtoOptional = logRepository.findById(logDto.id());
        if (logDtoOptional.isPresent()) {
            throw new BusinessException("Log already exists");
        }
        return logRepository.save(logDto);
    }

    @Override
    public LogDto update(LogDto logDto) {
        Optional<LogDto> logDtoOptional = logRepository.findById(logDto.id());
        if (logDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Log not found");
        }
        return logRepository.save(logDto);
    }

    @Override
    public void deleteById(Long id) {
        Optional<LogDto> logDtoOptional = logRepository.findById(id);
        if (logDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Log not found");
        }
        logRepository.deleteById(id);
    }
}
