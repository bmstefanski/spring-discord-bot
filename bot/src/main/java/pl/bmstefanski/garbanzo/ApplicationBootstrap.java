package pl.bmstefanski.garbanzo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.bmstefanski.garbanzo.command.ExampleCommand;
import pl.bmstefanski.garbanzo.command.defaults.impl.CommandRegistry;
import pl.bmstefanski.garbanzo.service.impl.GarbanzoServiceImpl;

@EnableConfigurationProperties
@SpringBootApplication
public class ApplicationBootstrap implements CommandLineRunner {

  private final GarbanzoServiceImpl garbanzoService;
  private final CommandRegistry commandRegistry;

  public ApplicationBootstrap(GarbanzoServiceImpl garbanzoService, CommandRegistry commandRegistry) {
    this.garbanzoService = garbanzoService;
    this.commandRegistry = commandRegistry;
  }

  public static void main(String[] args) {
    SpringApplication.run(ApplicationBootstrap.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    this.garbanzoService.startBot();

    this.garbanzoService.registerListeners(

    );

    this.commandRegistry.registerByExecutors(
        new ExampleCommand()
    );
  }

}
