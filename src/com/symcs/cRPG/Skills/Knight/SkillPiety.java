package com.symcs.cRPG.Skills.Knight;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.StatusEffects.Generic.StatusEffectBonusDamage;

public class SkillPiety extends Skill{

	public SkillPiety(cRPG plugin) {
		super(plugin);

		setSkillItem(new ItemStack(Material.POTION));
		setSkillName("Piety");
		setSkillDescription(Arrays.asList("Gain bonus damage", "for 10 seconds"));
		setSkillCooldown(10);

	}
	
	public void Cast(){
		plugin.getPlayerManager().getPlayer(this.player).getStatusEffectManager().addStatusEffect(new StatusEffectBonusDamage(1.2, 10));
	}

}
