package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.utils.EventReservation;
import com.symcs.cRPG.utils.AnimationEffects.EffectBeam;


public class SkillTEST extends Skill{

	public SkillTEST(cRPG plugin) {
		super(plugin);
		
		setSkillItem(new ItemStack(Material.GHAST_TEAR));
		setSkillName("Test Skill");
		setSkillDescription(Arrays.asList("Test all the things"));
		setSkillCooldown(0);
	}
	
	public void log(String s){
		plugin.getLogger().info(s);
	}
	
	@Override
	public void Cast(){
		
		plugin.getListenerManager().reserveNextEvent("PlayerMove", new EventReservation(this){
			
			@Override
			public boolean If(Event e){
				PlayerMoveEvent event = (PlayerMoveEvent)e;
				if(event.getPlayer() == skill.getPlayer()){return true;}
				return false;
			}
		});
		
	}
	
	@Override
	public void EventReservationCallback(Event e){
		if(e instanceof PlayerMoveEvent){
			PlayerMoveEvent event = (PlayerMoveEvent) e;
			plugin.getLogger().info("Player moved to: " + event.getTo().toString());
		}
		
	}
	
}