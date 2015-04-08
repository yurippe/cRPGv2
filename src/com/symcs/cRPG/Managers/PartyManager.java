package com.symcs.cRPG.Managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.utils.NMSFunctions;
import com.symcs.cRPG.utils.Party;

public class PartyManager {
	
	@SuppressWarnings("unused")
	private cRPG plugin;

	public HashMap<Player, Party> playersParty = new HashMap<Player, Party>();
	public HashMap<Player, Player> pendingInvites = new HashMap<Player, Player>();
	
	private List<ItemStack> inviteItems = new ArrayList<ItemStack>();
	private List<String> inviteCommands = Arrays.asList("/partyinviteaccept", "/partyinvitedecline");
 
	
	public PartyManager(cRPG plugin){
		this.plugin = plugin;
		
		ItemStack y = new ItemStack(Material.DIAMOND);
		ItemStack n = new ItemStack(Material.EMERALD);
		ItemMeta ym = y.getItemMeta();
		ItemMeta nm = y.getItemMeta();
		ym.setLore(Arrays.asList("Click to accept the party invite"));
		ym.setDisplayName(ChatColor.GREEN + "Accept");
		nm.setLore(Arrays.asList("Click to decline the party invite"));
		nm.setDisplayName(ChatColor.RED + "Decline");
		y.setItemMeta(ym);
		n.setItemMeta(nm);
		inviteItems.add(y);
		inviteItems.add(n);
		
	}
	
	public void CreateParty(Player player){
		if(!(playersParty.containsKey(player))){
			playersParty.put(player, new Party(player));
		}
	}
	
	public void SendInvite(Player from, Player to){
		String msg = ChatColor.GOLD + "Accept invite: <item> <item>";
		to.sendMessage(ChatColor.GOLD + "-----------------------------");
		to.sendMessage(ChatColor.GOLD + from.getName() + " sent you a partyinvite.");
		NMSFunctions.sendItemInMessageWithCommand(to, inviteItems, msg, inviteCommands);
		to.sendMessage(ChatColor.GOLD + "-----------------------------");
		this.pendingInvites.put(to, from);
	}
	
	public void InvitePlayer(Player invitedFrom, Player invitedPlayer){
		if(invitedFrom == invitedPlayer){invitedFrom.sendMessage(ChatColor.RED + "You can't invite yourself!"); return;}
		if(!(this.playersParty.containsKey(invitedFrom))){CreateParty(invitedFrom);} //inviter is not in any party, create one
		if(this.playersParty.containsKey(invitedPlayer)){invitedFrom.sendMessage(ChatColor.RED + "Player is already in a party");return;}
		
		Party party = this.playersParty.get(invitedFrom);
		if(party.getLeader() == invitedFrom){
			if(!(party.isFull())){
				SendInvite(invitedFrom, invitedPlayer);
			}else{invitedFrom.sendMessage(ChatColor.RED + "Your party is full!");}
		}else{invitedFrom.sendMessage(ChatColor.RED + "You are not the partyleader!");}
		
	}
	
	public void KickPlayer(Player playerToKick, Player initiator){
		if(playerToKick == initiator){DisbandParty(initiator);}
		if(!(this.playersParty.containsKey(playerToKick))){return;}
		Party party = this.playersParty.get(playerToKick);
		if(initiator == party.getLeader()){
			party.removeMember(playerToKick);
			this.playersParty.remove(playerToKick);
		}else{initiator.sendMessage(ChatColor.RED + "You do not have the permission to kick this player");}
	}
	
	public void LeaveParty(Player player){
		if(this.playersParty.containsKey(player)){
			Party party = this.playersParty.get(player);
			if(player == party.getLeader()){DisbandParty(player);}
			else{
			for(Player p: party.getPlayers()){p.sendMessage(ChatColor.GOLD + player.getName()+ " left the party");}
			party.removeMember(player);
			this.playersParty.remove(player);
			}
		}
	}
	
	public boolean isLeader(Player player){
		if(this.playersParty.containsKey(player)){
			return (player == this.playersParty.get(player).getLeader());	
		}else{return false;}
	}
	
	public void DisbandParty(Player leader){
		if(!(this.playersParty.containsKey(leader))){return;}
		Party party = this.playersParty.get(leader);
		if(party.getLeader() == leader){
			List<Player> players = new ArrayList<Player>(party.getMembers());
			for(Player p: players){
				this.playersParty.remove(p);
			}
			leader.sendMessage(ChatColor.GOLD + "Party disbanded!");
		}
	}
	
	public void AcceptPartyInvite(Player player){
		if(this.pendingInvites.containsKey(player)){
			Player partyLeader = this.pendingInvites.get(player);
			if(this.playersParty.containsKey(partyLeader)){
				Party party = this.playersParty.get(partyLeader);
				if(party.getLeader() == partyLeader){
					if(!(party.isFull())){
						
						party.addMember(player);
						this.playersParty.put(player, party);
						this.pendingInvites.remove(player);
						for(Player p: party.getPlayers()){p.sendMessage(ChatColor.GOLD + player.getName()+ " joined the party");}
						
					}else{player.sendMessage(ChatColor.RED + "The party is now full");this.pendingInvites.remove(player);}
				
				}else{player.sendMessage(ChatColor.RED + "The person who invited you is no longer the leader"); this.pendingInvites.remove(player);}
			
			}else{player.sendMessage(ChatColor.RED + "The person who invited you left the party"); this.pendingInvites.remove(player);}
			
		}else{player.sendMessage(ChatColor.RED + "You have no pending party invites");}
	}
	
	public void DeclinePartyInvite(Player player){
		
		if(this.pendingInvites.containsKey(player)){
			player.sendMessage(ChatColor.RED + "You decline the party invite");
			this.pendingInvites.remove(player);
		}else{player.sendMessage(ChatColor.RED + "You have no pending party invites");}
		
	}
	
	public Party getParty(Player player){
		if(this.playersParty.containsKey(player)){
			return this.playersParty.get(player);
		}else{return null;}
	}
	
	public List<Player> getPartyMembersOrPlayer(Player player){ //convenience method for skills
		if(this.playersParty.containsKey(player)){
			return this.playersParty.get(player).getMembers();
		}else{return Arrays.asList(player);}
	}
}
