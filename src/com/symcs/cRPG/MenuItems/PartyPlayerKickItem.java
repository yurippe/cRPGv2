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
import com.symcs.cRPG.utils.Party;

public class PartyPlayerKickItem extends MenuItem{

	
	private static final String DISPLAY_NAME = ChatColor.GOLD + "Party Member:";
	private static final ItemStack ICON = new ItemStack(Material.REDSTONE_BLOCK);
	private static final String[] LORE = {"test line 1", "test line 2"};
	private cRPG plugin;
	private int index;
	private Player WillKick;

	public PartyPlayerKickItem(cRPG plugin, int index) {
		super(DISPLAY_NAME, ICON, LORE);
		this.plugin = plugin;
		this.index = index;
	}
	
	@Override
	public void onItemClick(ItemClickEvent event){
		this.plugin.getPartyManager().KickPlayer(WillKick, event.getPlayer());
		event.setWillUpdate(true);
	}
	
	@Override
	public ItemStack getFinalIcon(Player player){
		ItemStack finalicon = super.getFinalIcon(player);
		Player p;
		Party party = plugin.getPartyManager().getParty(player);
		if(party == null){return new ItemStack(Material.AIR);}else{p = player;}
		if(!(player == party.getLeader())){return new ItemStack(Material.AIR);}
		else{List<Player> members = party.getMembers();
		if(index < members.size()){p = members.get(index);}else{return new ItemStack(Material.AIR);}}
		
		if(party.getLeader() == p){
		finalicon = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta fm = finalicon.getItemMeta();
		fm.setLore(Arrays.asList(ChatColor.RED + p.getName(), "", ChatColor.GRAY + "Disbands this party"));
		fm.setDisplayName(ChatColor.RED + "Disband party!");
		finalicon.setItemMeta(fm);
		}else{
		
		ItemMeta fm = finalicon.getItemMeta();
		fm.setLore(Arrays.asList(ChatColor.RED + p.getName(), "", ChatColor.GRAY + "Kick this player from the party"));
		fm.setDisplayName(ChatColor.RED + "Kick:");
		finalicon.setItemMeta(fm);
		}
		
		this.WillKick = p;
		
		return finalicon;
	}

}
