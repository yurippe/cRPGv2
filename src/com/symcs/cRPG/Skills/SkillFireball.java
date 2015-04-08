package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.StatusEffects.StatusEffectStun;

public class SkillFireball extends Skill{

	public SkillFireball(cRPG plugin) {
		super(plugin);
		
		setSkillItem(new ItemStack(Material.FIREBALL));
		setSkillName("Fireball");
		setSkillDescription(Arrays.asList("Cast a mighty fireball"));
		setSkillCooldown(5);
	}
	
	@Override
	public void Cast(){
		registerProjectile(this.player.launchProjectile(Fireball.class));
	  
	}
	
	/*
	@Override
	public void onProjectileHitPlayer(EntityDamageByEntityEvent event){
		super(event);
		Player p = (Player) event.getEntity();
		p.sendMessage(this.Class.player.getName() + " hit you with a fireball, that bitch");
		
		plugin.getPlayerData(p).statusEffectManager.addStatusEffect(new StatusEffectStun(5, p));
	}*/
	
	@Override
	public void onSkillHitPlayer(Player p){
		p.sendMessage(this.player.getName() + " hit you with a fireball, that bitch");
		
		plugin.getPlayerManager().getPlayer(p).getStatusEffectManager().addStatusEffect(new StatusEffectStun(5, p));
	}
	
	

}
