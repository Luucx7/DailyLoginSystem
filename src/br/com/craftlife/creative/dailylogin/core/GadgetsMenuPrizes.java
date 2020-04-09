package br.com.craftlife.creative.dailylogin.core;

import org.bukkit.entity.Player;

import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.utils.mysteryboxes.MysteryBoxType;

public class GadgetsMenuPrizes {
	public static void give(Player p, int dust, int boxQntd, int boxLvl) {
		if (dust>0) {
			GadgetsMenuAPI.getPlayerManager(p).addMysteryDust(dust);
		}
		if (boxQntd>0) {

			MysteryBoxType lvl = null;
			switch(boxLvl) {
			case 1:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_1;
				break;
			case 2:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_2;
				break;
			case 3:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_3;
				break;
			case 4:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_4;
				break;
			case 5:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_5;
				break;
			default:
				lvl=MysteryBoxType.NORMAL_MYSTERY_BOX_1;
				break;
			}
			GadgetsMenuAPI.getPlayerManager(p).giveMysteryBoxes(lvl, null, false, null, boxQntd);
		}
	}
}
