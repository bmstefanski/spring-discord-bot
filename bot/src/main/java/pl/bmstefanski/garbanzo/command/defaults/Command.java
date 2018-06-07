package pl.bmstefanski.garbanzo.command.defaults;

public interface Command {

  void setName(String name);

  void setUsage(String usage);

  void setMinArguments(int minArguments);

  void setMaxArguments(int maxArguments);

  void setExecutor(CommandExecutor executor);

  String getName();

  String getUsage();

  int getMinArguments();

  int getMaxArguments();

  CommandExecutor getExecutor();

}
