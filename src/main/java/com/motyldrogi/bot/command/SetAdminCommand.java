package com.motyldrogi.bot.command;

import java.util.Optional;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.repository.UserRepository;
import com.motyldrogi.bot.service.TwitchApiService;
import com.motyldrogi.bot.util.Role;

public class SetAdminCommand implements CommandExecutor {
    
    private final UserRepository userRepository;

    public SetAdminCommand(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @CommandInfo(value = "Role", minArguments = 0, maxArguments = 3, usage = "<add> <role> <user>", description = "Set the specified role to the specified user")
    @Override
    public void execute(TwitchApiService twitchApiService, String commandString, UserEntityImpl user){

        String[] data = commandString.trim().split(" ");
        
        if (data.length == 1){
            twitchApiService.sendMessage("@" + user.getName() + " Your role is " + user.getRole());
            return;
        } else if (!data[1].toLowerCase().equals("add")){
            twitchApiService.sendMessage("@" + user.getName() + " Invalid command ! Usage: !role <add> <role> <user>");
            return;
        }
        
        Role role = null;
        try{
            role = Role.valueOf(data[2].toUpperCase());
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
        data[3] = data[3].replace("@","").trim().toLowerCase();

        Optional<UserEntityImpl> optionalPromotedRoleUser = userRepository.findByName(data[3]);

        if (!optionalPromotedRoleUser.isPresent()){
            twitchApiService.sendMessage("@" + user.getName() + " User must send a message on this channel !");
            return;
        } else {
            UserEntityImpl promotedRoleUser = optionalPromotedRoleUser.get();
            promotedRoleUser.setRole(role);
            userRepository.save(promotedRoleUser);
            twitchApiService.sendMessage("The user " + promotedRoleUser.getName() + " has now role " + promotedRoleUser.getRole());
        }

        
    }
}
