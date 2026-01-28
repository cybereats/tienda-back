package cybereats.fpmislata.com.tiendaback.persistence.repository.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.CategoryPCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;
import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.UserJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ReportMapperTest {

        @Test
        @DisplayName("Debería mapear de ReportJpaEntity a ReportDto")
        void shouldMapEntityToDto() {
                UserJpaEntity userEntity = new UserJpaEntity(1L, "Name", "Surname", "email@test.com", "1990-01-01",
                                "user",
                                "pass", UserRole.CLIENT);
                CategoryPCJpaEntity categoryEntity = new CategoryPCJpaEntity(1L, "Category", "category",
                                new BigDecimal("10.00"));
                PCJpaEntity pcEntity = new PCJpaEntity(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png",
                                "AVAILABLE",
                                categoryEntity);
                ReportJpaEntity entity = new ReportJpaEntity(1L, "High", "Description", "Subject", "Status",
                                "2025-01-15 10:00:00", userEntity, pcEntity);

                ReportDto dto = ReportMapper.fromReportJpaEntitytoReportDto(entity);

                assertNotNull(dto);
                assertEquals(entity.getId(), dto.id());
                assertEquals(entity.getPriority(), dto.priority());
                assertEquals(entity.getDescription(), dto.description());
                assertEquals(entity.getSubject(), dto.subject());
                assertEquals(entity.getStatus(), dto.status());
                assertEquals(entity.getCreatedAt(), dto.createdAt());
                assertEquals(entity.getUser().getId(), dto.user().id());
                assertEquals(entity.getPc().getId(), dto.pc().id());
        }

        @Test
        @DisplayName("Debería mapear de ReportDto a ReportJpaEntity")
        void shouldMapDtoToEntity() {
                UserDto userDto = new UserDto(1L, "Name", "Surname", "email@test.com", "1990-01-01", "user", "pass",
                                UserRole.CLIENT);
                CategoryPCDto categoryDto = new CategoryPCDto(1L, "Category", "category", new BigDecimal("10.00"));
                PCDto pcDto = new PCDto(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png",
                                cybereats.fpmislata.com.tiendaback.domain.model.PCStatus.AVAILABLE, categoryDto);
                ReportDto dto = new ReportDto(1L, "High", "Description", "Subject", "Status", "2025-01-15 10:00:00",
                                userDto,
                                pcDto);

                ReportJpaEntity entity = ReportMapper.fromReportDtotoReportJpaEntity(dto);

                assertNotNull(entity);
                assertEquals(dto.id(), entity.getId());
                assertEquals(dto.priority(), entity.getPriority());
                assertEquals(dto.description(), entity.getDescription());
                assertEquals(dto.subject(), entity.getSubject());
                assertEquals(dto.status(), entity.getStatus());
                assertEquals(dto.createdAt(), entity.getCreatedAt());
                assertEquals(dto.user().id(), entity.getUser().getId());
                assertEquals(dto.pc().id(), entity.getPc().getId());
        }
}
