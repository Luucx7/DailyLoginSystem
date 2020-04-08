package br.com.craftlife.creative.dailylogin.commands;

import static br.com.craftlife.creative.dailylogin.core.DatesManager.localToDate;

import java.io.File;
import java.time.LocalDate;

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
import br.com.craftlife.creative.dailylogin.visual.guis.LoginsGUI;

public class Logins implements Listener, CommandExecutor {
	
	private String pluginFolder = Main.getMain().pluginFolder;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length==0 && sender instanceof Player) {
			Player p = (Player) sender;
			sender.sendMessage(JogadorDAO.getJogador(p).getDaysSequence()+"");
			LoginsGUI.v1_15(p, JogadorDAO.getJogador(p).getDaysSequence());
		}
		else if (args[0].equalsIgnoreCase("check") && args.length>=2 && sender.hasPermission("logins.check")) {
			Player p = Bukkit.getPlayerExact(args[1]);
			if (p!=null) {
				File f = new File(pluginFolder + File.separator+"player_data"+File.separator + p.getName() + ".json"); 
				if(f.exists() && f.isFile()) {
					JSON json = new JSON();
					json.readJSON(p.getName(), File.separator+"player_data"+File.separator, p.getName());
					
					Jogador jog = new Jogador();
					
					try {
						do {
							jog = JogadorDAO.getJogador(p);
						} while(jog==null);
					} catch(NullPointerException ex) {};
					
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
		} else if (args[0].equalsIgnoreCase("date")) {
			sender.sendMessage("§5"+LocalDate.now());
		} else if (args[0].equalsIgnoreCase("ontem")) {
			sender.sendMessage("§4"+LocalDate.now().minusDays(1));
		} else if (args[0].equalsIgnoreCase("debug")) {
			Player p = (Player) sender;
			sender.sendMessage(""+(localToDate(JogadorDAO.getJogador(p).getLastLogin()).equals(localToDate(LocalDate.now().minusDays(1)))));
		}
		return false;
	}
}
