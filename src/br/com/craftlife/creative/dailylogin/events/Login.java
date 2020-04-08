package br.com.craftlife.creative.dailylogin.events;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.craftlife.creative.dailylogin.Main;
import br.com.craftlife.creative.dailylogin.core.MessagesManager;
import br.com.craftlife.creative.dailylogin.core.json.JSON;
import br.com.craftlife.creative.dailylogin.core.model.Jogador;
import br.com.craftlife.creative.dailylogin.core.model.JogadorDAO;

public class Login implements Listener {
	
	private final static FileConfiguration config = Main.getMain().getConfig();
	private String pluginFolder = Main.getMain().pluginFolder;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent ev) {
		// Temporary
		if (ev.getPlayer().hasPermission("logins.receive")) {
		
		File f = new File(pluginFolder + File.separator+"player_data"+File.separator + ev.getPlayer().getName() + ".json"); 
		if(f.exists() && f.isFile()) {
			JSON json = new JSON();
			json.readJSON(ev.getPlayer().getName(), File.separator+"player_data"+File.separator, ev.getPlayer().getName());
			
			Jogador jog = new Jogador();
			
			do {
				jog = JogadorDAO.getJogador(ev.getPlayer());
			} while(jog==null);
			
			JogadorDAO.checkDates(JogadorDAO.getJogador(ev.getPlayer()));
			
			MessagesManager.loginMsg(jog.getPlayer(), jog.getDaysSequence());
		} else {
			JogadorDAO.setFirstData(ev.getPlayer());
			JogadorDAO.givePrizes(ev.getPlayer(), config.getInt("prizes.day1.money"), config.getInt("prizes.day1.dust"), config.getInt("prizes.day1.box.qntd"), config.getInt("prizes.day1.box.lvl"));
			MessagesManager.firstLogin(ev.getPlayer());
		}
	}
	// Temporary
	}
}
