package com.maxim.gamemanagement.core.controller;

import com.maxim.gamemanagement.core.controller.errorhandler.ConstraintExceptionHandler;
import com.maxim.gamemanagement.core.controller.errorhandler.ControllerExceptionHandler;
import com.maxim.gamemanagement.core.controller.errorhandler.JerseyParamExceptionHandler;
import com.maxim.gamemanagement.core.controller.errorhandler.JsonMappingExceptionHandler;
import com.maxim.gamemanagement.core.controller.errorhandler.JsonParseExceptionHandler;
import com.maxim.gamemanagement.core.controller.errorhandler.UncaughtExceptionHandler;
import com.maxim.gamemanagement.core.controller.card.CardController;
import com.maxim.gamemanagement.core.controller.deck.DeckController;
import com.maxim.gamemanagement.core.controller.game.GameController;
import com.maxim.gamemanagement.core.controller.player.PlayerController;
import java.util.Set;
import javax.validation.ConstraintViolationException;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

  @Bean
  public ResourceConfig resourceConfig() {
    return new ResourceConfig()
        .property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true)
        .registerClasses(
            Set.of(
                // controllers
                GameController.class,
                PlayerController.class,
                DeckController.class,
                CardController.class,
                // error handlers
                ConstraintExceptionHandler.class,
                JerseyParamExceptionHandler.class,
                JsonParseExceptionHandler.class,
                JsonMappingExceptionHandler.class,
                ControllerExceptionHandler.class,
                UncaughtExceptionHandler.class));
  }
}
