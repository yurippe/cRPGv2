package com.symcs.cRPG.Skills.Mage;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.StatusEffects.StatusEffectStun;
import com.symcs.cRPG.utils.Hitboxes.HitboxSphere;

public class SkillShockWave extends Skill{

	public SkillShockWave(cRPG plugin) {
		super(plugin);
		
		setSkillItem(new ItemStack(Material.APPLE));
		setSkillName("Shock Wave");
		setSkillDescription(Arrays.asList("Stun enemies around you", "[only works on players]"));
		setSkillCooldown(3);
		setSkillIgnoreFriendly();
	}
	
	@Override
	public void Cast(){
		HitboxSphere hitbox = new HitboxSphere(plugin, this.player, 5);
		hitbox.registerHits(this);
	}
	
	@Override
	public void onSkillHitPlayer(Player p){
		plugin.getPlayerManager().getPlayer(p).getStatusEffectManager().addStatusEffect(new StatusEffectStun(3, p));
		p.sendMessage("You got hit by Shock Wave - Stunned for 3 seconds");
	}
	
}