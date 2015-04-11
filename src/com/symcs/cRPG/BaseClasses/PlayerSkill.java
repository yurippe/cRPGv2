package com.symcs.cRPG.BaseClasses;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;
import com.symcs.cRPG.Tasks.Task;

public class PlayerSkill {
	
	private Skill skill;
	
	private boolean onCooldown = false;
	private int remainingCooldown = 0;
	
	protected cRPG plugin;
	protected PlayerClass playerclass;
	
	public PlayerSkill(PlayerClass playerClass, Skill skill){
		this.playerclass = playerClass;
		this.skill = skill;
		this.plugin = this.skill.getPlugin();
		this.skill.setPlayer(playerClass.getPlayer());
	}
	
	public boolean onCooldown(){
		return this.onCooldown;
	}
	
	public void setOnCooldown(boolean setOnCooldown){
		this.onCooldown = setOnCooldown;
	}
	
	public int getRemainingCooldown(){
		return this.remainingCooldown;
	}
	
	public void setRemainingCooldown(int remCooldown){
		this.remainingCooldown = remCooldown;
	}
	
	public int getCooldown(){
		return this.skill.getSkillCooldown();
	}
	
	public int calculateCooldown(){
		//TODO calculate cooldown with cooldown reductions
		PlayerData pdat = this.plugin.getPlayerManager().getPlayer(this.playerclass.getPlayer());
		double aprox = getCooldown() * pdat.getStatusEffectManager().cooldownModifier();
		return (int) aprox;
	}
	
	public void setCooldown(int cooldown){
		this.skill.setSkillCooldown(cooldown);
	}
	
	protected ItemStack getCooldownItem(Material mat, int stacksize){
		ItemStack item = new ItemStack(mat, stacksize);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(skill.getSkillName());
		meta.setLore(skill.getSkillDescription());
		item.setItemMeta(meta);
		return item;
	}
	
	public void setSkillItem(ItemStack item){
		this.skill.setSkillItem(item);
	}
	
	
	public ItemStack getSkillItem(){
		return this.skill.getSkillItem();
	}
	
	public String getName(){
		return this.skill.getSkillName();
	}
	
	public void setName(String name){
		this.skill.setSkillName(name);
	}
	
	public void invokeCooldown(){
		
		setOnCooldown(true);
		setRemainingCooldown(calculateCooldown());
		this.updateCooldownItem();
		
		new Task(this.skill.getPlugin()){
			
			private PlayerSkill skill;
			
			public Task addArgs(PlayerSkill skill){
				this.skill = skill;
				return this;
			}
			
			@Override
			public void cancelEarly(){
				skill.completeCooldown();
				this.cancel();// dunno bout this
			}
  			
  			@Override
  			public void run(){
  				
  				if(skill.remainingCooldown <= 1){
  					skill.completeCooldown();
  					this.cancel();
  				}else{
  					skill.tickCooldown();
  				}
  				
  			}
  	
	}.addArgs(this).runTaskTimer(this.skill.getPlugin(), 20, 20);
		
	}
	
	public void completeCooldown(){
		
		setOnCooldown(false);
		setRemainingCooldown(0);
		

		if(this.playerclass.isArmed){
		this.playerclass.player.getInventory().setItem(this.playerclass.Keybinds.get(this), this.skill.getSkillItem());
		this.playerclass.player.updateInventory();
		}
	}
	
	public void tickCooldown(){
		
		setRemainingCooldown(getRemainingCooldown()-1);
		updateCooldownItem();
	}
	
	public void updateCooldownItem(){
		if(this.playerclass.isArmed){
		this.playerclass.player.getInventory().setItem(this.playerclass.Keybinds.get(this), getCooldownItem(Material.REDSTONE, this.remainingCooldown));
		this.playerclass.player.updateInventory();
		}
	}
	
	public void Cast(){
		if(this.onCooldown){return;}
		else{if(skill.getSkillCooldown() > 0){invokeCooldown();}}
		this.skill.Cast();
	}

}
