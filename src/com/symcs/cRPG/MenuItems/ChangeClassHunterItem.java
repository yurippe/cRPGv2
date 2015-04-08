package com.symcs.cRPG.MenuItems;

import ninja.amp.ampmenus.events.ItemClickEvent;
import ninja.amp.ampmenus.items.MenuItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;

public class ChangeClassHunterItem extends MenuItem{

	private static final String DISPLAY_NAME = ChatColor.GOLD + "Hunter";
	private static final ItemStack ICON = new ItemStack(Material.BOW);
	private static final String[] LORE = {"Change your active class", "and become a Hunter"};
	private cRPG plugin;

	public ChangeClassHunterItem(cRPG plugin) {
		super(DISPLAY_NAME, ICON, LORE);
		this.plugin = plugin;
	}
	
	@Override
	public void onItemClick(ItemClickEvent event){
		PlayerData PD = plugin.getPlayerManager().getPlayer(event.getPlayer());
		if(PD.getPlayerClass().getClassName() == "Hunter"){
			event.setWillUpdate(true);
		}
		else
		{
			PD.setPlayerClass("Hunter");
			event.setWillClose(true);
		}
	}
	
}
