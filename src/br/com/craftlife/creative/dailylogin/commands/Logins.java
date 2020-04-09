package br.com.craftlife.creative.dailylogin.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import br.com.craftlife.creative.dailylogin.core.json.JSON;
import br.com.craftlife.creative.dailylogin.core.model.JogadorDAO;
import br.com.craftlife.creative.dailylogin.visual.guis.LoginsGUI;

public class Logins implements Listener, CommandExecutor {
		
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length==0 && sender instanceof Player) {
			Player p = (Player) sender;
			LoginsGUI.v1_15(p, JogadorDAO.getJogador(p).getDaysSequence());
		}
		else if (args[0].equalsIgnoreCase("check") && args.length>=2 && sender.hasPermission("logins.check")) {
			Player p = Bukkit.getPlayerExact(args[1]);
			if (p!=null) {
				JSON json = new JSON();
				json.readJSON(p.getName(), File.separator+"player_data"+File.separator, p.getName(), p, false, sender);
			} else {
				sender.sendMessage("§cJogador não encontrado.");
			}
		}
		return false;
	}
}
