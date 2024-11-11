package com.motyldrogi.bot.command;

import java.util.Optional;

import com.motyldrogi.bot.command.defaults.CommandExecutor;
import com.motyldrogi.bot.command.defaults.CommandInfo;
import com.motyldrogi.bot.command.defaults.CommandSender;
import com.motyldrogi.bot.component.TwitchMessage;
import com.motyldrogi.bot.entity.impl.UserEntityImpl;
import com.motyldrogi.bot.repository.UserRepository;
import com.motyldrogi.bot.util.Role;

public class SetAdminCommand implements CommandExecutor {
    
    private final UserRepository userRepository;

    public SetAdminCommand(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @CommandInfo(value = "Role", minArguments = 0, maxArguments = 3, usage = "<add> <role> <user>", description = "Set the specified role to the specified user")
    @Override
    public void execute(TwitchMessage tMessage, CommandSender commandSender, UserEntityImpl user){

        if (tMessage.getData().trim().isEmpty()){
            commandSender.sendRawMessage("@" + tMessage.getSentBy() + " Your role is " + user.getRole());
            return;
        } 
    
        String[] data = tMessage.getData().split(" ");

        if (!data[0].toLowerCase().equals("add")){
            commandSender.sendRawMessage("@" + tMessage.getSentBy() + " Invalid command ! Usage: !role <add> <role> <user>");
            return;
        }

        UserEntityImpl userEntity = null;

        
        data[1] = data[1].toUpperCase();
        Role role = null;
        try{
            role = Role.valueOf(data[1]);
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
        if (role == null){
            commandSender.sendRawMessage("@" + tMessage.getSentBy() + " Invalid role name !");
            return;
        }
        
        if (Role.valueOf(data[1]).getLevel() >= user.getRole().getLevel() && !user.getRole().equals(Role.ADMIN)){
            commandSender.sendRawMessage("@" + tMessage.getSentBy() + " No permission to set this role !");
            return;
        }
        data[2] = data[2].replace("@","").trim().toLowerCase();

        Optional<UserEntityImpl> optionalUserEntity = userRepository.findByName(data[2]);

        if (!optionalUserEntity.isPresent()){
            userEntity = new UserEntityImpl.Builder()
                .withIdentifier(null)
                .withName(data[2])
                .withRole(role)
                .withNumberMessagesSent(0)
                .withCounter(0)
                .build();

            userEntity = userRepository.save(userEntity);
        }

        if (userEntity == null){
            userEntity = optionalUserEntity.get();
            userEntity.setRole(role);
            userRepository.save(userEntity);
        }

        commandSender.sendRawMessage("The user " + userEntity.getName() + " has now role " + userEntity.getRole());
    }
}
