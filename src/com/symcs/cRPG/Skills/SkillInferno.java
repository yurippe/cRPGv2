package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.Tasks.TaskHitboxRegister;
import com.symcs.cRPG.utils.RelativeBlocks;
import com.symcs.cRPG.utils.Hitboxes.HitboxSphereBlock;

public class SkillInferno extends Skill{
	
	public SkillInferno(cRPG plugin){
		super(plugin);
		
		setSkillItem(new ItemStack(Material.APPLE));
		setSkillName("Inferno");
		setSkillDescription(Arrays.asList("sample skill", "description"));
		setSkillCooldown(3);
		//setSkillIgnoreFriendly(); //Use this if you want the skill to completely ignore allies (onSkillHitFriendly will never be called however)
		//setSkillDifferentOnAllies(); //Required for onSkillHitFriendly to be triggered (Allies are defined as party members)
	}
	
	@Override
	public void Cast(){
		
		Block b = RelativeBlocks.frontOfPlayer(this.player, 3);

		HitboxSphereBlock hitbox = new HitboxSphereBlock(plugin, b, 4);
		TaskHitboxRegister task = new TaskHitboxRegister(plugin, 10, hitbox, this, b);
		
		task.runTaskTimer(plugin, 0, 20);
		
		//EffectInferno eff = new EffectInferno(plugin, 20, b.getLocation());
		//eff.play(10);
	}
	

	@Override
	public void onSkillHitPlayer(Player hit){
		
	}
	
	@Override
	public void onSkillHitEntity(LivingEntity hit){
		
	}
	
	//hit can be either a player or a living entity usefull if there is no difference on the effect
	@Override
	public void onSkillHit(LivingEntity hit){
		plugin.getLogger().info("Hit " + hit.getName() + " with inferno");
	}
	
	@Override
	public void onSkillHitFriendly(Player hit){
		
	}

}