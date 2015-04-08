package com.symcs.cRPG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;

public class CommandPartyInviteDecline implements CommandExecutor {
	private final cRPG plugin;
 
	public CommandPartyInviteDecline(cRPG plugin) {
		this.plugin = plugin;
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
		if(cmd.getName().equalsIgnoreCase("partyinvitedecline")){
			this.plugin.getPartyManager().DeclinePartyInvite((Player)sender);
			return true;
		}}
		return false;
	}
}