package me.moose.AdderMCDiscordBot;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.BanList.Type;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
public class Main extends JavaPlugin implements Listener{
	public Main plugin;
	public static JDA jda;
	public final String TOKEN = "token";
	public final String PREFIX = "!";
	
	@Override 
	public void onEnable() {
	plugin = this;
	this.getServer().getPluginManager().registerEvents(this, this);
	startBot();
	System.out.print("Made by Moose1301. Discord: Moose1301#9437");
	new PermBan(this);
	new UnBan(this);
	jda.addEventListener(new PermBan(this));
	jda.addEventListener(new UnBan(this));
}
	private void startBot() {
        try {
			jda = new JDABuilder(AccountType.BOT).setToken(TOKEN).build();
			jda.addEventListener(new PermBan(this));
			jda.addEventListener(new UnBan(this));
		} catch (LoginException e) {
			System.out.print("ERROR");
			e.printStackTrace();
			System.out.print("ERROR");
		}
}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent e) {
		if(e.getPlayer().isBanned()) {
			e.disallow(PlayerLoginEvent.Result.KICK_BANNED, Bukkit.getBanList(Type.NAME).getBanEntry(e.getPlayer().getName()).getReason());
			// e.getPlayer().kickPlayer(Bukkit.getBanList(Type.NAME).getBanEntry(e.getPlayer().getName()).getReason());
		}
	   
	}
}
