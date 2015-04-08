package com.symcs.cRPG.Managers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;

public class PlayerManager {
	private cRPG plugin;
	private HashMap<Player, PlayerData> PlayersMap = new HashMap<Player, PlayerData>();
	
	public PlayerManager(cRPG plugin){
		this.plugin = plugin;
	}
	
	public void addPlayer(Player player, PlayerData playerdata){
		this.PlayersMap.put(player, playerdata);
	}
	
	public void removePlayer(Player player){
		this.PlayersMap.remove(player);
	}
	
	public HashMap<Player, PlayerData> getPlayers(){
		return this.PlayersMap;
	}
	public PlayerData getPlayer(Player player){
		return this.PlayersMap.get(player);
	}
	
	public PlayerData getPlayer(String uuid){
		return getPlayer(String2UUID(uuid));
	}
	
	public PlayerData getPlayer(UUID uuid){
		Player player = this.plugin.getServer().getPlayer(uuid);
		if(player == null){return null;}
		return getPlayer(player);
	}
	
	public boolean hasData(Player player){
		return this.PlayersMap.containsKey(player);
	}
	
	public boolean hasData(UUID uuid){
		Player player = this.plugin.getServer().getPlayer(uuid);
		if(player == null){return false;}
		return hasData(player);
	}
	
	public boolean hasData(String uuid){
		return hasData(String2UUID(uuid));
	}
	
	public Player UUID2Player(UUID uuid){
		return this.plugin.getServer().getPlayer(uuid);
	}
	
	public Player UUID2Player(String uuid){
		return UUID2Player(String2UUID(uuid));
	}
	
	public UUID String2UUID(String UUIDString){
		return UUID.fromString(UUIDString);
	}

}
