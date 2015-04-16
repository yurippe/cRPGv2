package com.symcs.cRPG.Skills.Bard;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;

public class SkillHealingTouch extends Skill{

	public SkillHealingTouch(cRPG plugin) {
		super(plugin);

		setSkillItem(new ItemStack(Material.RECORD_4));
		setSkillName("Healing Touch");
		setSkillDescription(Arrays.asList("Heal yourslef"));
		setSkillCooldown(10);

	}
	
	public void Cast(){
		this.plugin.getPlayerManager().getPlayer(this.player).getPlayerClass().healPlayer(3.0);
	}

}
