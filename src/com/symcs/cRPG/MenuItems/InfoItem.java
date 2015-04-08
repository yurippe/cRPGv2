package com.symcs.cRPG.MenuItems;

import java.util.Arrays;

import ninja.amp.ampmenus.events.ItemClickEvent;
import ninja.amp.ampmenus.items.MenuItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.symcs.cRPG.cRPG;

public class InfoItem extends MenuItem{

		
		private static final String DISPLAY_NAME = ChatColor.GOLD + "Player info:";
		private static final ItemStack ICON = new ItemStack(Material.BOOK_AND_QUILL);
		private static final String[] LORE = {"test line 1", "test line 2"};
		private cRPG plugin;

		public InfoItem(cRPG plugin) {
			super(DISPLAY_NAME, ICON, LORE);
			this.plugin = plugin;
		}
		
		@Override
		public void onItemClick(ItemClickEvent event){
			event.setWillUpdate(true);
		}
		
		@Override
		public ItemStack getFinalIcon(Player player){
			
			ItemStack FinalIcon = super.getFinalIcon(player);
			
			ItemMeta meta = FinalIcon.getItemMeta();
			
			String pName = player.getName().toString();
			String pClass = plugin.getPlayerManager().getPlayer(player).getPlayerClass().getClassName();
			String pLevel = Integer.toString(plugin.getPlayerManager().getPlayer(player).getPlayerClass().getLevel());
			
			meta.setLore(Arrays.asList(ChatColor.RESET + "" + ChatColor.BLUE + "Name: " + pName, 
									   ChatColor.RESET + "" + ChatColor.BLUE + "Class: " + pClass, 
									   ChatColor.RESET + "" + ChatColor.BLUE + "Level: " + pLevel));
			FinalIcon.setItemMeta(meta);
			
			return FinalIcon;
			
		}

	}
