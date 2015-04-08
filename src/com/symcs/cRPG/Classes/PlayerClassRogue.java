package com.symcs.cRPG.Classes;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.BaseClasses.PlayerSkill;
import com.symcs.cRPG.Skills.SkillEarthWall;

public class PlayerClassRogue extends PlayerClass{
	

	public PlayerClassRogue(Player player, cRPG plugin) {
		super(player, plugin);
		setClassName("Rogue");
		setDamageMultiplier(1.3);
		setMaxHealth(20);
		
		setTierWeapon(1, new ItemStack(Material.FEATHER, 1), "Noob Dagger", Arrays.asList("Rogue weapon", "Tier 1"));
		
		setSkill(1, new PlayerSkill(this, new SkillEarthWall(this.plugin)));
		
	}

}
