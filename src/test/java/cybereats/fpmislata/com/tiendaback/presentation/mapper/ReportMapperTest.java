package cybereats.fpmislata.com.tiendaback.presentation.mapper;

import cybereats.fpmislata.com.tiendaback.domain.model.UserRole;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.CategoryPCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.UserDto;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.ReportRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.ReportResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ReportMapperTest {

        @Test
        @DisplayName("Debería mapear de ReportRequest a ReportDto")
        void shouldMapRequestToDto() {
                ReportRequest request = new ReportRequest(1L, 10L, 20L, "Description", "Subject", "Status",
                                "2025-01-15 10:00:00", 1);

                ReportDto dto = ReportMapper.fromReportRequestToReportDto(request);

                assertNotNull(dto);
                assertEquals(request.id(), dto.id());
                assertEquals(request.description(), dto.description());
                assertEquals(request.subject(), dto.subject());
                assertEquals(request.status(), dto.status());
                assertEquals(request.createdAt(), dto.createdAt());
                assertEquals(request.priority(), dto.priority());
                assertEquals(request.userId(), dto.user().id());
                assertEquals(request.pcId(), dto.pc().id());
        }

        @Test
        @DisplayName("Debería mapear de ReportDto a ReportResponse")
        void shouldMapDtoToResponse() {
                UserDto userDto = new UserDto(1L, "Name", "Surname", "email@test.com", "1990-01-01", "user", "pass",
                                UserRole.CLIENT);
                CategoryPCDto categoryPCDto = new CategoryPCDto(1L, "Category", "category", new BigDecimal("10.00"));
                PCDto pcDto = new PCDto(1L, "PC 1", "pc-1", 100, "Specs", "2020-01-01", "image.png",
                                cybereats.fpmislata.com.tiendaback.domain.model.PCStatus.AVAILABLE, categoryPCDto);
                ReportDto dto = new ReportDto(1L, 1, "Description", "Subject", "Status", "2025-01-15 10:00:00", userDto,
                                pcDto);

                ReportResponse response = ReportMapper.fromReportDtoToReportResponse(dto);

                assertNotNull(response);
                assertEquals(dto.id(), response.id());
                assertEquals(dto.priority(), response.priority());
                assertEquals(dto.description(), response.description());
                assertEquals(dto.subject(), response.subject());
                assertEquals(dto.status(), response.status());
                assertEquals(dto.createdAt(), response.createdAt());
                assertEquals(dto.user().id(), response.user().id());
                assertEquals(dto.pc().id(), response.pc().id());
        }
}
