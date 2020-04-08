package br.com.craftlife.creative.dailylogin.core.model;

import static br.com.craftlife.creative.dailylogin.core.DatesManager.localToDate;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.time.DateUtils;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.utils.mysteryboxes.MysteryBoxType;

import br.com.craftlife.creative.dailylogin.Main;
import br.com.craftlife.creative.dailylogin.core.MessagesManager;
import br.com.craftlife.creative.dailylogin.core.json.JSON;

@SuppressWarnings("unchecked")
public class JogadorDAO {
	
	private final static FileConfiguration config = Main.getMain().getConfig();

	// Setting player data on file
	
	// The data set when the player doesn't have a file 
	public static void setFirstData(Player player) {
		JSON json = new JSON();
		JSONObject obj = new JSONObject();
		obj.put("lastLogin", LocalDate.now()+"");
		obj.put("weeks", 0+"");
		obj.put("days", 1+"");
		obj.put("coins", 0);
		json.writeJSON(player.getName(), File.separator+"player_data"+File.separator, player.getName(), obj.toJSONString());
		
		Jogador jogador = new Jogador();
		jogador.setLastLogin(LocalDate.now());
		jogador.setWeeksSequence(0);
		jogador.setDaysSequence(1);
		jogador.setNick(player.getName());
		jogador.setPlayer(player);
		
		Main.jogadores.put(player.getName(), jogador);
	}
	
	// Method to set any new data to player
	public static void setData(Player player, LocalDate lastLogin, int weeks, int days, int coins) {
		JSON json = new JSON();
		JSONObject obj = new JSONObject();
		Jogador jog = new Jogador();
		
		obj.put("lastLogin", lastLogin);
		obj.put("weeks", weeks);
		obj.put("days", days);
		obj.put("coins", coins);
		json.writeJSON(player.getName(), File.separator+"player_data"+File.separator, player.getName(), obj.toJSONString());
		
		jog = getJogador(player);
		jog.setDaysSequence(days);
		jog.setLastLogin(lastLogin);
		jog.setWeeksSequence(weeks);
		
		Main.jogadores.put(player.getName(), jog);
	}
	
	// Simple method to reset the player data
	public static void resetStreak(Jogador jog) {
		setData(jog.getPlayer(), LocalDate.now(), 0, 1, jog.getCoins());
	}
	
	// Simple method to start a new week
	public static void restartWeek(Jogador jog, int weeks) {
		setData(jog.getPlayer(), LocalDate.now(), weeks+1, 1, jog.getCoins());
	}
	
	// Getting player data of files
	public static void getJogadorData(String player, Map<String, String> map) {
		Jogador jog = new Jogador();
		
		jog.setDaysSequence(Integer.parseInt(map.get("weeks")));
		jog.setDaysSequence(Integer.parseInt(map.get("days")));
		jog.setLastLogin(LocalDate.parse(map.get("lastLogin")));
		jog.setCoins(Integer.parseInt(map.get("coins")));
		
		Main.jogadores.put(player, jog);
	}
	
	// Getting player data from memory
	public static Jogador getJogador(Player player) {
		Jogador jogador = new Jogador();
		jogador = Main.jogadores.get(player.getName());
		if (jogador.getPlayer()==null) {
			jogador.setPlayer(player);
			Main.jogadores.put(player.getName(), jogador);
		}
		return jogador;
	}
	
	// Dealing with player data
	
	// Check player last login
	public static void checkDates(Jogador jog) {
		
		// Check if player log in today
		if (DateUtils.isSameDay(localToDate(jog.getLastLogin()), localToDate(LocalDate.now()))) {
			MessagesManager.prizeAlreadyReceived(jog.getPlayer());
		}
		
		// Check if player logged in yesterday
		else if (DateUtils.isSameDay(localToDate(jog.getLastLogin()), localToDate(LocalDate.now().minusDays(1)))) {
			int newdays = jog.getDaysSequence()+1;
			int weeks = jog.getWeeksSequence();
			int coins = jog.getCoins();
			switch(newdays) {
			case 8:
				calcPrizes(jog, 1);
				jog.setDaysSequence(1);
				jog.setWeeksSequence(weeks+1);
				jog.setCoins(coins+1);
		    default:
		    	calcPrizes(jog, newdays);
				jog.setDaysSequence(newdays);
			}
			jog.setLastLogin(LocalDate.now());
			
			setData(jog.getPlayer(), jog.getLastLogin(), jog.getWeeksSequence(), jog.getDaysSequence(), jog.getCoins());
		} 
		
		// If not, reset
		else {
			resetStreak(jog);
			JogadorDAO.givePrizes(jog.getPlayer(), config.getInt("prizes.day1.money"), config.getInt("prizes.day1.dust"), config.getInt("prizes.day1.box.qntd"), config.getInt("prizes.day1.box.lvl"));
			MessagesManager.streakFailed(jog.getPlayer());
		}
	}
	
	// Calculate the prizes to give
	public static void calcPrizes(Jogador jog, int days) {
		givePrizes(jog.getPlayer(), config.getInt("prizes.day"+days+".money"), config.getInt("prizes.day"+days+"dust"), config.getInt("prizes.day"+days+".box.qntd"), config.getInt("prizes.day"+days+".box.lvl"));
		if (days>=7 & config.getBoolean("prizes.day7.special.enable")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), config.getString("prizes.day7.special.command"));
		}
	}
	
	// Prizes method
	public static void givePrizes(Player p, int money, int dust, int boxQntd, int boxLvl) {
		if (money>0) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money give "+p.getName()+" "+money);
		} 
		if (dust>0) {
			GadgetsMenuAPI.getPlayerManager(p).addMysteryDust(dust);
		}
		if (boxQntd>0) {
			
			MysteryBoxType lvl = null;
			switch(boxLvl) {
			case 1:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_1;
			case 2:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_2;
			case 3:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_3;
			case 4:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_4;
			case 5:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_5;
			default:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_1;
			}
			
			GadgetsMenuAPI.getPlayerManager(p).giveMysteryBoxes(lvl, null, false, null, boxQntd);
		}
	}
}
