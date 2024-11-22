package com.motyldrogi.bot.command;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.role.entity.Role;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.user.UserEntity;
import com.motyldrogi.bot.user.UserService;

public class SetAdminCommand implements CommandExecutor {
    
    private final TwitchApiService twitchApiService;
    private final UserService userService;

    public SetAdminCommand(TwitchApiService twitchApiService, UserService userService){
        this.twitchApiService = twitchApiService;
        this.userService = userService;
    }

    @CommandInfo(value = "Role", minArguments = 0, maxArguments = 3, usage = "<add> <role> <user>", description = "Set the specified role to the specified user")
    @Override
    public void execute(CommandParser command, UserEntity user){

        
        if (command.getArgs().length == 0){
            twitchApiService.sendMessage("@" + user.getName() + " Your role is " + user.getRole());
            return;
        } else if (!command.getArgs()[0].equals("add")){
            twitchApiService.sendMessage("@" + user.getName() + " Invalid command ! Usage: !role <add> <role> <user>");
            return;
        }
        
        Role role = null;
        try{
            role = Role.valueOf(command.getArgs()[1].toUpperCase());
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
        if (role == null){
            twitchApiService.sendMessage("@" + user.getName() + " Invalid role name !");
            return;
        }
        
        if (role.getLevel() >= user.getRole().getLevel() && !user.getRole().equals(Role.ADMIN)){
            twitchApiService.sendMessage("@" + user.getName() + " No permission to set this role !");
            return;
        }
        command.getArgs()[2] = command.getArgs()[2].replace("@","").trim().toLowerCase();

        UserEntity promotedRoleUser = userService.getUserByName(command.getArgs()[2]);
        
        if (promotedRoleUser == null) {
            twitchApiService.sendMessage("@" + user.getName() + " User must send a message on this channel !");
            return;
        }
            
        promotedRoleUser.setRole(role);
        userService.saveUser(promotedRoleUser);
        twitchApiService.sendMessage("The user " + promotedRoleUser.getName() + " has now role " + promotedRoleUser.getRole());

        
    }
}
