package com.authlyn.authlynbot;

import com.authlyn.authlynbot.commands.*;
import com.authlyn.authlynbot.commandutils.CommandManager;
import com.authlyn.authlynbot.core.Database;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

/**
 * This class contains the JDA instance.
 */
public class Authlyn {

    // This will initialize the JDA instance when the program is started.
    public static void main(String args[]) throws LoginException, InterruptedException {
    	
    	// Test stuff to see if the database installation works
    	Database.installDatabase();
        Database.insert_items();
        Database.insert_locations();
        Database.insert_npcs();
        Database.insert_monsters();
    	
        JDABuilder.createLight("NzI3OTI3MTkxMTUyNjIzNzM2.Xvy9Bg.4JGXVZ0ellQ0TDdo7ZKHhgmiuEk", GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
        .addEventListeners(new GuildMessageReceivedListener())
        .setActivity(Activity.playing("Type !ping"))
        .build();
        
        // Here we register the Commands.
        CommandManager.registerCommand(new Ping());
    }

    /**
     * Getter for the JDA instance.
     * @return the JDA instance for this session.
     */

}