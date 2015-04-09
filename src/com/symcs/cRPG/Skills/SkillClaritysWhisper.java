package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.StatusEffects.StatusEffectStun;
import com.symcs.cRPG.StatusEffects.Generic.StatusEffectBonusSpeed;

public class SkillClaritysWhisper extends Skill{

	public SkillClaritysWhisper(cRPG plugin) {
		super(plugin);

		setSkillItem(new ItemStack(Material.ARROW));
		setSkillName("Clarity's Whisper");
		setSkillDescription(Arrays.asList("Stuns and roots and enemy", "or","Heals and grants bonus speed to an ally"));
		setSkillCooldown(10);
		setSkillDifferentOnAllies();
	}
	
	@Override
	public void Cast(){
		registerProjectile((Projectile) this.player.launchProjectile(Arrow.class));
	}
	
	@Override
	public void onSkillHitFriendly(Player p){
		//p.addPotionEffect(PotionEffectType.SPEED.createEffect(60, 2));
		plugin.getPlayerManager().getPlayer(p).getStatusEffectManager().addStatusEffect(new StatusEffectBonusSpeed(1,3));
		//this.player.addPotionEffect(PotionEffectType.SPEED.createEffect(60, 2));
		plugin.getPlayerManager().getPlayer(this.player).getStatusEffectManager().addStatusEffect(new StatusEffectBonusSpeed(1,3));
		p.sendMessage("Got buffed with Clarity's Whisper - Speed boost for 3 seconds");
		this.player.sendMessage("You hit an ally with Clarity's Whisper - Speed boost for 3 seconds");
	}
	
	@Override
	public void onSkillHitPlayer(Player p){
		plugin.getPlayerManager().getPlayer(p).getStatusEffectManager().addStatusEffect(new StatusEffectStun(1, p));
		dealDamage(p, 3);
		p.sendMessage("You got hit by Clarity's Whisper - Stunned for 1 seconds");
	}
}
