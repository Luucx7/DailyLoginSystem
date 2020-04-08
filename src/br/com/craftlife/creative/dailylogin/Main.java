package br.com.craftlife.creative.dailylogin;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.craftlife.creative.dailylogin.commands.Coins;
import br.com.craftlife.creative.dailylogin.commands.Logins;
import br.com.craftlife.creative.dailylogin.core.model.Jogador;
import br.com.craftlife.creative.dailylogin.events.Login;
import br.com.craftlife.creative.dailylogin.visual.events.LoginsEvents;
import br.com.craftlife.creative.dailylogin.Main;

public class Main extends JavaPlugin implements Listener {
	
	public static HashMap<String, Jogador> jogadores = new HashMap<>();
	
	public String pluginFolder = getDataFolder().getAbsolutePath();
	
	public void onEnable() {
		
		// Configuration file
		saveDefaultConfig();
		
		// Commands
		this.getCommand("logins").setExecutor(new Logins());
		this.getCommand("coins").setExecutor(new Coins());
		
		// Events
		this.getServer().getPluginManager().registerEvents(new Login(), this);
		this.getServer().getPluginManager().registerEvents(new LoginsEvents(), this);
	}
	
	public static Main getMain() {
		return (Main) Bukkit.getPluginManager().getPlugin("DailyLoginSystem");
	}
}
