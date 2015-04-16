package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.CustomEvents.CustomDamageEvent;
import com.symcs.cRPG.utils.EventReservation;


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
		
		plugin.getListenerManager().reserveNextEvent("CustomDamage", new EventReservation(this){
			
			@Override
			public boolean If(Event e){
				CustomDamageEvent event = (CustomDamageEvent)e;
				if(event.getDamagee() == skill.getEntity()){return true;}
				return false;
			}
		});
		
	}
	
	@Override
	public void EventReservationCallback(Event e){
		if(e instanceof CustomDamageEvent){
			CustomDamageEvent event = (CustomDamageEvent) e;
			LivingEntity ent = event.getDamager();
			String n;
			if(ent == null){n = " not a livin entity";}
			else{n = ent.getName();}
			plugin.getLogger().info("ReservedDamageEvent: Damage from:" + n);
		}
		
	}
	
}