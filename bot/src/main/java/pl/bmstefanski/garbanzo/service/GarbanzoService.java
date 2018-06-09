package pl.bmstefanski.garbanzo.service;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.JDA;
import org.springframework.stereotype.Service;

@Service
public interface GarbanzoService {

  void startBot() throws LoginException, InterruptedException;

  void shutdownBot();

  void registerListeners(Object... listeners);

  JDA getJda();

}
