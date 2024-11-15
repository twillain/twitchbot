package com.motyldrogi.bot.component;

public class TwitchMessage {
    
    private String rawMessage;
    private String sentBy;
    private String message;
    private String channel;
    private String prefix;
    private String data;
    private String command;

    public TwitchMessage(String rawMessage, String prefix) {
        this.rawMessage = rawMessage;
        this.prefix = prefix;
        parseMessage(rawMessage);
    }

    private void parseMessage(String rawMessage) {
        String[] splitMessage = rawMessage.split(":");
        
        // Parse metadata
        String[] splitMetadata = splitMessage[1].split(" ");
        this.sentBy = splitMetadata[0].split("!")[0];
        this.channel = splitMetadata[2].replace("#", "").trim();

        // Extract the message
        String metadata = ":" + splitMetadata[0] + " PRIVMSG " + "#" + this.channel + " :";
        this.message = this.rawMessage.replace(metadata, ""); 

        // Parse data
        this.command = this.message.split(" ")[0].replace(this.prefix, "");
        this.data = this.message.replace(this.prefix + this.command, "").trim();
        System.err.println(data);
    }

    public String getMessage() {
        return this.message;
    }

    public String getData() {
        return data;
    }

    public String getCommand() {
        return command;
    }

    public String getSentBy() { 
        return this.sentBy;
    }

    public String getChannel() {
        return this.channel;
    }
}
