package pl.bmstefanski.garbanzo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.bmstefanski.garbanzo.command.CommitCommand;
import pl.bmstefanski.garbanzo.command.GitHubCommand;
import pl.bmstefanski.garbanzo.command.RegisterAccountCommand;
import pl.bmstefanski.garbanzo.command.SayCommand;
import pl.bmstefanski.garbanzo.command.defaults.impl.CommandRegistry;
import pl.bmstefanski.garbanzo.dao.impl.UserEntityDaoImpl;
import pl.bmstefanski.garbanzo.listener.GuildMemberJoinListener;
import pl.bmstefanski.garbanzo.service.GarbanzoService;

@EnableWebMvc
@EnableConfigurationProperties
@SpringBootApplication
public class ApplicationBootstrap implements CommandLineRunner {

  private final GarbanzoService garbanzoService;
  private final CommandRegistry commandRegistry;
  private final UserEntityDaoImpl userEntityDao;

  public ApplicationBootstrap(GarbanzoService garbanzoService, CommandRegistry commandRegistry,
      UserEntityDaoImpl userEntityDao) {
    this.garbanzoService = garbanzoService;
    this.commandRegistry = commandRegistry;
    this.userEntityDao = userEntityDao;
  }

  public static void main(String[] args) {
    SpringApplication.run(ApplicationBootstrap.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    this.garbanzoService.startBot();

    this.garbanzoService.registerListeners(
        new GuildMemberJoinListener(this.userEntityDao)
    );

    this.commandRegistry.registerByExecutors(
        new RegisterAccountCommand(this.userEntityDao),
        new GitHubCommand(),
        new CommitCommand(),
        new SayCommand()
    );
  }

}
