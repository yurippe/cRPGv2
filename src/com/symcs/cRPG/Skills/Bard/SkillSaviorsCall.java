package com.symcs.cRPG.Skills.Bard;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.StatusEffects.Generic.StatusEffectBonusSpeed;

public class SkillSaviorsCall extends Skill{

	public SkillSaviorsCall(cRPG plugin) {
		super(plugin);

		setSkillItem(new ItemStack(Material.RECORD_5));
		setSkillName("Saviors Call");
		setSkillDescription(Arrays.asList("Gain Massive speed bonus", "Cancelled on damage taken"));
		setSkillCooldown(10);

	}
	
	public void Cast(){
		plugin.getPlayerManager().getPlayer(this.player).getStatusEffectManager().addStatusEffect(new StatusEffectBonusSpeed(2, 10));
	}
}
