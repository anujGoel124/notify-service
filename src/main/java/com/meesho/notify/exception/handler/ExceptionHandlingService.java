package com.meesho.notify.exception.handler;

import com.meesho.notify.exception.BadRequestException;
import com.meesho.notify.exception.InternalServerException;
import com.meesho.notify.exception.NotFoundException;
import com.meesho.notify.models.response.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
@Slf4j
public class ExceptionHandlingService extends ResponseEntityExceptionHandler {

    private static final String ERROR = "INVALID REQUEST";

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequest(Exception ex) {
        log.error("error is {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ServiceResponse.builder().error(ServiceResponse.Error.builder().code(ERROR).message(ex.getMessage()).build()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFound(Exception ex) {
        log.error("error is {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ServiceResponse.builder().error(ServiceResponse.Error.builder().code(ERROR).message(ex.getMessage()).build()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerException.class)
    public final ResponseEntity<Object> handleInternalServerError(Exception ex) {
        log.error("error is {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ServiceResponse.builder().error(ServiceResponse.Error.builder().code(ERROR).message(ex.getMessage()).build()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
