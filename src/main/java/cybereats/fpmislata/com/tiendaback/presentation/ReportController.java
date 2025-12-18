package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.ReportService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.ReportDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.ReportMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.ReportRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.ReportResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportResponse> createReport(@RequestBody ReportRequest reportRequest) {
        ReportDto reportDto = ReportMapper.fromReportRequestToReportDto(reportRequest);
        DtoValidator.validate(reportDto);
        ReportDto createdReportDto = reportService.insert(reportDto);
        return new ResponseEntity<>(ReportMapper.fromReportDtoToReportResponse(createdReportDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ReportResponse>> getAllReports(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Page<ReportDto> reportDtoPage = reportService.findAll(page, size);
        List<ReportResponse> content = reportDtoPage.data().stream()
                .map(ReportMapper::fromReportDtoToReportResponse)
                .toList();
        return new ResponseEntity<>(new Page<>(content, page, size, reportDtoPage.totalElements()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReportById(@PathVariable Long id) {
        ReportDto reportDto = reportService.findById(id).get();
        return new ResponseEntity<>(ReportMapper.fromReportDtoToReportResponse(reportDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportResponse> updateReport(@PathVariable Long id,
            @RequestBody ReportRequest reportRequest) {
        if (!id.equals(reportRequest.id())) {
            throw new IllegalArgumentException("El id del reporte no coincide con el id proporcionado");
        }
        ReportDto reportDto = ReportMapper.fromReportRequestToReportDto(reportRequest);
        DtoValidator.validate(reportDto);
        ReportDto updatedReportDto = reportService.update(reportDto);
        return new ResponseEntity<>(ReportMapper.fromReportDtoToReportResponse(updatedReportDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
