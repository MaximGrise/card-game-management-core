package com.maxim.gamemanagement.core.controller.errorhandler;

import com.payfacto.gamemanagement.core.api.ErrorDto;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.server.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Path/Query param handler to override ugly Jersey message.
 */
@Provider
public class JerseyParamExceptionHandler implements ExceptionMapper<ParamException> {

  private final Logger logger =
      LoggerFactory.getLogger(UncaughtExceptionHandler.class);

  @Override
  public Response toResponse(ParamException exception) {
    logger.error("Invalid parameter.", exception);
    ErrorDto exceptionDto = new ErrorDto().code("param-0")
        .message(
            List.of(String.format("The parameter [%s] is invalid.", exception.getParameterName())));
    return Response
        .status(HttpStatus.BAD_REQUEST.value())
        .entity(exceptionDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
