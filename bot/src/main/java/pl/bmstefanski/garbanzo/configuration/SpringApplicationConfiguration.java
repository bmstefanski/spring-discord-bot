package pl.bmstefanski.garbanzo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bmstefanski.garbanzo.command.defaults.impl.CommandRegistry;
import pl.bmstefanski.garbanzo.component.MessageComponent;
import pl.bmstefanski.garbanzo.service.impl.GarbanzoServiceImpl;

@Configuration
public class SpringApplicationConfiguration {

  private final MessageComponent messageComponent;
  private final GarbanzoServiceImpl garbanzoService;
  private final GarbanzoProperties garbanzoProperties;

  public SpringApplicationConfiguration(
      MessageComponent messageComponent,
      GarbanzoServiceImpl garbanzoService,
      GarbanzoProperties garbanzoProperties) {
    this.messageComponent = messageComponent;
    this.garbanzoService = garbanzoService;
    this.garbanzoProperties = garbanzoProperties;
  }

  @Bean
  public CommandRegistry commandRegistryBean() {
    return new CommandRegistry(this.messageComponent, this.garbanzoService, this.garbanzoProperties);
  }

}
