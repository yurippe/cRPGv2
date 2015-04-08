package com.symcs.cRPG.Classes;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.BaseClasses.PlayerSkill;
import com.symcs.cRPG.Skills.SkillEarthWall;


public class PlayerClassKnight extends PlayerClass{

	public PlayerClassKnight(Player player, cRPG plugin) {
		super(player, plugin);
		setClassName("Knight");
		setDamageMultiplier(0.5);
		setMaxHealth(20);
		
		setTierWeapon(1, new ItemStack(Material.WOOD_SWORD, 1), "Noob Sword",Arrays.asList("Knight weapon", "Tier 1"));
		
		setSkill(1, new PlayerSkill(this, new SkillEarthWall(this.plugin)));
	}

	
	
}
