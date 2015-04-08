package com.symcs.cRPG.Classes;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.BaseClasses.PlayerSkill;
import com.symcs.cRPG.Skills.SkillFireball;

public class PlayerClassHunter extends PlayerClass{
	

	public PlayerClassHunter(Player player, cRPG plugin) {
		super(player, plugin);
		setClassName("Hunter");
		setDamageMultiplier(1.3);
		setMaxHealth(20);
		
		setTierWeapon(1, new ItemStack(Material.BOW, 1), "Noob Bow", Arrays.asList("Hunter weapon", "Tier 1"));
		
		setSkill(1, new PlayerSkill(this, new SkillFireball(this.plugin)));
		
	}

}
