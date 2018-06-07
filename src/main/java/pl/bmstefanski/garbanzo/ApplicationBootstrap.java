package pl.bmstefanski.garbanzo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import pl.bmstefanski.garbanzo.command.ExampleCommand;
import pl.bmstefanski.garbanzo.command.defaults.CommandExecutor;
import pl.bmstefanski.garbanzo.command.defaults.impl.CommandRegistry;
import pl.bmstefanski.garbanzo.service.GarbanzoService;

@SpringBootApplication
public class ApplicationBootstrap implements CommandLineRunner {

  private final Environment environment;
  private final GarbanzoService garbanzoService;
  private final CommandRegistry commandRegistry;

  public ApplicationBootstrap(Environment environment, GarbanzoService garbanzoService,
      CommandRegistry commandRegistry) {
    this.environment = environment;
    this.garbanzoService = garbanzoService;
    this.commandRegistry = commandRegistry;
  }

  public static void main(String[] args) {
    SpringApplication.run(ApplicationBootstrap.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    this.garbanzoService.startBot(this.environment.getProperty("jda.discord.token"));

    this.garbanzoService.registerListeners(

    );

    this.registerCommandsByExecutor(
        new ExampleCommand()
    );
  }

  private void registerCommandsByExecutor(CommandExecutor... commandExecutors) {
    for (CommandExecutor commandExecutor : commandExecutors) {
      this.commandRegistry.registerByExecutor(commandExecutor);
    }
  }

}
