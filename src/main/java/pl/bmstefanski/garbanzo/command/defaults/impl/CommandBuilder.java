package pl.bmstefanski.garbanzo.command.defaults.impl;

import pl.bmstefanski.garbanzo.command.defaults.CommandExecutor;
import pl.bmstefanski.garbanzo.command.defaults.Command;
import pl.bmstefanski.garbanzo.util.Builder;

public class CommandBuilder implements Builder<Command> {

  private String name;
  private String usage = "";
  private int minArguments = 0;
  private int maxArguments = 0;
  private CommandExecutor executor;

  public CommandBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public CommandBuilder withUsage(String usage) {
    this.usage = usage;
    return this;
  }

  public CommandBuilder withMinArguments(int minArguments) {
    this.minArguments = minArguments;
    return this;
  }

  public CommandBuilder withMaxArguments(int maxArguments) {
    this.maxArguments = maxArguments;
    return this;
  }

  public CommandBuilder withCommandExecutor(CommandExecutor commandExecutor) {
    this.executor = commandExecutor;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public String getUsage() {
    return this.usage;
  }

  public int getMinArguments() {
    return this.minArguments;
  }

  public int getMaxArguments() {
    return this.maxArguments;
  }

  public CommandExecutor getExecutor() {
    return this.executor;
  }

  @Override
  public Command build() {
    return new CommandImpl(this);
  }

}
