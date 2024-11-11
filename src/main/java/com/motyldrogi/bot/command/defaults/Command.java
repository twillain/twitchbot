package com.motyldrogi.bot.command.defaults;

import com.motyldrogi.bot.util.Role;

public interface Command {

    void setName(String name);
  
    void setUsage(String usage);

    void setMinArguments(int minArguments);

    void setMaxArguments(int maxArguments);

    void setRole(Role role);

    void setDescription(String description);
  
    void setExecutor(CommandExecutor executor);
  
    String getName();
  
    String getUsage();

    int getMinArguments();

    int getMaxArguments();

    Role getRole();

    String getDescription();
  
    CommandExecutor getExecutor();
  
  }