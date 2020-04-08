package br.com.craftlife.creative.dailylogin.core.model;

import java.time.LocalDate;

import org.bukkit.entity.Player;

public class Jogador {

	Player player;
	String Nick;
	int daysSequence;
	int weeksSequence;
	int coins;
	LocalDate lastLogin;
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public String getNick() {
		return Nick;
	}
	public void setNick(String nick) {
		Nick = nick;
	}
	
	public int getDaysSequence() {
		return daysSequence;
	}
	public void setDaysSequence(int daysSequence) {
		this.daysSequence = daysSequence;
	}
	
	public int getWeeksSequence() {
		return weeksSequence;
	}
	public void setWeeksSequence(int weeksSequence) {
		this.weeksSequence = weeksSequence;
	}
	
	public LocalDate getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDate lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public int getCoins() {
		return this.coins;
	}
	public void setCoins(int coins) {
		this.coins=coins;
	}
}