package com.symcs.cRPG.MenuItems;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.utils.Party;

import ninja.amp.ampmenus.events.ItemClickEvent;
import ninja.amp.ampmenus.items.MenuItem;

public class TestItem extends MenuItem {
	
	private static final String DISPLAY_NAME = ChatColor.GREEN + "Test me!";
	private static final ItemStack ICON = new ItemStack(Material.DIAMOND);
	private static final String[] LORE = {"test line 1", "test line 2"};
	private cRPG plugin;

	public TestItem(cRPG plugin) {
		super(DISPLAY_NAME, ICON, LORE);
		this.plugin = plugin;
	}
	
	@Override
	public void onItemClick(ItemClickEvent event){
		
		this.plugin.getLogger().info("clicked testitem");

		Party party = this.plugin.getPartyManager().getParty(event.getPlayer());
		String players = "";
		if(!(party == null)){
			for(Player p: party.getPlayers()){
				players = players + p.getName() + " ";
			}
			event.getPlayer().sendMessage(players);
			}
		
			
		}
		
	
	@Override
	public ItemStack getFinalIcon(Player player){
		
		ItemStack FinalIcon = super.getFinalIcon(player);
		
		ItemMeta meta = FinalIcon.getItemMeta();
		meta.setLore(Arrays.asList("OP " + player.getName().toString()));
		FinalIcon.setItemMeta(meta);
		
		return FinalIcon;
		
	}

}
