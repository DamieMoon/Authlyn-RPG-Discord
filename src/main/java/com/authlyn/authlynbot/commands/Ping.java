package com.authlyn.authlynbot.commands;

import com.authlyn.authlynbot.commandutils.Command;
import com.authlyn.authlynbot.commandutils.CommandContext;

public class Ping implements Command {

    // The identifier for the command is "example"
    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "This is an example command";
    }

    // This command has no arguments.
    @Override
    public String getUsage() {
        return "ping";
        // return "example <required arg> [optional arg]";
    }

    // This command has no aliases
    @Override
    public String[] getAliases() {
        return new String[0];
        // return new String[]{"alias1", "alias2"};
    }

    // For this command all we do is reply to tell the member that he ran the command.
    @Override
    public void onCommand(CommandContext context) {
        context.getChannel().sendMessage("No one loves Bidoof").queue();
    }

}