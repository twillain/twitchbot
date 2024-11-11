package com.motyldrogi.bot.command.defaults.impl;

import com.motyldrogi.bot.command.defaults.Command;
import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.util.Role;

public class CommandImpl implements Command {

    private String name;
    private String usage;
    private int minArguments;
    private int maxArguments;
    private Role role;
    private String description;
    private CommandExecutor executor;
  
    CommandImpl(CommandBuilder commandBuilder) {
      this.name = commandBuilder.getName();
      this.usage = commandBuilder.getUsage();
      this.minArguments = commandBuilder.getMinArguments();
      this.maxArguments = commandBuilder.getMaxArguments();
      this.role = commandBuilder.getRole();
      this.description = commandBuilder.getDescription();
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
    public void setRole(Role role){
      this.role = role;
    }

    @Override
    public void setDescription(String description){
      this.description = description;
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
    public Role getRole(){
      return this.role;
    }

    @Override
    public String getDescription(){
      return this.description;
    }

  
    @Override
    public CommandExecutor getExecutor() {
      return this.executor;
    }
  
  } 
