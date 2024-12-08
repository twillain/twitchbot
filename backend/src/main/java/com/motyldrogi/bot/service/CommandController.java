package com.motyldrogi.bot.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motyldrogi.bot.command.CommandParser;
import com.motyldrogi.bot.command.defaults.Command;
import com.motyldrogi.bot.user.UserEntity;
import com.motyldrogi.bot.user.UserService;

@RestController
@RequestMapping("/command")
public class CommandController {

    private CommandProcessorService commandProcessorService;
    private UserService userService;

    public CommandController(CommandProcessorService commandProcessorService, UserService userService){
        this.commandProcessorService = commandProcessorService;
        this.userService = userService;
    }


    @GetMapping("/commands")
    public Collection<Command> getAllCommands(){
        return this.commandProcessorService.getAllCommands().values();
    }

    @PostMapping("/send")
    public void sendCommand(
        @RequestHeader(value = "Broadcaster-Id", required = true) String broadcasterId,
        @RequestBody String command){
            System.out.println("Command : " + command);
            UserEntity user = userService.getUserByTwitchId(broadcasterId);
            this.commandProcessorService.processCommand(new CommandParser(command), user);
    }
    
}
