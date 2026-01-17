package org.example.library.web.exception;

import jakarta.persistence.OptimisticLockException;
import org.example.library.api.error.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OptimisticLockException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleOptimisticLock(
            OptimisticLockException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponse error = new ApiErrorResponse();
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());
        error.setErrorCode("CONCURRENT_MODIFICATION");
        error.setMessage("Resource was modified by another request");
        return error;
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleIllegalState(
            IllegalStateException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponse error = new ApiErrorResponse();
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());
        error.setErrorCode(ex.getMessage());
        error.setMessage("Invalid operation");
        return error;
    }
}
