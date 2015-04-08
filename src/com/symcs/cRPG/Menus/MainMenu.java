package com.symcs.cRPG.Menus;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.MenuItems.*;

import net.md_5.bungee.api.ChatColor;
import ninja.amp.ampmenus.items.CloseItem;
import ninja.amp.ampmenus.items.SubMenuItem;
import ninja.amp.ampmenus.menus.ItemMenu;

public class MainMenu extends ItemMenu{

	public MainMenu(cRPG plugin) {
		super("Main Menu", Size.SIX_LINE, plugin);
		
		
		setItem(0, new SubMenuItem(plugin, ChatColor.GOLD + "Party management", 
				new ItemStack(Material.SKULL_ITEM), 
				new PartyMenu(plugin), 
				"Manage your party"));
		
		setItem(1, new LevelUpItem(plugin));
		
		setItem(9, new SubMenuItem(plugin, "Select class", 
									new ItemStack(Material.BOOK_AND_QUILL), 
									new ClassSelectionMenu(plugin), 
									"Select a new class"));
		

		
		
		//SubMenuItem(JavaPlugin plugin, String displayName, ItemStack icon, ItemMenu menu, String... lore)
		setItem(53, new CloseItem());
	}

}
