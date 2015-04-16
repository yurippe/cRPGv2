package com.symcs.cRPG.Skills.Mage;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
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
		setSkillIgnoreAllies();
	}
	
	@Override
	public void Cast(){
		registerProjectile(this.player.launchProjectile(Fireball.class));
	  
	}
	
	
	@Override
	public void onSkillHitPlayer(Player p){
		p.sendMessage(this.player.getName() + " hit you with a fireball, that bitch");
		dealDamage(p, 10.0);
		plugin.getPlayerManager().getPlayer(p).getStatusEffectManager().addStatusEffect(new StatusEffectStun(5, p));
	}
	
	@Override
	public void onSkillHitEntity(LivingEntity e){
		dealDamage(e, 100.0);
	}
	
	@Override
	public void onProjectileLand(Location loc){
		plugin.getLogger().info("Fireball landed at: " + loc.toString());
	}
	

}
