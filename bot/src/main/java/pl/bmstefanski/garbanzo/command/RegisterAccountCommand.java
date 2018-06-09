package pl.bmstefanski.garbanzo.command;

import java.util.List;
import java.util.Optional;
import net.dv8tion.jda.core.entities.User;
import org.springframework.stereotype.Component;
import pl.bmstefanski.garbanzo.command.defaults.CommandExecutor;
import pl.bmstefanski.garbanzo.command.defaults.CommandInfo;
import pl.bmstefanski.garbanzo.command.defaults.CommandSender;
import pl.bmstefanski.garbanzo.dao.impl.UserEntityDaoImpl;
import pl.bmstefanski.garbanzo.entity.UserEntity;
import pl.bmstefanski.garbanzo.entity.impl.UserEntityImpl;

@Component
public class RegisterAccountCommand implements CommandExecutor {

  private final UserEntityDaoImpl userEntityDao;

  public RegisterAccountCommand(UserEntityDaoImpl userEntityDao) {
    this.userEntityDao = userEntityDao;
  }

  @CommandInfo(name = "register")
  @Override
  public void execute(CommandSender commandSender, List<String> args) {
    long userId = commandSender.getJdaUser().getIdLong();

    Optional<UserEntity> optionalUserEntity = Optional.ofNullable(userEntityDao.read(userId));

    if (optionalUserEntity.isPresent()) {
      commandSender.sendRawMessage("Jestes już zarejestrowany!");
      return;
    }

    User jdaUser = commandSender.getJdaUser();

    UserEntityImpl userEntity = new UserEntityImpl.Builder()
        .withIdentifier(jdaUser.getIdLong())
        .withName(jdaUser.getName())
        .build();

    this.userEntityDao.create(userEntity);

    commandSender.sendRawMessage("Pomyslnie zarejestrowano! \n Name: %s \n Identifier: %s",
        userEntity.getName(), userEntity.getIdentifier());
  }

}
