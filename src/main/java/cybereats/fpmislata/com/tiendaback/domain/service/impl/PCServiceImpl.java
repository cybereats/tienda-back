package cybereats.fpmislata.com.tiendaback.domain.service.impl;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.repository.PCRepository;
import cybereats.fpmislata.com.tiendaback.domain.service.PCService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;

import java.util.Optional;

public class PCServiceImpl implements PCService {
    private final PCRepository pcRepository;

    public PCServiceImpl(PCRepository pcRepository) {
        this.pcRepository = pcRepository;
    }

    @Override
    public Page<PCDto> getAll(int page, int size) {
        Page<PCDto> pcDtoPage = pcRepository.findAll(page, size);

        return new Page<>(
                pcDtoPage.data(),
                pcDtoPage.pageNumber(),
                pcDtoPage.pageSize(),
                pcDtoPage.totalElements()
        );
    }

    @Override
    public PCDto getBySlug(String slug) {
        return pcRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("PC not found"));
    }

    @Override
    public Optional<PCDto> findBySlug(String slug) {
        return Optional.ofNullable(pcRepository.findBySlug(slug).orElseThrow(() -> new RuntimeException("PC not found")));
    }

    @Override
    public PCDto create(PCDto pcDto) {
        return pcRepository.save(pcDto);
    }

    @Override
    public PCDto update(PCDto pcDto) {
        return pcRepository.save(pcDto);
    }

    @Override
    public void deleteBySlug(String slug) {
        pcRepository.deleteBySlug(slug);
    }
}
