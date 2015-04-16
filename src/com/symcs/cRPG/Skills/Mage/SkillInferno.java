package com.symcs.cRPG.Skills.Mage;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.Tasks.TaskHitboxRegister;
import com.symcs.cRPG.utils.Hitboxes.HitboxSphereBlock;

public class SkillInferno extends Skill{
	
	public SkillInferno(cRPG plugin){
		super(plugin);
		
		setSkillItem(new ItemStack(Material.APPLE));
		setSkillName("Inferno");
		setSkillDescription(Arrays.asList("sample skill", "description"));
		setSkillCooldown(3);
		setSkillIgnoreFriendly(); //Use this if you want the skill to completely ignore allies (onSkillHitFriendly will never be called however)
		//setSkillDifferentOnAllies(); //Required for onSkillHitFriendly to be triggered (Allies are defined as party members)
	}
	
	@Override
	public void Cast(){
		
		registerProjectile(this.player.launchProjectile(Fireball.class));

	}
	
	@Override
	public void onProjectileLand(Location loc){
		Block b = loc.getBlock();

		HitboxSphereBlock hitbox = new HitboxSphereBlock(plugin, b, 4);
		TaskHitboxRegister task = new TaskHitboxRegister(plugin, 10, hitbox, this, b);
		
		task.runTaskTimer(plugin, 0, 20);
		plugin.getEffectManager().createInfernoEffect(loc);
	}
	
	@Override
	public void onSkillHit(LivingEntity hit){
		dealDamage(hit, 2.0);
	}
	
}