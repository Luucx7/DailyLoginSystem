package br.com.craftlife.creative.dailylogin.visual.guis;

import static br.com.craftlife.creative.dailylogin.core.Util.getString;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import br.com.craftlife.creative.dailylogin.visual.itens.Itens;

public class AboutGUI {

	public static void v1_15(Player p) {
		Inventory gui = Bukkit.getServer().createInventory(p, 36, getString("guis.header.main"));
		for (int i = 0;i<=gui.getSize()-1;i++) {
			gui.setItem(i, Itens.itens_nl(Material.BLACK_STAINED_GLASS_PANE, " ", false));
		}
		ArrayList<String> lore = new ArrayList<>();
		
		lore.add("§eA cada dia que você logar, irá");
		lore.add("§ereceber prêmios! quanto mais");
		lore.add("§edias seguidos você logar,");
		lore.add("§emelhores eles serão!");
		lore.add("§eApós completar uma semana");
		lore.add("§ea sequência recomeça e você");
		lore.add("§epoderá ganhar os prêmios denovo!");
		gui.setItem(10, Itens.itens(Material.BOOK, "§6§lSobre o Sistema", lore, false));
		lore.clear();
		
		lore.add("§aCoin é um dinheiro especial");
		lore.add("§aque você ganha ao completar");
		lore.add("§a7 dias de login. Em breve você");
		lore.add("§apoderá trocar coins por coisas");
		lore.add("§aúnicas ou especiais! ");
		gui.setItem(13, Itens.itens(Material.EMERALD, "§2§lCoins", lore, false));
		lore.clear();
		
		lore.add("§cDe vez em quando, estará");
		lore.add("§cdisponível um prêmio especial");
		lore.add("§cao completar o sétimo dia,");
		lore.add("§cvocê poderá ver na descrição!");
		lore.add("§ceste prêmio pode mudar a qualquer");
		lore.add("§cmometo, então se ver algo interessante");
		lore.add("§ccomplete a semana!");
		gui.setItem(16, Itens.itens(Material.BEACON, "§4§lPrêmio Especial", lore, false));
		lore.clear();
		
		gui.setItem(31, Itens.itens_nl(Material.BARRIER, "§c§lVoltar", false));
		
		lore.add("§8Plugin para dar prêmios aos jogadores");
		lore.add("§8que logarem todo dia no servidor.");
		lore.add(" ");
		lore.add("§8Versão: §7"+Bukkit.getServer().getPluginManager().getPlugin("DailyLoginSystem").getDescription().getVersion());
		lore.add("§8Autor: §7Luucx7");
		gui.setItem(35, Itens.itens(Material.BOOK, "§8§lSobre o Sistema", lore, false));
		lore.clear();
		
		p.openInventory(gui);
	}	
}
