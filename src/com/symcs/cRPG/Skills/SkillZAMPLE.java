package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.utils.Hitboxes.HitboxSphere;

public class SkillZAMPLE extends Skill{

	public SkillZAMPLE(cRPG plugin) {
		super(plugin);
		
		setSkillItem(new ItemStack(Material.APPLE));
		setSkillName("Sample skill name");
		setSkillDescription(Arrays.asList("sample skill", "description"));
		setSkillCooldown(3);
		//setSkillIgnoreFriendly();
		setSkillDifferentOnAllies();
	}
	
	@Override
	public void Cast(){
		HitboxSphere hitbox = new HitboxSphere(plugin, this.player, 5);
		hitbox.registerHits(this);
		hitbox.registerSelf(this);
	}
	
	@Override
	public void onSkillHitPlayer(Player hit){
		
	}
	
	//can be overriden to add extra effects on hit
	@Override
	public void onSkillHitEntity(LivingEntity hit){
		
	}
	
	//combines the 2 above
	@Override
	public void onSkillHit(LivingEntity hit){
		hit.setHealth(0.0);
	}
	
	//can be overriden to add extra effects on hit friendly
	@Override
	public void onSkillHitFriendly(Player hit){
		hit.setHealth(10.0);
	}

}
