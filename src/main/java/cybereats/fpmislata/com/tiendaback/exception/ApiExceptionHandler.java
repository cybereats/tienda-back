package cybereats.fpmislata.com.tiendaback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(org.springframework.web.client.HttpStatusCodeException.class)
    public org.springframework.http.ResponseEntity<ErrorMessage> handleHttpStatusCodeException(
            org.springframework.web.client.HttpStatusCodeException ex) {
        System.err.println("Microservice Error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
        ErrorMessage microserviceError = ex.getResponseBodyAs(ErrorMessage.class);
        if (microserviceError == null) {
            microserviceError = new ErrorMessage("MicroserviceError", ex.getMessage());
        }
        return new org.springframework.http.ResponseEntity<>(microserviceError, ex.getStatusCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ BusinessException.class })
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ErrorMessage handleBusinessException(BusinessException ex) {
        return new ErrorMessage(ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ ResourceNotFoundException.class })
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorMessage(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ ValidationException.class, IllegalArgumentException.class,
            HttpMessageNotReadableException.class })
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ErrorMessage handleValidationException(Exception ex) {
        System.err.println("Validation Error: " + ex.getMessage());
        return new ErrorMessage(ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ErrorMessage handleGeneralException(Exception exception) {
        return new ErrorMessage(exception);
    }
}