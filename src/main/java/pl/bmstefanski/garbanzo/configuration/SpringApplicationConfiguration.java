package pl.bmstefanski.garbanzo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bmstefanski.garbanzo.command.defaults.impl.CommandRegistry;
import pl.bmstefanski.garbanzo.component.MessageComponent;
import pl.bmstefanski.garbanzo.service.GarbanzoService;

@Configuration
public class SpringApplicationConfiguration {

  private final MessageComponent messageComponent;
  private final GarbanzoService garbanzoService;

  @Autowired
  public SpringApplicationConfiguration(MessageComponent messageComponent,
      GarbanzoService garbanzoService) {
    this.messageComponent = messageComponent;
    this.garbanzoService = garbanzoService;
  }

  @Bean
  public CommandRegistry commandRegistry() {
    return new CommandRegistry(messageComponent, this.garbanzoService);
  }

}
