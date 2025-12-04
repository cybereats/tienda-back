package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.LogService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.LogDto;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.LogMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.LogRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.LogResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<Page<LogResponse>> findAllLogs(@RequestParam(required = false, defaultValue = "1") int page,
                                                                 @RequestParam(required = false, defaultValue = "10") int size) {
        Page<LogDto> logDtoPage = logService.getAll(page, size);

        List<LogResponse> logResponses = logDtoPage.data().stream()
                .map(logDto -> LogMapper.getInstance().logDtoToLogResponse(logDto))
                .toList();

        Page<LogResponse> logPage = new Page<>(
                logResponses,
                logDtoPage.pageNumber(),
                logDtoPage.pageSize(),
                logDtoPage.totalElements()
        );

        return new ResponseEntity<>(logPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogResponse> getLogById(@PathVariable Long id) {
        LogResponse logResponse = LogMapper.getInstance().logDtoToLogResponse(logService.getById(id));
        return new ResponseEntity<>(logResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LogResponse> createLog(@RequestBody LogRequest logRequest) {
        LogDto logDto = LogMapper.getInstance().logRequestToLogDto(logRequest);
        DtoValidator.validate(logDto);
        LogDto createdLog = logService.create(logDto);
        return new ResponseEntity<>(LogMapper.getInstance().logDtoToLogResponse(createdLog), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogResponse> updateLog(@PathVariable("id") Long id, @RequestBody LogRequest logRequest) {
        if (!id.equals(logRequest.id())) {
            throw new IllegalArgumentException("ID in path and request body must match");
        }
        LogDto logDto = LogMapper.getInstance().logRequestToLogDto(logRequest);
        DtoValidator.validate(logDto);
        LogDto updatedLog = logService.update(logDto);
        return new ResponseEntity<>(LogMapper.getInstance().logDtoToLogResponse(updatedLog), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable("id") Long id) {
        logService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
