package br.com.craftlife.creative.dailylogin.events;

import java.io.File;

//import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

//import br.com.craftlife.creative.dailylogin.Main;
import br.com.craftlife.creative.dailylogin.core.json.JSON;

public class Login implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent ev) {
		try {
			if (ev.getPlayer().hasPermission("logins.receive")) {
				JSON json = new JSON();
				json.readJSON(ev.getPlayer().getName(), File.separator+"player_data"+File.separator, ev.getPlayer().getName(), ev.getPlayer(), true, null);
			}
		}catch(Exception e){
			e.printStackTrace();
			ev.getPlayer().sendMessage("§4§lERRO! §7Algo grave aconteceu ao logar e seu prêmio não pode ser dado. Contate o diretor.");
		};

	}
}
