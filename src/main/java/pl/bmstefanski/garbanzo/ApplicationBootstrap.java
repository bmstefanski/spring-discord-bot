package pl.bmstefanski.garbanzo;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ApplicationBootstrap implements CommandLineRunner {

  private final Environment environment;

  @Autowired
  public ApplicationBootstrap(Environment environment) {
    this.environment = environment;
  }

  public static void main(String[] args) {
    SpringApplication.run(ApplicationBootstrap.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    new JDABuilder(AccountType.BOT)
        .setToken(this.environment.getProperty("jda.discord.token"))
        .setEventManager(new AnnotatedEventManager())
        .buildBlocking();
  }

}
