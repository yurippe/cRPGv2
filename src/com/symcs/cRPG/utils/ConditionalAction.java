package com.symcs.cRPG.utils;

import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.BaseClasses.Skill;

public abstract class ConditionalAction {
	
	protected cRPG plugin;
	protected PlayerData playerData;
	protected PlayerClass playerClass;
	protected Skill skill;
	protected Player player;
	
	public ConditionalAction(){}
	public ConditionalAction(cRPG plugin){this.plugin = plugin;}
	public ConditionalAction(PlayerData playerData)
	{
		this.plugin = playerData.getPlugin();
		this.playerData = playerData;
		this.playerClass = playerData.getPlayerClass();
		this.player = playerData.player;
	}
	public ConditionalAction(PlayerClass playerClass)
	{
		this.plugin = playerClass.getPlugin();
		this.playerData = plugin.getPlayerManager().getPlayer(playerClass.getPlayer());
		this.playerClass = playerClass;
		this.player = playerClass.getPlayer();
	}
	public ConditionalAction(PlayerData playerData, Skill skill)
	{
		this(playerData);
		this.skill = skill;
	}
	public ConditionalAction(PlayerClass playerClass, Skill skill)
	{
		this(playerClass);
		this.skill = skill;
	}
	public ConditionalAction(cRPG plugin, Player player, Skill skill){
		this.plugin = plugin;
		this.player = player;
		this.skill = skill;
	}
	
	
	public abstract boolean If();
	public abstract void Then();

}
