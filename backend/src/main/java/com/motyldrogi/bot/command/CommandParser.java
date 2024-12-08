package com.motyldrogi.bot.command;

import java.util.ArrayList;

public class CommandParser {

    private String command;
    private String[] args;

    public CommandParser(String input) {
        String trimmedInput = input.startsWith("!") ? input.substring(1) : input;

        // Séparer la commande et les arguments
        String[] parts = trimmedInput.split(" ", -1);

        // Le premier élément est la commande
        this.command = parts[0];

        // Créer une liste pour les arguments
        ArrayList<String> arguments = new ArrayList<>();

        // Traiter les autres éléments comme arguments
        StringBuilder currentArg = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];

            // Si l'argument commence par un guillemet, commencer à traiter l'argument
            if (part.startsWith("\"")) {
                insideQuotes = true;
                if (part.endsWith("\"")) arguments.add(part);
                else currentArg.append(part.substring(1)); // Retirer le guillemet ouvrant
            } else if (part.endsWith("\"") && insideQuotes) {
                // Si l'argument se termine par un guillemet et qu'on est dans un texte entre guillemets
                currentArg.append(" ").append(part.substring(0, part.length() - 1)); // Retirer le guillemet fermant
                arguments.add(currentArg.toString());
                currentArg.setLength(0); // Réinitialiser le StringBuilder pour le prochain argument
                insideQuotes = false;
            } else if (insideQuotes) {
                // Si on est à l'intérieur de guillemets, ajouter à l'argument en cours
                currentArg.append(" ").append(part);
            } else {
                // Sinon, ajouter l'argument normalement
                arguments.add(part);
            }
        }

        // Si un argument est en cours (et qu'il est entre guillemets), on l'ajoute
        if (currentArg.length() > 0) {
            arguments.add(currentArg.toString());
        }

        // Convertir la liste d'arguments en un tableau
        this.args = arguments.toArray(new String[0]);
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("CommandParser - Command : " + this.command + ", args :");
        for (int i = 0; i < this.args.length; i++) {
            sb.append(String.format("\n - arg[%s] : %s", i, this.args[i]));
        }
        return sb.toString();
    }

    public static void main(String... args) {
        CommandParser cmd = new CommandParser("!poll new 0 \"Test titre\" 30 \"Choice1\" \"Choice2\"");
        System.out.println(cmd);
    }
}
