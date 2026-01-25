package com.kickdrum.smarthome.exception;

import com.kickdrum.smarthome.dto.response.ErrorResponse;
import com.kickdrum.smarthome.util.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex
    ) {

        log.warn("Business exception occurred. Code: {}, Message: {}",
                ex.getErrorCode(), ex.getMessage());

        HttpStatus status = mapStatus(ex.getErrorCode());

        ErrorResponse response =
                new ErrorResponse(ex.getErrorCode(), ex.getMessage());

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        log.error("Unhandled exception occurred", ex);

        ErrorResponse response =
                new ErrorResponse(
                        ErrorCode.INTERNAL_ERROR,
                        "Something went wrong"
                );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    private HttpStatus mapStatus(ErrorCode code) {

        switch (code) {

            case AUTH_FAILED:
                return HttpStatus.UNAUTHORIZED;

            case USER_ALREADY_EXISTS:
                return HttpStatus.CONFLICT;

            case USER_NOT_FOUND:
                return HttpStatus.NOT_FOUND;

            case HOUSE_NOT_FOUND:
            case ROOM_NOT_FOUND:
            case DEVICE_NOT_FOUND:
                return HttpStatus.NOT_FOUND;

            case HOUSE_ACCESS_DENIED:
            case ROOM_ACCESS_DENIED:
                return HttpStatus.FORBIDDEN;

            case DEVICE_ALREADY_REGISTERED:
                return HttpStatus.CONFLICT;

            case DEVICE_INVALID_CREDENTIALS:
                return HttpStatus.BAD_REQUEST;

            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
