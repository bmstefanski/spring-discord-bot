package pl.bmstefanski.garbanzo.listener;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.SubscribeEvent;
import org.springframework.stereotype.Component;
import pl.bmstefanski.garbanzo.dao.impl.UserEntityDaoImpl;
import pl.bmstefanski.garbanzo.entity.UserEntity;
import pl.bmstefanski.garbanzo.entity.impl.UserEntityImpl;

@Component
public class GuildMemberJoinListener {

  private UserEntityDaoImpl userEntityDao;

  public GuildMemberJoinListener(UserEntityDaoImpl userEntityDao) {
    this.userEntityDao = userEntityDao;
  }

  @SubscribeEvent
  public void execute(GuildMemberJoinEvent event) {
    User user = event.getUser();

    Optional<UserEntity> optionalUserEntity = Optional.ofNullable(this.userEntityDao.read(user.getIdLong()));

    if (optionalUserEntity.isPresent()) {
      return;
    }

    UserEntityImpl userEntity = new UserEntityImpl.Builder()
        .withIdentifier(user.getIdLong())
        .withName(user.getName())
        .setRegistrationDate(Date.from(Instant.now()))
        .build();

    this.userEntityDao.create(userEntity);
  }

}
