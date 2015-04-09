package com.symcs.cRPG.Classes;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.BaseClasses.PlayerSkill;
import com.symcs.cRPG.Skills.*;


public class PlayerClassTEST extends PlayerClass{
	

	public PlayerClassTEST(Player player, cRPG plugin) {
		super(player, plugin);
		setClassName("TEST");
		setDamageMultiplier(1.3);
		setMaxHealth(20);
		
		setTierWeapon(1, new ItemStack(Material.STICK, 1), "Noob Wand", Arrays.asList("Mage weapon", "Tier 1"));
		setTierWeapon(2, new ItemStack(Material.BLAZE_ROD, 1), "FUCKING SORCERER WAND", Arrays.asList("Mage weapon", "Tier 2"));
		
		setSkill(1, new PlayerSkill(this, new SkillTEST(this.plugin)));
		setSkill(2, new PlayerSkill(this, new SkillClaritysWhisper(this.plugin)));
		setSkill(5, new PlayerSkill(this, new SkillCleave(this.plugin)));
		setSkill(6, new PlayerSkill(this, new SkillEternalFlame(this.plugin)));
		setSkill(4, new PlayerSkill(this, new SkillFireball(this.plugin)));
		setSkill(3, new PlayerSkill(this, new SkillCharge(this.plugin)));
		
	}
	
	
	
	
}