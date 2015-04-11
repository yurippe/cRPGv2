package com.symcs.cRPG.Managers;

import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;

public class EffectManager {
	
	//The idea is to implement the same effect as the block manager, except we never really change the blocks server side
	
	@SuppressWarnings("unused")
	private cRPG plugin;

	public EffectManager(cRPG plugin){
		this.plugin = plugin;
	}
	
	public void test(Player p){
		//p.sendChunkChange(arg0, arg1, arg2, arg3, arg4)
	}
	
	

}
