package gaedianz.org.Picle.common.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import gaedianz.org.Picle.common.dto.ApiResponse;
import gaedianz.org.Picle.exception.Error;
import gaedianz.org.Picle.exception.model.PicleException;

import java.util.Objects;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 400 BAD_REQUEST
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return ApiResponse.error(Error.REQUEST_VALIDATION_EXCEPTION, String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
    }

    /**
     * 500 Internal Server
     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    protected ApiResponse<Object> handleException(final Exception e) {
//        return ApiResponse.error(Error.INTERNAL_SERVER_ERROR);
//    }

    /**
     * Sopt custom error
     */
    @ExceptionHandler(PicleException.class)
    protected ResponseEntity<ApiResponse> handleSoptException(PicleException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponse.error(e.getError(), e.getMessage()));
    }
}
