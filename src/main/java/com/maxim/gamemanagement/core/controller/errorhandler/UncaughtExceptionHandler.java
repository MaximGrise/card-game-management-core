package com.maxim.gamemanagement.core.controller.errorhandler;

import com.payfacto.gamemanagement.core.api.ErrorDto;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Catching any error bubbling to the Controller.
 */
@Provider
public class UncaughtExceptionHandler implements ExceptionMapper<Throwable> {

  private final Logger logger =
      LoggerFactory.getLogger(UncaughtExceptionHandler.class);

  @Override
  public Response toResponse(Throwable exception) {
    String thumbprint = System.currentTimeMillis() + "-" + new Random().nextInt();
    logger.error("Uncaught error bubbled up to controller, thumbprint " + thumbprint, exception);
    ErrorDto exceptionDto = new ErrorDto().code("999")
        .message(List.of("Internal error, thumbprint: " + thumbprint));
    return Response
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .entity(exceptionDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}