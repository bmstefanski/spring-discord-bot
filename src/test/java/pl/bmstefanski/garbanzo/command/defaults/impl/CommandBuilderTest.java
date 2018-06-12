package pl.bmstefanski.garbanzo.command.defaults.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bmstefanski.garbanzo.command.defaults.Command;
import pl.bmstefanski.garbanzo.command.defaults.CommandExecutor;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class CommandBuilderTest {

  @Test
  public void commandBuilderWithNameTest() {
    Command command = new CommandBuilder()
        .withName("example")
        .build();

    assertEquals("example", command.getName());
  }

  @Test
  public void commandBuilderWithMinAndMaxArgumentsTest() {
    Command command = new CommandBuilder()
        .withMinArguments(1)
        .withMaxArguments(3)
        .build();

    assertEquals(1, command.getMinArguments());
    assertEquals(3, command.getMaxArguments());
  }

  @Test
  public void commandBuilderWithUsageTest() {
    Command command = new CommandBuilder()
        .withUsage("test usage")
        .build();

    assertEquals("test usage", command.getUsage());
  }

  @Test
  public void commandBuilderWithCustomExecutor() {
    CommandExecutor commandExecutor = (commandSender, args) -> {
      commandSender.sendRawMessage("i am kinda untestable");
    };

    Command command = new CommandBuilder()
        .withName("name")
        .withUsage("usage")
        .withMinArguments(5)
        .withMaxArguments(50)
        .withCommandExecutor(commandExecutor)
        .build();

    assertEquals(commandExecutor, command.getExecutor());
  }

}
