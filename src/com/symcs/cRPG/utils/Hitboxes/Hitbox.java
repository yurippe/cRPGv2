package com.symcs.cRPG.utils.Hitboxes;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;

public abstract class Hitbox {

	protected cRPG plugin;
	public Hitbox(cRPG plugin){
		this.plugin = plugin;
	}
	
	public boolean contains(Location loc, Vector p1, Vector p2) {
		if(loc == null) {
			return false;
		}
		return loc.getX() >= p1.getX() && loc.getX() <= p2.getX()
				&& loc.getY() >= p1.getY() && loc.getY() <= p2.getY()
				&& loc.getZ() >= p1.getZ() && loc.getZ() <= p2.getZ();
		}
	
	public void registerHits(Skill skill){
		for(LivingEntity ent : getEntitiesHit()){
			if(ent instanceof Player){plugin.getDamageManager().onSkillHitPlayer(skill, (Player) ent);}
			else{plugin.getDamageManager().onSkillHitEntity(skill, ent);}
		}
	}
	

	
	public abstract List<LivingEntity> getEntitiesHit();//includes players
	public abstract List<Player> getPlayersHit();
	
}