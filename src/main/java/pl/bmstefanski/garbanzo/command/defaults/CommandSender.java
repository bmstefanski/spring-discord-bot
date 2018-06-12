package pl.bmstefanski.garbanzo.command.defaults;

import java.util.List;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;

public interface CommandSender {

  void sendRawMessage(String message, Object... arguments);

  void sendRawMessage(List<String> messages, Object... arguments);

  void sendMessage(String key, String... arguments);

  void sendMessage(List<String> keys, String... arguments);

  String getMessage(String key, String... arguments);

  void sendEmbedMessage(MessageEmbed message);

  MessageChannel getMessageChannel();

  User getJdaUser();

}
