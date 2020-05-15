package com.maxim.gamemanagement.core.controller.errorhandler;

import com.maxim.gamemanagement.core.controller.ControllerException;
import com.payfacto.gamemanagement.core.api.ErrorDto;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ControllerExceptionHandler implements ExceptionMapper<ControllerException> {

  private final Logger logger =
      LoggerFactory.getLogger(ControllerExceptionHandler.class);

  @Override
  public Response toResponse(ControllerException exception) {
    logger.warn("Controller exception, message: {}", exception.getMessage());
    logger.debug("Controller exception, stack: ", exception);

    ErrorDto error = new
        ErrorDto().code(exception.getCode()).message(List.of(exception.getDescription()));
    return Response
        .status(exception.getHttpStatus().value())
        .entity(error)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
