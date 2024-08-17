package com.sidibrahim.Aman.exception.handler;
import com.sidibrahim.Aman.dto.ResponseMessage;
import com.sidibrahim.Aman.exception.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ResponseMessage> handleGenericException(GenericException ex) {
        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build()
        );
    }
}
