package pl.bmstefanski.garbanzo.command;

import java.awt.Color;
import java.util.List;
import java.util.Optional;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
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
      String footer = commandSender.getMessage("already-registered");

      MessageEmbed messageEmbed = new EmbedBuilder()
          .setColor(Color.RED)
          .setFooter(footer, null)
          .build();

      commandSender.sendEmbedMessage(messageEmbed);
      return;
    }

    User jdaUser = commandSender.getJdaUser();

    UserEntityImpl userEntity = new UserEntityImpl.Builder()
        .withIdentifier(jdaUser.getIdLong())
        .withName(jdaUser.getName())
        .build();

    this.userEntityDao.create(userEntity);

    String title = commandSender.getMessage("successfully-registered");
    String nickname = commandSender.getMessage("nickname");
    String identifier = commandSender.getMessage("identifier");
    String date = commandSender.getMessage("date");

    MessageEmbed messageEmbed = new EmbedBuilder()
        .setColor(Color.GREEN)
        .setTitle(title)
        .addField(nickname, userEntity.getName(), true)
        .addField(identifier, userEntity.getIdentifier().toString(), true)
        .addField(date, "todo", true)
        .build();

    commandSender.sendEmbedMessage(messageEmbed);
  }

}
