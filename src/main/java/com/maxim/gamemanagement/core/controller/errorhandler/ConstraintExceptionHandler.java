package com.maxim.gamemanagement.core.controller.errorhandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.payfacto.gamemanagement.core.api.ErrorDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Priority;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

@Priority(1)
@Provider
public class ConstraintExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

  private final Logger logger =
      LoggerFactory.getLogger(UncaughtExceptionHandler.class);

  @Override
  public Response toResponse(ConstraintViolationException exception) {
    logger.error("Invalid parameter.", exception);
    ErrorDto exceptionDto = new ErrorDto().code("c-0")
        .message(exception.getConstraintViolations()
            .stream()
            .map(violation -> {
              return ". Provided value [" + violation.getInvalidValue()
                  + "] for property [" + violation.getPropertyPath() + "], but property '"
                  + violation.getMessage() + "'";
            }).collect(Collectors.toList()));
    return Response
        .status(HttpStatus.BAD_REQUEST.value())
        .entity(exceptionDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}