package br.com.craftlife.creative.dailylogin.core;

import org.bukkit.configuration.file.FileConfiguration;

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
		msg = config.getString(ChatColor.translateAlternateColorCodes('&', path));
		return msg;
	}
	
	public static String cardinal(int n) {
		String dia = "";
		switch(n) {
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
		case 8:
			dia = "oitavo";
		case 9:
			dia = "nono";
		case 10:
			dia = "décimo";
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
