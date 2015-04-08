package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;

public class SkillDaybreak extends Skill{

	public SkillDaybreak(cRPG plugin) {
		super(plugin);
		
		setSkillItem(new ItemStack(Material.GLOWSTONE_DUST));
		setSkillName("Daybreak");
		setSkillDescription(Arrays.asList("Sets the time to 0"));
		setSkillCooldown(30);
	}
	
	
	@Override
	public void Cast(){
		super.Cast();
		
		plugin.getServer().getWorld("world").setTime(0);
		
	}

}
