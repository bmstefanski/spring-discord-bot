package pl.bmstefanski.garbanzo.command.defaults.impl;

import java.util.List;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import pl.bmstefanski.garbanzo.command.defaults.CommandSender;
import pl.bmstefanski.garbanzo.component.MessageComponent;

public class CommandSenderImpl implements CommandSender {

  private final MessageChannel messageChannel;
  private final MessageComponent messageComponent;
  private final User user;

  public CommandSenderImpl(MessageChannel messageChannel,
      MessageComponent messageComponent, User user) {
    this.messageChannel = messageChannel;
    this.messageComponent = messageComponent;
    this.user = user;
  }

  @Override
  public void sendRawMessage(String message, Object... arguments) {
    this.messageChannel.sendMessageFormat(message, arguments).queue();
  }

  @Override
  public void sendRawMessage(List<String> messages, Object... arguments) {
    messages.forEach(message -> this.sendRawMessage(message, arguments));
  }

  @Override
  public void sendMessage(String key, String... arguments) {
    this.messageChannel.sendMessage(this.messageComponent.get(key, arguments)).queue();
  }

  @Override
  public void sendMessage(List<String> keys, String... arguments) {
    keys.forEach(key -> this.sendMessage(key, arguments));
  }

  @Override
  public String getMessage(String key, String... arguments) {
    return this.messageComponent.get(key, arguments);
  }

  @Override
  public void sendEmbedMessage(MessageEmbed message) {
    this.messageChannel.sendMessage(message).queue();
  }

  @Override
  public MessageChannel getMessageChannel() {
    return this.messageChannel;
  }

  @Override
  public User getJdaUser() {
    return this.user;
  }

}
