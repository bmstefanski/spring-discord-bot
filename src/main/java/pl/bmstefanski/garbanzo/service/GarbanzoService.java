package pl.bmstefanski.garbanzo.service;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;
import org.springframework.stereotype.Service;
import pl.bmstefanski.garbanzo.command.listener.Listener;

@Service
public class GarbanzoService {

  private JDA jda;

  public void startBot(String token) throws LoginException, InterruptedException {
    this.jda = new JDABuilder(AccountType.BOT)
        .setToken(token)
        .setEventManager(new AnnotatedEventManager())
        .buildBlocking();
  }

  public void shutdownBot() {
    this.jda.shutdown();
  }

  public void registerListeners(Listener... listeners) {
    this.jda.addEventListener((Object[]) listeners);
  }

  public JDA getJda() {
    return this.jda;
  }

}
