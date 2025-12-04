package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;

public class PCMapper {
    private static PCMapper INSTANCE;

    private PCMapper() {}

    public static PCMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PCMapper();
        }
        return INSTANCE;
    }

    public PCDto pcJpaEntityToPCDto(PCJpaEntity pcJpaEntity) {
        if (pcJpaEntity == null) {
            return null;
        }

        return new PCDto(
                pcJpaEntity.getId(),
                pcJpaEntity.getLabel(),
                pcJpaEntity.getSlug(),
                pcJpaEntity.getRuntime(),
                pcJpaEntity.getSpecs(),
                pcJpaEntity.getWorking_since()
        );
    }

    public PCJpaEntity pcDtoToPCJpaEntity(PCDto pcDto) {
        if (pcDto == null) {
            return null;
        }

        return new PCJpaEntity(
                pcDto.id(),
                pcDto.label(),
                pcDto.slug(),
                pcDto.runtime(),
                pcDto.specs(),
                pcDto.working_since()
        );
    }
}
