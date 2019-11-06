package me.moose.AdderMCDiscordBot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.BanList.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import com.tjplaysnow.discord.object.Bot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class UnBan extends ListenerAdapter implements EventListener, CommandExecutor{   
	public Main plugin;
	public JDA jda;
	public Guild guild;
	public UnBan(Main plugin) {
  	  this.plugin = plugin;
 	       plugin.getCommand("unban").setExecutor(this);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
    	Date date = new Date(System.currentTimeMillis());
    	Date unban = new Date(+1);
        String SenderString = sender.toString();
	    if(!(sender.hasPermission("Addermc.Ban.unban"))) {
	    	sender.sendMessage("Sorry you don't have Permission to do this.");
	    	return false;
	    }
	    if(sender.hasPermission("Addermc.Ban.unban")) { //perms check
			if (args.length <= 0) { //not right anmount of args
				sender.sendMessage("Please input a reason for unban");
		    }
		    if (args.length >= 1) {//args good
		    	Bukkit.getBanList(Type.NAME).pardon(args[0]);
		    	TextChannel textChannel = (TextChannel) plugin.jda.getTextChannelsByName("staff-log", true).get(0).sendMessage(SenderString + " Has unbanned " + args[0] + " For Reason: " + args[1]).complete();
        return true;
        }
	    }
		return false;
    }
}  	
