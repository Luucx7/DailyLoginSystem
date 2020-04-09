package br.com.craftlife.creative.dailylogin;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.craftlife.creative.dailylogin.commands.Coins;
import br.com.craftlife.creative.dailylogin.commands.Logins;
import br.com.craftlife.creative.dailylogin.core.model.Jogador;
import br.com.craftlife.creative.dailylogin.events.Login;
import br.com.craftlife.creative.dailylogin.visual.events.LoginsEvents;
import net.milkbowl.vault.economy.Economy;
import br.com.craftlife.creative.dailylogin.Main;

public class Main extends JavaPlugin implements Listener {
	
	public static HashMap<String, Jogador> jogadores = new HashMap<>();
	
	public String pluginFolder = getDataFolder().getAbsolutePath();
	
	public static Economy econ = null;
	private static final Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		
		// Configuration file
		saveDefaultConfig();
		
		// Importing Vault
		if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		
		// Commands
		this.getCommand("logins").setExecutor(new Logins());
		this.getCommand("coins").setExecutor(new Coins());
		
		// Events
		this.getServer().getPluginManager().registerEvents(new Login(), this);
		this.getServer().getPluginManager().registerEvents(new LoginsEvents(), this);
		
		Jogador value = new Jogador();
		value.setNick("Notch");
		value.setCoins(0);
		value.setDaysSequence(0);
		value.setLastLogin(LocalDate.now());
		value.setWeeksSequence(0);
		value.setPlayer(null);
		jogadores.put("ImpossibleUser12345", value);
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	public static Main getMain() {
		return (Main) Bukkit.getPluginManager().getPlugin("DailyLoginSystem");
	}
}
