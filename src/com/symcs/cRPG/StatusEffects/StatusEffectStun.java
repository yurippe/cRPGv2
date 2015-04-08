package com.symcs.cRPG.StatusEffects;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.symcs.cRPG.BaseClasses.StatusEffect;



public class StatusEffectStun extends StatusEffect{
	
	
	public StatusEffectStun(int duration, Player player){
		super(duration);
	}
	
	@Override
	public boolean canCast(){
		return false;
	}
	
	@Override
	public void onCast(){
		this.player.addPotionEffect(PotionEffectType.JUMP.createEffect(9999, 128));
		this.player.addPotionEffect(PotionEffectType.SLOW.createEffect(9999, 255));
	}
	
	@Override
	public void onFade(){
		this.player.removePotionEffect(PotionEffectType.SLOW);
		this.player.removePotionEffect(PotionEffectType.JUMP);
	}
	
}
