package com.symcs.cRPG.Skills.Bard;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.StatusEffects.StatusEffectStun;
import com.symcs.cRPG.utils.Hitboxes.HitboxSphere;

public class SkillSongOfDuality extends Skill{

	public SkillSongOfDuality(cRPG plugin) {
		super(plugin);
		
		setSkillItem(new ItemStack(Material.APPLE));
		setSkillName("Song of Duality");
		setSkillDescription(Arrays.asList("Stun enemies around you", "Give bonus speed to allies around you"));
		setSkillCooldown(10);
		setSkillDifferentOnAllies();
	}
	
	@Override
	public void Cast(){
		HitboxSphere hitbox = new HitboxSphere(plugin, this.player, 5);
		hitbox.registerHits(this);
		hitbox.registerSelf(this);
	}
	
	@Override
	public void onSkillHitFriendly(Player p){
		p.addPotionEffect(PotionEffectType.SPEED.createEffect(60, 2));
		p.sendMessage("Got buffed with Song of Duality - Speed boost for 3 seconds");
	}
	
	@Override
	public void onSkillHitPlayer(Player p){
		plugin.getPlayerManager().getPlayer(p).getStatusEffectManager().addStatusEffect(new StatusEffectStun(3, p));
		p.sendMessage("You got hit by Song of Duality - Stunned for 3 seconds");
	}
	
}