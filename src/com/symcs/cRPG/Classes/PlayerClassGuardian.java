package com.symcs.cRPG.Classes;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.BaseClasses.PlayerSkill;
import com.symcs.cRPG.Skills.SkillCharge;
import com.symcs.cRPG.Skills.SkillEarthWall;
import com.symcs.cRPG.Skills.SkillEternalFlame;

public class PlayerClassGuardian extends PlayerClass{
	

	public PlayerClassGuardian(Player player, cRPG plugin) {
		super(player, plugin);
		setClassName("Guardian");
		setDamageMultiplier(0.3);
		setDefenseMultiplier(0.3);
		setMaxHealth(30);
		
		setTierWeapon(1, new ItemStack(Material.ANVIL, 1), "Noob Hammer", Arrays.asList("Guardian weapon", "Tier 1"));
		
		setSkill(1, new PlayerSkill(this, new SkillEarthWall(this.plugin)));
		setSkill(2, new PlayerSkill(this, new SkillCharge(this.plugin)));
		setSkill(3, new PlayerSkill(this, new SkillEternalFlame(this.plugin)));
		
	}

}
