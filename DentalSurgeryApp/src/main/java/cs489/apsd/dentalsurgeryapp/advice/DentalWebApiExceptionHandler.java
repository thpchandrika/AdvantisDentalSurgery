package cs489.apsd.dentalsurgeryapp.advice;

import cs489.apsd.dentalsurgeryapp.dto.ResponseDto;
import cs489.apsd.dentalsurgeryapp.exceptions.DentistNotFoundException;
import cs489.apsd.dentalsurgeryapp.exceptions.PatientNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class DentalWebApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PatientNotFoundException.class)
    protected ResponseEntity<ResponseDto> handlePatientNotFoundException(PatientNotFoundException patientNotFoundException) {
        var response = new ResponseDto(false,
                null,
                HttpStatus.BAD_REQUEST.value(),
                patientNotFoundException.getMessage(),
                null
                );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DentistNotFoundException.class)
    protected ResponseEntity<ResponseDto> handleDentistNotFoundException(DentistNotFoundException ex) {
        var response = new ResponseDto(false,
                null,
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ResponseDto> handleAccessDeniedException(AccessDeniedException ex) {
        var response = new ResponseDto(false,
                null,
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception ex) {
        var response = new ResponseDto(false,
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<ResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//        Map<String, String> fieldError = new HashMap<>();
//        List<FieldError> fieldErrors= ex.getBindingResult().getFieldErrors();
//        for (FieldError error : fieldErrors) {
//            fieldError.put(error.getField(), error.getDefaultMessage());
//        }
//        var response = new ResponseDto(false,
//                null,
//                HttpStatus.BAD_REQUEST.value(),
//                null,
//                fieldError
//        );
//        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
//    }
}
