package com.symcs.cRPG.Managers;

import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Menus.MainMenu;

public class MenuManager {
		
		@SuppressWarnings("unused")
		private cRPG plugin;
		private MainMenu mainMenu;
		
		public MenuManager(cRPG plugin){
			this.plugin = plugin;
			this.mainMenu = new MainMenu(plugin);
		}
		
		public void openMainMenu(Player player){
			this.mainMenu.open(player);
		}
}
