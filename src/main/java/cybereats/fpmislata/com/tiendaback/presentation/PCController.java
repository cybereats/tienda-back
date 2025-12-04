package cybereats.fpmislata.com.tiendaback.presentation;

import cybereats.fpmislata.com.tiendaback.domain.model.Page;
import cybereats.fpmislata.com.tiendaback.domain.service.PCService;
import cybereats.fpmislata.com.tiendaback.domain.service.dto.PCDto;
import cybereats.fpmislata.com.tiendaback.domain.validation.DtoValidator;
import cybereats.fpmislata.com.tiendaback.presentation.mapper.PCMapper;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.request.PCRequest;
import cybereats.fpmislata.com.tiendaback.presentation.webModel.response.PCResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pcs")
public class PCController {
    private final PCService pcService;

    public PCController(PCService pcService) {
        this.pcService = pcService;
    }

    @GetMapping
    public ResponseEntity<Page<PCResponse>> findAllPCs(@RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Page<PCDto> pcDtoPage = pcService.getAll(page, size);

        List<PCResponse> pcResponses = pcDtoPage.data().stream()
                .map(pcDto -> PCMapper.getInstance().pcDtoToPCResponse(pcDto))
                .toList();

        Page<PCResponse> pcPage = new Page<>(
                pcResponses,
                pcDtoPage.pageNumber(),
                pcDtoPage.pageSize(),
                pcDtoPage.totalElements());

        return new ResponseEntity<>(pcPage, HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<PCResponse> getPCBySlug(@PathVariable String slug) {
        PCResponse pcResponse = PCMapper.getInstance().pcDtoToPCResponse(pcService.getBySlug(slug));
        return new ResponseEntity<>(pcResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PCResponse> createPC(@RequestBody PCRequest pcRequest) {
        PCDto pcDto = PCMapper.getInstance().pcRequestToPCDto(pcRequest);
        DtoValidator.validate(pcDto);
        PCDto createdPC = pcService.create(pcDto);
        return new ResponseEntity<>(PCMapper.getInstance().pcDtoToPCResponse(createdPC), HttpStatus.CREATED);
    }

    @PutMapping("/{slug}")
    public ResponseEntity<PCResponse> updatePC(@PathVariable("slug") String slug, @RequestBody PCRequest pcRequest) {
        if (!slug.equals(pcRequest.slug())) {
            throw new IllegalArgumentException("SLUG in path and request body must match");
        }
        PCDto pcDto = PCMapper.getInstance().pcRequestToPCDto(pcRequest);
        DtoValidator.validate(pcDto);
        PCDto updatedPC = pcService.update(pcDto);
        return new ResponseEntity<>(PCMapper.getInstance().pcDtoToPCResponse(updatedPC), HttpStatus.OK);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<Void> deletePC(@PathVariable("slug") String slug) {
        pcService.deleteBySlug(slug);
        return ResponseEntity.noContent().build();
    }
}
