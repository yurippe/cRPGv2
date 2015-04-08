package com.symcs.cRPG.MenuItems;

import java.util.Arrays;
import java.util.List;

import ninja.amp.ampmenus.events.ItemClickEvent;
import ninja.amp.ampmenus.items.MenuItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;
import com.symcs.cRPG.utils.Party;

public class PartyPlayerInfoItem extends MenuItem{

	
	private static final String DISPLAY_NAME = ChatColor.GOLD + "Party Member:";
	private static final ItemStack ICON = new ItemStack(Material.SKULL_ITEM);
	private static final String[] LORE = {"test line 1", "test line 2"};
	private cRPG plugin;
	private int index;

	public PartyPlayerInfoItem(cRPG plugin, int index) {
		super(DISPLAY_NAME, ICON, LORE);
		this.plugin = plugin;
		this.index = index;
	}
	
	@Override
	public void onItemClick(ItemClickEvent event){
		event.setWillUpdate(true);
	}
	
	@Override
	public ItemStack getFinalIcon(Player player){
		ItemStack finalicon = super.getFinalIcon(player);
		Player p;
		Party party = plugin.getPartyManager().getParty(player);
		if(party == null){if(!(index == 0)){return new ItemStack(Material.AIR);}else{p = player;}}
		else{List<Player> members = party.getMembers();
		if(index < members.size()){p = members.get(index);}else{return new ItemStack(Material.AIR);}}
		
		String displayname = ChatColor.GREEN + "Party Member:";
		if(!(party == null)){if(party.getLeader() == p){displayname = ChatColor.GOLD + "Party Leader:";}}
		else{displayname = ChatColor.GOLD + "You are not in a party";}
		
		
		ItemMeta fm = finalicon.getItemMeta();
		PlayerData pd = plugin.getPlayerManager().getPlayer(p);
		fm.setLore(Arrays.asList(ChatColor.GRAY + p.getName(), 
								 ChatColor.GRAY + "Class: " + pd.getPlayerClass().getClassName(), 
								 ChatColor.GRAY + "Level: " + Integer.toString(pd.getPlayerClass().getLevel())
								 ));
		
		fm.setDisplayName(displayname);
		finalicon.setItemMeta(fm);
		
		return finalicon;
	}

}
