package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.PCRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.PCService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.exception.BusinessException;
import cybereats.fpmislata.com.tiendaback.exception.ResourceNotFoundException;

import java.util.Optional;

public class PCServiceImpl implements PCService {
    private final PCRepository pcRepository;

    public PCServiceImpl(PCRepository pcRepository) {
        this.pcRepository = pcRepository;
    }

    @Override
    public Page<PCDto> findAll(int page, int size) {
        Page<PCDto> pcDtoPage = pcRepository.findAll(page, size);

        return new Page<>(
                pcDtoPage.data(),
                pcDtoPage.pageNumber(),
                pcDtoPage.pageSize(),
                pcDtoPage.totalElements());
    }

    @Override
    public PCDto getBySlug(String slug) {
        return pcRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("PC not found"));
    }

    @Override
    public Optional<PCDto> findBySlug(String slug) {
        return Optional.ofNullable(
                pcRepository.findBySlug(slug).orElseThrow(() -> new ResourceNotFoundException("PC not found")));
    }

    @Override
    public PCDto create(PCDto pcDto) {
        Optional<PCDto> pcDtoOptional = pcRepository.findBySlug(pcDto.slug());
        if (pcDtoOptional.isPresent()) {
            throw new BusinessException("PC already exists");
        }
        return pcRepository.save(pcDto);
    }

    @Override
    public PCDto update(PCDto pcDto) {
        Optional<PCDto> pcDtoOptional = pcRepository.findBySlug(pcDto.slug());
        if (pcDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("PC not found");
        }
        return pcRepository.save(pcDto);
    }

    @Override
    public void deleteBySlug(String slug) {
        Optional<PCDto> pcDtoOptional = pcRepository.findBySlug(slug);
        if (pcDtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("PC not found");
        }
        pcRepository.deleteBySlug(slug);
    }
}
