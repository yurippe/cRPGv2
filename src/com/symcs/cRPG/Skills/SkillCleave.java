package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.utils.NMSFunctions;
import com.symcs.cRPG.utils.Hitboxes.HitboxSquareFront;

public class SkillCleave extends Skill{

	public SkillCleave(cRPG plugin) {
		super(plugin);
		
		setSkillItem(new ItemStack(Material.FIREBALL));
		setSkillName("Fireball");
		setSkillDescription(Arrays.asList("Cast a mighty fireball"));
		setSkillCooldown(5);
		setSkillDamage(5.0);
	}
	
	@Override
	public void Cast(){
		HitboxSquareFront  Hitbox = new HitboxSquareFront(this.plugin, this.player, 4, 3, 2, 1);
		Hitbox.registerHits(this);
	}
	
	@Override
	public void onSkillHit(LivingEntity entity){
		dealDamage(entity);
		if(entity instanceof Player){
			//NMSFunctions.sendIngameEffect(block.getLocation(), 2001, 133, (Player) entity);
		}
	}
	
}