package br.com.craftlife.creative.dailylogin.core;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import br.com.craftlife.creative.dailylogin.Main;

public class MessagesManager {
	
	private final static FileConfiguration config = Main.getMain().getConfig();
	
	public static void firstLogin(Player p) {
		p.sendMessage("§b§lSeja bem-vindo ao §a§lCraft§e§lLife§b§l!");
		p.sendMessage("§bEste é seu §cprimeiro dia §bde login!");
		p.sendMessage("§bPor logar, você recebeu §2"+config.getInt("prizes.day1.money")+" Money §be §5"+config.getInt("prizes.day1.dust")+"§5 Dusts");
		if (config.getInt("prizes.day1.box.qntd")>0) {
			p.sendMessage("§bAlém de §d"+config.getInt("prizes.day1.box.qntd")+"§5 Caixa Misteriosa "+Util.StarsCheck(config.getInt("prizes.day1.box.lvl")));
		}
		p.sendMessage("§bLogue todo dia e receba ainda mais prêmios!");
	}
	
	public static void loginMsg(Player p, int day) {
		String dia = null;
		int box = 0;
		int boxlvl = 0;
		dia = Util.cardinal(day);
		box = config.getInt("prizes.day"+day+".box.qntd");
		boxlvl = config.getInt("prizes.day"+day+".box.lvl");
		p.sendMessage("§b§lSeja bem-vindo ao §a§lCraft§e§lLife§b§l!");
		p.sendMessage("§bEste é seu §c"+dia+"§b dia de login!");
		p.sendMessage("§bPor logar, você recebeu §2"+config.getInt("prizes.day1.money")+" Money §be §5"+config.getInt("prizes.day1.dust")+"§5 Dusts");
		if (box>0) {
			p.sendMessage("§bAlém de §d"+box+"§5 Caixa Misteriosa "+Util.StarsCheck(boxlvl));
		}
		if (day>=7) {
			p.sendMessage("§bPor completar uma semana, você ganhou §2§n1 Coin");
		}
		p.sendMessage("§bLogue todo dia e receba ainda mais prêmios!");
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
		p.sendMessage("§cVocê ficou um tempo sem logar e");
		p.sendMessage("§cperdeu sua sequência de dias :c");
		p.sendMessage("§bPor logar, você recebeu §2"+config.getInt("prizes.day1.money")+" Money §be §5"+config.getInt("prizes.day1.dust")+"§5 Dusts");
		if (box>0) {
			p.sendMessage("§bAlém de §d"+box+"§5 Caixa Misteriosa "+Util.StarsCheck(boxlvl));
		}
		p.sendMessage("§bLogue todo dia e receba ainda mais prêmios!");
	}
}
