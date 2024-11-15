package com.motyldrogi.bot.command.defaults.impl;

import java.lang.reflect.Method;
import com.motyldrogi.bot.command.defaults.Command;
import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.service.CommandProcessorService;

public class CommandRegistry {

  private final CommandProcessorService commandProcessorService;

  public CommandRegistry(CommandProcessorService commandProcessorService) {
    this.commandProcessorService = commandProcessorService;
  }

  public void registerByExecutors(CommandExecutor... commandExecutors) {
    for (CommandExecutor commandExecutor : commandExecutors) {
      Method[] methods = commandExecutor.getClass().getMethods();

      for (Method method : methods) {
        if (method.isAnnotationPresent(CommandInfo.class)) {
          CommandInfo commandInfo = method.getAnnotation(CommandInfo.class);

          Command command = new CommandBuilder()
              .withName(commandInfo.value())
              .withUsage(commandInfo.usage())
              .withMinArguments(commandInfo.minArguments())
              .withMaxArguments(commandInfo.maxArguments())
              .withRole(commandInfo.role())
              .withDescription(commandInfo.description())
              .withCommandExecutor(commandExecutor)
              .build();

          this.commandProcessorService.registerCommand(command.getName(), command);
        }
      }
    }
  }

}
