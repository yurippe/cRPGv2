package com.symcs.cRPG.Classes;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.BaseClasses.PlayerSkill;
import com.symcs.cRPG.Skills.SkillPerfectPitch;

public class PlayerClassBard extends PlayerClass{
	

	public PlayerClassBard(Player player, cRPG plugin) {
		super(player, plugin);
		setClassName("Bard");
		setDamageMultiplier(1.3);
		setMaxHealth(20);
		
		setTierWeapon(1, new ItemStack(Material.RECORD_11, 1), "Noob Record", Arrays.asList("Bard weapon", "Tier 1"));
		
		setSkill(1, new PlayerSkill(this, new SkillPerfectPitch(this.plugin)));
		
	}

}
