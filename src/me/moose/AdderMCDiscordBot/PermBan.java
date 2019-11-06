package me.moose.AdderMCDiscordBot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.BanList.Type;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
public class PermBan extends ListenerAdapter implements EventListener, CommandExecutor{   
	public Main plugin;
	public JDA jda;
	public PermBan(Main plugin) {
  	  this.plugin = plugin;
 	       plugin.getCommand("ban").setExecutor(this);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {  //Command start heres
	    if(!(sender.hasPermission("Addermc.Ban.perm"))) { //null perms
	    	sender.sendMessage("Sorry you don't have Permission to do this.");
	    	return false;
	    }
	    if(sender.hasPermission("Addermc.Ban.perm")) { //have perms and args is bad
			if (args.length <= 0) { 
				sender.sendMessage("usage /ban (Player) (Reason)");
		    	return true;
		    }
	    }
    	Player pp = Bukkit.getServer().getPlayer(args[0]);
    	String TargetName = pp.getName();
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
    	Date date = new Date(System.currentTimeMillis());
        UUID ppu = pp.getUniqueId();
        String TargetUuid = ppu.toString();
        String SenderString = sender.getName().toString();
	    if(!(sender.hasPermission("Addermc.Ban.perm"))) { //null perms
	    	sender.sendMessage("Sorry you don't have Permission to do this.");
	    	return true;
	    }
	    if(sender.hasPermission("Addermc.Ban.perm")) { //have perms and args is bad
		    if (args.length >= 1) { //have pwerms and args is good
		        plugin.getServer().getBanList(Type.NAME).addBan(TargetName, "§cYou are banned: " + args[1] + "\n §cAppeal on the Discord", null, null);
		    	pp.kickPlayer("You were banned for: " + args[1]);
		    	TextChannel textChannel = (TextChannel) plugin.jda.getTextChannelsByName("staff-log", true).get(0).sendMessage(SenderString + " Has Permanently Banned " + args[0] + " For Reason: " + args[1] + " UUID: " + TargetUuid).complete();
        }
	    }
		return false;
    }
}  

