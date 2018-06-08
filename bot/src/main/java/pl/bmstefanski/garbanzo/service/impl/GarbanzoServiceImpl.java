package pl.bmstefanski.garbanzo.service.impl;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.bmstefanski.garbanzo.listener.Listener;
import pl.bmstefanski.garbanzo.service.GarbanzoService;

@Service
public class GarbanzoServiceImpl implements GarbanzoService {

  private JDA jda;

  @Value("${jda.discord.token}")
  private String token;

  @Override
  public void startBot() throws LoginException, InterruptedException {
    this.jda = new JDABuilder(AccountType.BOT)
        .setToken(token)
        .setEventManager(new AnnotatedEventManager())
        .buildBlocking();
  }

  @Override
  public void shutdownBot() {
    this.jda.shutdown();
  }

  @Override
  public void registerListeners(Listener... listeners) {
    this.jda.addEventListener((Object[]) listeners);
  }

  @Override
  public JDA getJda() {
    return this.jda;
  }

}
