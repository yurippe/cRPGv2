package com.symcs.cRPG.Managers;

import org.bukkit.Location;

import com.symcs.cRPG.cRPG;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.effect.TornadoEffect;

public class EffectManager {
	
	//The idea is to implement the same effect as the block manager, except we never really change the blocks server side
	
	@SuppressWarnings("unused")
	private cRPG plugin;
	
	private EffectLib elib;
	private de.slikey.effectlib.EffectManager manager;

	public EffectManager(cRPG plugin){
		this.plugin = plugin;
		try{
		this.elib = EffectLib.instance();
		this.manager = new de.slikey.effectlib.EffectManager(this.elib);
		}
		catch(Exception e){plugin.getLogger().info("EffectLib could not be loaded");this.elib=null;this.manager=null;}
	}
	
	public void onDisable(){
		if(this.manager==null){return;}
		this.manager.dispose();
	}
	

	public void createInfernoEffect(Location loc) {
		if(this.manager==null){return;}
		TornadoEffect e = new TornadoEffect(this.manager);
		e.setLocation(loc);
		e.start();
	}
	

}
