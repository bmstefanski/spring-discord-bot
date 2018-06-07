package pl.bmstefanski.garbanzo.command.defaults;

import java.util.List;

public interface CommandSender {

  void sendRawMessage(String message, Object... arguments);

  void sendRawMessage(List<String> messages, Object... arguments);

  void sendMessage(String key, String... arguments);

  void sendMessage(List<String> keys, String... arguments);

}
