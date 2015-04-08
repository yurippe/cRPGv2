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

public class PartyPlayerLeaveItem  extends MenuItem{

	
	private static final String DISPLAY_NAME = ChatColor.RED + "Leave party";
	private static final ItemStack ICON = new ItemStack(Material.REDSTONE_BLOCK);
	private cRPG plugin;

	public PartyPlayerLeaveItem(cRPG plugin) {
		super(DISPLAY_NAME, ICON);
		this.plugin = plugin;
	}
	
	@Override
	public void onItemClick(ItemClickEvent event){
		this.plugin.getPartyManager().LeaveParty(event.getPlayer());
		event.setWillUpdate(true);
	}
	
	@Override
	public ItemStack getFinalIcon(Player player){
		ItemStack icon = super.getFinalIcon(player);
		if(this.plugin.getPartyManager().isLeader(player)){
			ItemMeta mi = icon.getItemMeta();
			mi.setDisplayName(ChatColor.RED + "Disband Party!");
			mi.setLore(Arrays.asList("", ChatColor.RED + "WARNING:", ChatColor.RED + "If you leave, the party", ChatColor.RED + "will be disbanded!"));
			icon.setItemMeta(mi);
		}
		return icon;
	}

}
