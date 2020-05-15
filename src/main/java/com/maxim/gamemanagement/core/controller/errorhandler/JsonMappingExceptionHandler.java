package com.maxim.gamemanagement.core.controller.errorhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.payfacto.gamemanagement.core.api.ErrorDto;
import java.util.List;
import javax.annotation.Priority;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Json mapping error handler to override ugly Jersey/Jackson message.
 */
@Priority(1)
@Provider
public class JsonMappingExceptionHandler implements ExceptionMapper<JsonMappingException> {

  private final Logger logger =
      LoggerFactory.getLogger(UncaughtExceptionHandler.class);

  @Override
  public Response toResponse(JsonMappingException exception) {
    logger.error("Invalid json request.", exception);
    ErrorDto exceptionDto = new ErrorDto().code("br-0")
        .message(List.of(
            "A field in the request json could not be mapped. Potentially an typo in the name, or a invalid enum value."));
    return Response
        .status(HttpStatus.BAD_REQUEST.value())
        .entity(exceptionDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
