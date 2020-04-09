package br.com.craftlife.creative.dailylogin.core;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;

import br.com.craftlife.creative.dailylogin.Main;
import net.md_5.bungee.api.ChatColor;

public class Util {

	private final static FileConfiguration config = Main.getMain().getConfig();

	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    @SuppressWarnings("unused")
		double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static String getString(String path) {
		String msg = null;
		msg = ChatColor.translateAlternateColorCodes('&', config.getString(path));
		return msg;
	}
	
	public static boolean gui_item(InventoryClickEvent e, String path) {
		if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(path)) {
			return true;
		}
		return false;
	}
	
	public static String cardinal(int n) {
		String dia = "";
		switch(n) {
		case 1:
			dia = "primeiro";
			break;
		case 2:
			dia = "segundo";
			break;
		case 3:
			dia = "terceiro";
			break;
		case 4:
			dia = "quarto";
			break;
		case 5:
			dia = "quinto";
			break;
		case 6:
			dia = "sexto";
			break;
		case 7:
			dia = "sétimo";
			break;
		case 8:
			dia = "oitavo";
			break;
		case 9:
			dia = "nono";
			break;
		}
		return dia;
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
