package pl.bmstefanski.garbanzo.command;

import java.util.List;
import org.springframework.stereotype.Component;
import pl.bmstefanski.garbanzo.command.defaults.CommandExecutor;
import pl.bmstefanski.garbanzo.command.defaults.CommandInfo;
import pl.bmstefanski.garbanzo.command.defaults.CommandSender;
import pl.bmstefanski.garbanzo.dao.impl.UserEntityDaoImpl;

@Component
public class ExampleCommand implements CommandExecutor {

  @CommandInfo(name = "example")
  @Override
  public void execute(CommandSender commandSender, List<String> args) {
//    Optional<UserEntity> optionalUserEntity = Optional.empty();

  }


}
