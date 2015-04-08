package com.symcs.cRPG.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Party {
	
	private List<Player> players = new ArrayList<Player>();
	private Player leader;
	private int MaxSize;
	
	public Party(Player leader){
		this.leader = leader;
		this.players.add(leader);
		this.MaxSize = 5;
	}
	
	public void setLeader(Player leader){
		this.leader = leader;
	}
	public Player getLeader(){
		return this.leader;
	}
	public List<Player> getPlayers(){
		return this.players;
	}
	public List<Player> getMembers(){
		return this.players;
	}
	public boolean addMember(Player player){
		if(this.players.size() < this.MaxSize){
			players.add(player);
			return true;
		}else{return false;}
	}
	public boolean removeMember(Player player){
		if(this.players.contains(player)){
			this.players.remove(player);
			return true;
		}else{return false;}
	}
	public boolean isFull(){
		return (this.players.size() >= this.MaxSize);
	}
}
