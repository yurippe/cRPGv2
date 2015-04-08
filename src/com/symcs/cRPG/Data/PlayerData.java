package com.symcs.cRPG.Data;

import java.util.HashMap;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Managers.StatusEffectManager;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.Classes.*;
import com.symcs.cRPG.SaveUtils.CustomPlayerConfig;

import org.bukkit.entity.Player;


public class PlayerData {
	
	private cRPG plugin;
	private PlayerClass playerClass;
	public Player player;
	
	private StatusEffectManager statusEffectManager;
	
	private HashMap<String, PlayerClass> classes = new HashMap<String, PlayerClass>();
	
	private CustomPlayerConfig playerConfig;
	
	public PlayerData(Player p, cRPG plugin){
		
		this.plugin = plugin;
		this.player = p;
		this.playerConfig = new CustomPlayerConfig(plugin, p);
		this.statusEffectManager = new StatusEffectManager(plugin, this);
		
		addClass(new PlayerClassKnight(p, plugin));
		addClass(new PlayerClassMage(p, plugin));
		addClass(new PlayerClassHunter(p, plugin));
		addClass(new PlayerClassBard(p, plugin));
		addClass(new PlayerClassGuardian(p, plugin));
		addClass(new PlayerClassRogue(p, plugin));
		
		setPlayerClass("Mage");
		
	}
	
	public cRPG getPlugin(){
		return this.plugin;
	}
	
	public PlayerClass getPlayerClass(){
		return this.playerClass;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public HashMap<String, PlayerClass> getPlayerClasses(){
		return this.classes;
	}
	
	public StatusEffectManager getStatusEffectManager(){
		return this.statusEffectManager;
	}
	
	public void onLogout(){
		this.playerClass.onLogout();
		saveAll();
	}
	
	public void onLogin(){
		this.playerClass.onLogin();
		loadAll();
	}
	

	
	public void onDisable(){
		onLogout();
	}
	public void onEnable(){
		onLogin();
	}
	public void saveAll(){
		//Save class data
		for(PlayerClass c: this.classes.values()){
			this.playerConfig.saveClass(c);
		}
		
		this.playerConfig.savePlayerData(this);
		
	}
	public void loadAll(){
		//Load class data
		for(PlayerClass c: this.classes.values()){
			this.playerConfig.loadClass(c);
		}
		
		this.playerConfig.loadPlayerData(this);
		
	}
	
	public void addClass(PlayerClass aclass){
		if(aclass.isUnlockable()){
			if(plugin.getPremiumManager().isClassUnlocked(this, aclass)){
				this.classes.put(aclass.getClassName(), aclass);
			}
		}
		
		else{this.classes.put(aclass.getClassName(), aclass);}
	}
	
	public void setPlayerClass(String name){
		if(this.classes.containsKey(name))
		{
			if(!(this.playerClass == null)){if(this.playerClass.isArmed){this.playerClass.ToggleWeapon();}}
			this.playerClass = this.classes.get(name);
			this.playerClass.onClassChange();
		}
		else{plugin.getLogger().warning(
				"Could not set active class for player: " + player.getUniqueId().toString() + " Tried to set class to: " + name);}
	}
	
	public CustomPlayerConfig getPlayerSave(){return this.playerConfig;}
	
}
