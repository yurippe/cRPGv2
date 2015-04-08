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

public class LevelUpItem extends MenuItem{

		
		private static final String DISPLAY_NAME = ChatColor.GOLD + "Level up:";
		private static final ItemStack ICON = new ItemStack(Material.BOOK_AND_QUILL);
		private static final String[] LORE = {"test line 1", "test line 2"};
		private cRPG plugin;

		public LevelUpItem(cRPG plugin) {
			super(DISPLAY_NAME, ICON, LORE);
			this.plugin = plugin;
		}
		
		@Override
		public void onItemClick(ItemClickEvent event){
			plugin.getPlayerManager().getPlayer(event.getPlayer()).getPlayerClass().levelUp();
			event.setWillUpdate(true);
		}
		
		@Override
		public ItemStack getFinalIcon(Player player){
			
			ItemStack FinalIcon = super.getFinalIcon(player);
			
			ItemMeta meta = FinalIcon.getItemMeta();
			
			String pClass = plugin.getPlayerManager().getPlayer(player).getPlayerClass().getClassName();
			String pLevel = Integer.toString(plugin.getPlayerManager().getPlayer(player).getPlayerClass().getLevel());
			
			meta.setLore(Arrays.asList(ChatColor.RESET + "" + ChatColor.DARK_PURPLE + "Spend your xp to level up this class", 
									   ChatColor.RESET + "" + ChatColor.BLUE + "Current Class: " + pClass, 
									   ChatColor.RESET + "" + ChatColor.BLUE + "Current Level: " + pLevel));
			FinalIcon.setItemMeta(meta);
			
			return FinalIcon;
			
		}

	}