package com.symcs.cRPG.Skills.Mage;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.Managers.StatusEffectManager;
import com.symcs.cRPG.StatusEffects.Generic.StatusEffectBonusSpeed;
import com.symcs.cRPG.StatusEffects.Generic.StatusEffectCooldownReduction;

public class SkillBlessingOfValkyria extends Skill{

	public SkillBlessingOfValkyria(cRPG plugin) {
		super(plugin);
		
		setSkillItem(new ItemStack(Material.APPLE));
		setSkillName("Blessing Of Valkyria");
		setSkillDescription(Arrays.asList("Increase speed and gives", "20% cooldown reduction for 6 seconds"));
		setSkillCooldown(3);
		//setSkillIgnoreFriendly();
		//setSkillDifferentOnAllies();
	}
	
	@Override
	public void Cast(){
		StatusEffectManager effman = plugin.getPlayerManager().getPlayer(this.player).getStatusEffectManager();
		effman.addStatusEffect(new StatusEffectBonusSpeed(2, 6));
		effman.addStatusEffect(new StatusEffectCooldownReduction(0.8, 6));
	}
	

}
