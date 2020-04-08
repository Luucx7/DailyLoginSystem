package br.com.craftlife.creative.dailylogin.core;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import br.com.craftlife.creative.dailylogin.Main;
import net.md_5.bungee.api.ChatColor;

public class MessagesManager {
	
	private final static FileConfiguration config = Main.getMain().getConfig();

	public static String getString(String path) {
		String msg = null;
		msg = config.getString(ChatColor.translateAlternateColorCodes('&', path));
		return msg;
	}
	
	public static void firstLogin(Player p) {
		p.sendMessage("§b§lSeja bem-vindo ao §a§lCraft§e§lLife§b§l!");
		p.sendMessage("§bEste é seu §cprimeiro dia §bde login!");
		p.sendMessage("§bPor logar, você recebeu §2"+config.getInt("prizes.day1.money")+" Money §be §5"+config.getInt("prizes.day1.dust")+"§5 Dusts");
		p.sendMessage("§4Logue todo dia e receba ainda mais prêmios!");
	}
	
	public static void loginMsg(Player p, int day) {
		String dia = null;
		int box = 0;
		int boxlvl = 0;
		switch(day) {
		case 1:
			dia = "primeiro";
		case 2:
			dia = "segundo";
		case 3:
			dia = "terceiro";
		case 4:
			dia = "quarto";
		case 5:
			dia = "quinto";
		case 6:
			dia = "sexto";
		case 7:
			dia = "sétimo";
		}
		box = config.getInt("prizes.day"+day+".box.qntd");
		boxlvl = config.getInt("prizes.day"+day+".box.lvl");
		p.sendMessage("§b§lSeja bem-vindo ao §a§lCraft§e§lLife§b§l!");
		p.sendMessage("§bEste é seu §c"+dia+"§b dia de login!");
		p.sendMessage("§bPor logar, você recebeu §2"+config.getInt("prizes.day1.money")+" Money §be §5"+config.getInt("prizes.day1.dust")+"§5 Dusts");
		if (box>0) {
			p.sendMessage("§bAlém de §d"+box+"§5 Caixa Misteriosa "+StarsCheck(boxlvl));
		}
		p.sendMessage("§4Logue todo dia e receba ainda mais prêmios!");
	}
	
	public static void prizeAlreadyReceived(Player p) {
		p.sendMessage("§b§lSeja bem-vindo ao §a§lCraft§e§lLife§b§l!");
		p.sendMessage("§bVocê já recebeu seu prêmio de hoje.");
		p.sendMessage("§bLogue todo dia e receba ainda mais prêmios!");
	}
	
	public static void streakFailed(Player p) {
		int box = config.getInt("prizes.day1.box.qntd");
		int boxlvl = config.getInt("prizes.day1.box.lvl");
		p.sendMessage("§b§lSeja bem-vindo ao §a§lCraft§e§lLife§b§l!");
		p.sendMessage("§cVocê ficou um tempo sem logar e perdeu sua sequência de dias :c");
		p.sendMessage("§bPor logar, você recebeu §2"+config.getInt("prizes.day1.money")+" Money §be §5"+config.getInt("prizes.day1.dust")+"§5 Dusts");
		if (box>0) {
			p.sendMessage("§bAlém de §d"+box+"§5 Caixa Misteriosa "+StarsCheck(boxlvl));
		}
		p.sendMessage("§4Logue todo dia e receba ainda mais prêmios!");
	}
	
	public static String StarsCheck(int StarCount) {
		String stars = null;
		switch (StarCount) {
		case 1:
			stars = "§e✰§7✰✰✰✰";
			break;
		case 2:
			stars = "§e✰✰§7✰✰✰";
			break;
		case 3:
			stars = "§e✰✰✰§7✰✰";
			break;
		case 4:
			stars = "§e✰✰✰✰§7✰";
			break;
		case 5:
			stars = "§e✰✰✰✰✰";
			break;
		default:
			stars = "§e✰§7✰✰✰✰";
			break;
		}
		return stars;
	}
}
