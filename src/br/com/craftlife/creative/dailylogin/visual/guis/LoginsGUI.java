package br.com.craftlife.creative.dailylogin.visual.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import br.com.craftlife.creative.dailylogin.Main;
import br.com.craftlife.creative.dailylogin.core.Util;
import br.com.craftlife.creative.dailylogin.visual.itens.Itens;

import static br.com.craftlife.creative.dailylogin.core.Util.getString;
import static br.com.craftlife.creative.dailylogin.core.Util.cardinal;

import java.util.ArrayList;

public class LoginsGUI {

	private final static FileConfiguration config = Main.getMain().getConfig();

	public static void v1_15(Player p, int day) {
		Inventory gui = Bukkit.getServer().createInventory(p, 36, getString("guis.header.main"));
		for (int i = 0;i<=gui.getSize()-1;i++) {
			gui.setItem(i, Itens.itens_nl(Material.BLACK_STAINED_GLASS_PANE, " ", false));
		}
		gui.setItem(10, Itens.itens(Material.EMERALD_BLOCK, getString("guis.itens.day1"), loreCreator(1, true), true));
		gui.setItem(11, Itens.itens(getBlock(day, 2), getString("guis.itens.day2"), loreCreator(2, day>=2 ? true : false), true));
		gui.setItem(12, Itens.itens(getBlock(day, 3), getString("guis.itens.day3"), loreCreator(3, day>=3 ? true : false), true));
		gui.setItem(13, Itens.itens(getBlock(day, 4), getString("guis.itens.day4"), loreCreator(4, day>=4 ? true : false), true));
		gui.setItem(14, Itens.itens(getBlock(day, 5), getString("guis.itens.day5"), loreCreator(5, day>=5 ? true : false), true));
		gui.setItem(15, Itens.itens(getBlock(day, 6), getString("guis.itens.day6"), loreCreator(6, day>=6 ? true : false), true));
		gui.setItem(16, Itens.itens(day>=7 ? Material.ENDER_EYE : Material.ENDER_PEARL, getString("guis.itens.day7"), loreSeven(day>=7 ? true : false), true));

		ArrayList<String> lore = new ArrayList<>();
		lore.add("§5A cada semana completa");
		lore.add("§5você irá ganhar 1 coin.");
		lore.add("§5Em breve, você poderá comprar");
		lore.add("§5coisas únicas com coins!");
		lore.add(" ");
		lore.add("§aVeja seus coins com /coins");
		gui.setItem(27, Itens.itens(Material.NETHER_STAR, getString("guis.itens.super"), lore, true));
		lore.clear();

		gui.setItem(35, Itens.itens_nl(Material.BOOK, getString("guis.itens.about"), true));
		p.openInventory(gui);
	}

	public static ArrayList<String> loreCreator(int day, boolean received) {
		ArrayList<String> lore = new ArrayList<>();
		if (received) {
			lore.add("§5Este é seu "+cardinal(day)+" dia de login!");
			if (!(config.getInt("prizes.day"+day+".box.qntd")==0)) {
				lore.add("§5Além de §r§o§2"+config.getInt("prizes.day"+day+".money")+"$ §r§5e §c§o"+config.getInt("prizes.day"+day+".dust")+" Dusts");
				lore.add("§5Você também ganhou ");
				lore.add("§5§o"+config.getInt("prizes.day"+day+".box.qntd")+" Caixa Misteriosa "+Util.StarsCheck(config.getInt("prizes.day"+day+".box.lvl")));
			} else {
				lore.add("§5Você ganhou §r§2§o"+config.getInt("prizes.day"+day+".money")+"$ §r§5e §c§o"+config.getInt("prizes.day"+day+".dust")+"§5 Dusts");
			}
		} else {
			lore.add("§5No seu "+cardinal(day)+" dia você ganhará:");
			if (!(config.getInt("prizes.day"+day+".box.qntd")==0)) {
				lore.add("§2§o"+config.getInt("prizes.day"+day+".money")+"$§r§5, §c§o"+config.getInt("prizes.day"+day+".dust")+" Dusts §r§5e");
				lore.add("§5§o"+config.getInt("prizes.day"+day+".box.qntd")+" Caixa Misteriosa "+Util.StarsCheck(config.getInt("prizes.day"+day+".box.lvl")));
			} else {
				lore.add("§2§o"+config.getInt("prizes.day"+day+".money")+"$§r§5 e §c§o"+config.getInt("prizes.day"+day+".dust")+" Dusts");
			}
		}
		return lore;
	}

	public static ArrayList<String> loreSeven(boolean received) {
		ArrayList<String> lore = new ArrayList<>();
		if (received) {
			lore.add("§5Você completou uma semana de login!");
			lore.add("§5Como prêmio especial você ganhou:");
			lore.add(" ");
			lore.add("§2§o"+config.getInt("prizes.day7.money")+" Money");
			lore.add("§c§o"+config.getInt("prizes.day7.dust")+" Dusts");
			if (config.getInt("prizes.day7.box.qntd")>0) {
				lore.add("§5§o"+config.getInt("prizes.day7.box.qntd")+" Caixas Misteriosas "+Util.StarsCheck(config.getInt("prizes.day7.box.lvl")));
				if (config.getBoolean("prizes.day7.special.enable")) {
					lore.add(Util.getString("prizes.day7.special.text"));
				}
			}
		} else {
			lore.add("§5No seu sétimo dia você ganhará:");
			lore.add(" ");
			lore.add("§2§o"+config.getInt("prizes.day7.money")+" Money");
			lore.add("§c§o"+config.getInt("prizes.day7.dust")+" Dusts");
			if (config.getInt("prizes.day7.box.qntd")>0) {
				lore.add("§5§o"+config.getInt("prizes.day7.box.qntd")+" Caixas Misteriosas "+Util.StarsCheck(config.getInt("prizes.day7.box.lvl")));
				if (config.getBoolean("prizes.day7.special.enable")) {
					lore.add("§5§oAlém do prêmio especial!");
				}
			}
		}
		return lore;
	}

	public static Material getBlock(int day, int req) {
		if (day>=req) {
			return Material.EMERALD_BLOCK;
		} else {
			return Material.REDSTONE_BLOCK;
		}
	}
}
