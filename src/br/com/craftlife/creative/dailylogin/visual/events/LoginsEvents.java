package br.com.craftlife.creative.dailylogin.visual.events;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import br.com.craftlife.creative.dailylogin.Main;
import br.com.craftlife.creative.dailylogin.core.Util;
import br.com.craftlife.creative.dailylogin.core.model.JogadorDAO;
import br.com.craftlife.creative.dailylogin.visual.guis.AboutGUI;
import br.com.craftlife.creative.dailylogin.visual.guis.LoginsGUI;

import static br.com.craftlife.creative.dailylogin.core.Util.gui_item;
import static br.com.craftlife.creative.dailylogin.core.Util.getString;

@SuppressWarnings("unused")
public class LoginsEvents implements Listener {
	
	private final FileConfiguration config = Main.getMain().getConfig();
	
	@EventHandler
	private void inventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		String i = e.getView().getTitle();
		
		if (i.equalsIgnoreCase(Util.getString("guis.header.main"))) {
			
			e.setCancelled(true);
			
			// Debug
			if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType().equals(Material.AIR))) {
                return;
            }
			
			// Itens
			if (gui_item(e, getString("guis.itens.about"))) {
				AboutGUI.v1_15(p);
			}
			if (gui_item(e, "§c§lVoltar")) {
				LoginsGUI.v1_15(p, JogadorDAO.getJogador(p).getDaysSequence());
			}
		}
	}
}
