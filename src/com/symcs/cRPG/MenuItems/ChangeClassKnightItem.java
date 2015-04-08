package com.symcs.cRPG.MenuItems;

import ninja.amp.ampmenus.events.ItemClickEvent;
import ninja.amp.ampmenus.items.MenuItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;

public class ChangeClassKnightItem extends MenuItem{

	private static final String DISPLAY_NAME = ChatColor.GOLD + "Knight";
	private static final ItemStack ICON = new ItemStack(Material.IRON_SWORD);
	private static final String[] LORE = {"Change your active class", "and become a Knight"};
	private cRPG plugin;

	public ChangeClassKnightItem(cRPG plugin) {
		super(DISPLAY_NAME, ICON, LORE);
		this.plugin = plugin;
	}
	
	@Override
	public void onItemClick(ItemClickEvent event){
		PlayerData PD = plugin.getPlayerManager().getPlayer(event.getPlayer());
		if(PD.getPlayerClass().getClassName() == "Knight"){
			event.setWillUpdate(true);
		}
		else
		{
			PD.setPlayerClass("Knight");
			event.setWillClose(true);
		}
	}
	
}