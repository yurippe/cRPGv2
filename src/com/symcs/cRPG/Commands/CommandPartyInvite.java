package com.symcs.cRPG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;

public class CommandPartyInvite implements CommandExecutor {
	private final cRPG plugin;
 
	public CommandPartyInvite(cRPG plugin) {
		this.plugin = plugin;
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("partyinvite")){
			if(sender instanceof Player){
				if(args.length == 1){
					Player p2inv = this.plugin.getServer().getPlayer(args[0]);
					this.plugin.getPartyManager().InvitePlayer((Player) sender, p2inv);
					return true;
				}
			}
		}
		return false;
	}
}