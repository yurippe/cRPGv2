package com.symcs.cRPG.Skills.Knight;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.StatusEffects.StatusEffectEternalFlame;

public class SkillEternalFlame extends Skill{

	public SkillEternalFlame(cRPG plugin) {
		super(plugin);
		setSkillItem(new ItemStack(Material.GLOWSTONE_DUST));
		setSkillName("Eternal Flame");
		setSkillDescription(Arrays.asList("Prevents death the next 10 seconds"));
		setSkillCooldown(60);
	}
	
	public void Cast(){
		
		plugin.getPlayerManager().getPlayer(this.player).getStatusEffectManager().addStatusEffect(new StatusEffectEternalFlame(1, 10));
	}

}
