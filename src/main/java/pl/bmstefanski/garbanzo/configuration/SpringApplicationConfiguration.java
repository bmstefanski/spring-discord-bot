package pl.bmstefanski.garbanzo.configuration;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bmstefanski.garbanzo.command.defaults.impl.CommandRegistry;
import pl.bmstefanski.garbanzo.component.MessageComponent;
import pl.bmstefanski.garbanzo.service.GarbanzoService;
import pl.bmstefanski.garbanzo.service.impl.GarbanzoServiceImpl;

@Configuration
public class SpringApplicationConfiguration {

  private final MessageComponent messageComponent;
  private final GarbanzoProperties garbanzoProperties;

  public SpringApplicationConfiguration(MessageComponent messageComponent,
      GarbanzoProperties garbanzoProperties) {
    this.messageComponent = messageComponent;
    this.garbanzoProperties = garbanzoProperties;
  }

  @Bean
  public GarbanzoService garbanzoService() {
    return new GarbanzoServiceImpl();
  }

  @Bean
  public CommandRegistry commandRegistryBean() {
    return new CommandRegistry(this.messageComponent, this.garbanzoService(),
        this.garbanzoProperties);
  }

}
