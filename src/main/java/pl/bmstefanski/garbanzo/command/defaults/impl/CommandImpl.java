package pl.bmstefanski.garbanzo.command.defaults.impl;

import pl.bmstefanski.garbanzo.command.defaults.CommandExecutor;
import pl.bmstefanski.garbanzo.command.defaults.Command;

public class CommandImpl implements Command {

  private String name;
  private String usage;
  private int minArguments;
  private int maxArguments;
  private CommandExecutor executor;

  CommandImpl(CommandBuilder commandBuilder) {
    this.name = commandBuilder.getName();
    this.usage = commandBuilder.getUsage();
    this.minArguments = commandBuilder.getMinArguments();
    this.maxArguments = commandBuilder.getMaxArguments();
    this.executor = commandBuilder.getExecutor();
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setUsage(String usage) {
    this.usage = usage;
  }

  @Override
  public void setMinArguments(int minArguments) {
    this.minArguments = minArguments;
  }

  @Override
  public void setMaxArguments(int maxArguments) {
    this.maxArguments = maxArguments;
  }

  @Override
  public void setExecutor(CommandExecutor executor) {
    this.executor = executor;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getUsage() {
    return this.usage;
  }

  @Override
  public int getMinArguments() {
    return this.minArguments;
  }

  @Override
  public int getMaxArguments() {
    return this.maxArguments;
  }

  @Override
  public CommandExecutor getExecutor() {
    return this.executor;
  }

}
