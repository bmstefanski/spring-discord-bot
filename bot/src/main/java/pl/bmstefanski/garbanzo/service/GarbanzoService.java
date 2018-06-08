package pl.bmstefanski.garbanzo.service;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.JDA;
import pl.bmstefanski.garbanzo.listener.Listener;

public interface GarbanzoService {

  void startBot() throws LoginException, InterruptedException;

  void shutdownBot();

  void registerListeners(Listener... listeners);

  JDA getJda();

}
