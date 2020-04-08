package br.com.craftlife.creative.dailylogin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import br.com.craftlife.creative.dailylogin.core.Util;
import br.com.craftlife.creative.dailylogin.core.model.Jogador;
import br.com.craftlife.creative.dailylogin.core.model.JogadorDAO;

public class Coins implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg, String[] args) {
		if (args.length==0 && s instanceof Player) {
			Player p = (Player) s;
			s.sendMessage("§2Você possui "+JogadorDAO.getJogador(p).getCoins()+" coin(s).");
		}
		else if (args[0].equalsIgnoreCase("set") && args.length>=2 && s.hasPermission("coins.set")) {
			Player p = Bukkit.getPlayerExact(args[1]);
			if (p!=null && Util.isNumeric(args[2])) {
				Jogador jog = JogadorDAO.getJogador(p);
				JogadorDAO.setData(p, jog.getLastLogin(), jog.getWeeksSequence(), jog.getDaysSequence(), Integer.parseInt(args[2]));
			}
		}
		else if (args[0].equalsIgnoreCase("add") && args.length>=2 && s.hasPermission("coins.add")) {
			Player p = Bukkit.getPlayerExact(args[1]);
			if (p!=null && Util.isNumeric(args[2])) {
				Jogador jog = JogadorDAO.getJogador(p);
				JogadorDAO.setData(p, jog.getLastLogin(), jog.getWeeksSequence(), jog.getDaysSequence(), jog.getCoins()+Integer.parseInt(args[2]));
			}
		}
		else if (args[0].equalsIgnoreCase("remove") && args.length>=2 && s.hasPermission("coins.remove")) {
			Player p = Bukkit.getPlayerExact(args[1]);
			if (p!=null && Util.isNumeric(args[2])) {
				Jogador jog = JogadorDAO.getJogador(p);
				
				int newcoins = jog.getCoins()-Integer.parseInt(args[2]);
				if (newcoins<0) {
					newcoins=0;
				}
				
				JogadorDAO.setData(p, jog.getLastLogin(), jog.getWeeksSequence(), jog.getDaysSequence(), newcoins);
			}
		}
		return false;
	}

}
