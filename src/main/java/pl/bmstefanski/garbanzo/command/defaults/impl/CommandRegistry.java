package pl.bmstefanski.garbanzo.command.defaults.impl;

import java.awt.Color;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;
import net.dv8tion.jda.core.hooks.SubscribeEvent;
import pl.bmstefanski.garbanzo.command.defaults.Command;
import pl.bmstefanski.garbanzo.command.defaults.CommandExecutor;
import pl.bmstefanski.garbanzo.command.defaults.CommandInfo;
import pl.bmstefanski.garbanzo.command.defaults.CommandSender;
import pl.bmstefanski.garbanzo.component.MessageComponent;
import pl.bmstefanski.garbanzo.configuration.GarbanzoProperties;
import pl.bmstefanski.garbanzo.service.GarbanzoService;

public class CommandRegistry {

  private final MessageComponent messageComponent;
  private final GarbanzoService garbanzoService;
  private final GarbanzoProperties properties;

  public CommandRegistry(MessageComponent messageComponent, GarbanzoService garbanzoService,
      GarbanzoProperties properties) {
    this.messageComponent = messageComponent;
    this.garbanzoService = garbanzoService;
    this.properties = properties;
  }

  public void registerByClasses(Class<? extends CommandExecutor>... classes) {
    try {
      for (Class<? extends CommandExecutor> clazz : classes) {
        CommandExecutor commandExecutor = clazz.newInstance();
        this.registerByExecutors(commandExecutor);
      }
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  public void registerByExecutors(CommandExecutor... commandExecutors) {
    for (CommandExecutor commandExecutor : commandExecutors) {
      Method[] methods = commandExecutor.getClass().getMethods();

      for (Method method : methods) {
        if (method.isAnnotationPresent(CommandInfo.class)) {
          CommandInfo commandInfo = method.getAnnotation(CommandInfo.class);

          Command command = new CommandBuilder()
              .withName(commandInfo.name())
              .withUsage(commandInfo.usage())
              .withMinArguments(commandInfo.minArguments())
              .withMaxArguments(commandInfo.maxArguments())
              .withCommandExecutor(commandExecutor)
              .build();

          this.garbanzoService.getJda().addEventListener(new AnnotatedEventManager() {

            @SubscribeEvent
            public void messageReceivedEvent(MessageReceivedEvent event) {
              MessageChannel messageChannel = event.getChannel();
              String messageContent = event.getMessage().getContentRaw();
              CommandSender commandSender = new CommandSenderImpl(messageChannel, messageComponent,
                  event.getAuthor());

              String prefix = properties.getPrefix();

              if (messageContent.startsWith(prefix + command.getName())) {
                List<String> args = Arrays.stream(messageContent.split(" ")).skip(1)
                    .collect(Collectors.toList());

                if ((args.size() < command.getMinArguments()) || (args.size() > command
                    .getMaxArguments())) {

                  String usage = prefix + command.getName() + " " + command.getUsage();
                  MessageEmbed messageEmbed = new EmbedBuilder()
                      .setColor(Color.RED)
                      .setFooter(messageComponent.get("invalid-pattern", usage), null)
                      .build();
                  messageChannel.sendMessage(messageEmbed).queue();
                  return;
                }

                commandExecutor.execute(commandSender, args);
              }
            }

          });

        }
      }
    }
  }

}
