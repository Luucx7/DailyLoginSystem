package br.com.craftlife.creative.dailylogin.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import br.com.craftlife.creative.dailylogin.Main;
import br.com.craftlife.creative.dailylogin.core.json.JSON;
import br.com.craftlife.creative.dailylogin.core.model.Jogador;
import br.com.craftlife.creative.dailylogin.core.model.JogadorDAO;

public class Logins implements Listener, CommandExecutor {
	
	private String pluginFolder = Main.getMain().pluginFolder;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length==0) {
			// TODO: Login Streak GUI
		}
		else if (args[0].equalsIgnoreCase("check") && args.length>=2 && sender.hasPermission("logins.check")) {
			Player p = Bukkit.getPlayerExact(args[0]);
			if (p!=null) {
				File f = new File(pluginFolder + File.separator+"player_data"+File.separator + p.getName() + ".json"); 
				if(f.exists() && f.isFile()) {
					JSON json = new JSON();
					json.readJSON(p.getName(), File.separator+"player_data"+File.separator, p.getName());
					
					Jogador jog = new Jogador();
					
					do {
						jog = JogadorDAO.getJogador(p);
					} while(jog==null);
					
					sender.sendMessage("§b§l§m-------------§r  §8§lSistema de Login  §b§m-------------");
					sender.sendMessage("");
					sender.sendMessage("§bJogador Verificado: §e"+p.getName());
					sender.sendMessage("");
					sender.sendMessage("§bDias: §e"+jog.getDaysSequence());
					sender.sendMessage("§bSemanas: §e"+jog.getWeeksSequence());
					sender.sendMessage("§bÚltimo Login: §e"+jog.getLastLogin());
					sender.sendMessage("");
					sender.sendMessage("§bCoins: §e"+jog.getCoins());
					sender.sendMessage("");
					sender.sendMessage("§b§l§m-----------------------------------------");
				} else {
					sender.sendMessage("§cArquivo não encontrado.");
				}
			} else {
				sender.sendMessage("§cJogador não encontrado.");
			}
		}
		else if (args[0].equalsIgnoreCase("set") && args.length>=2 && sender.hasPermission("logins.set")) {
			
		}
		return false;
	}
}
