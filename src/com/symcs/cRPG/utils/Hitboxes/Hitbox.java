package com.symcs.cRPG.utils.Hitboxes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;

public abstract class Hitbox {

	protected cRPG plugin;
	protected boolean limitedSet = false;
	private List<LivingEntity> limitedTo;
	
	public Hitbox(cRPG plugin){
		this.plugin = plugin;
		this.limitedSet = false;
	}
	

	public boolean contains(Location loc, Vector p1, Vector p2) {
		if(loc == null) {
			return false;
		}
		return loc.getX() >= p1.getX() && loc.getX() <= p2.getX()
				&& loc.getY() >= p1.getY() && loc.getY() <= p2.getY()
				&& loc.getZ() >= p1.getZ() && loc.getZ() <= p2.getZ();
		}
	
	public void limitSet(List<LivingEntity> limitTo){
		if(!(limitTo == null)){
			this.limitedTo = limitTo;
			this.limitedSet = true;
		}else{this.limitedSet = false;}
	}
	
	public void unLimitSet(){this.limitedSet = false;}
	public boolean isLimitedSet(){return this.limitedSet;}
	
	public void registerHits(Skill skill){
		for(LivingEntity ent : getEntitiesHit()){
			
			if(this.limitedSet){
				if(this.limitedTo.contains(ent)){
					if(ent instanceof Player){plugin.getDamageManager().onSkillHitPlayer(skill, (Player) ent);}
					else{plugin.getDamageManager().onSkillHitEntity(skill, ent);}
				}
			}
			else{
				if(ent instanceof Player){plugin.getDamageManager().onSkillHitPlayer(skill, (Player) ent);}
				else{plugin.getDamageManager().onSkillHitEntity(skill, ent);}
			}
		}
	}
	
	public void registerSelf(Skill skill){
		plugin.getDamageManager().onSkillHitPlayer(skill, skill.getPlayer());
	}
	

	
	public abstract List<LivingEntity> getEntitiesHit();//includes players
	//public abstract List<Player> getPlayersHit();
	public List<Player> getPlayersHit(){//Strictly get players only
		
		List<LivingEntity> l = getEntitiesHit();
		List<Player> returnlist = new ArrayList<Player>();
		for(LivingEntity e:l){
			if(e instanceof Player){returnlist.add((Player)e);}
		}
		return returnlist;
	}
	
}
