package com.symcs.cRPG.utils.Hitboxes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;

public class HitboxSphere extends Hitbox{

	private Player player;
	private double radius;
	private double height;

	public HitboxSphere(cRPG plugin, Player p, double radius, double height) {
		super(plugin);
		this.player = p;
		this.radius = radius;
		this.height = height;
	}
	
	public HitboxSphere(cRPG plugin, Player p, double radius){
		this(plugin, p, radius, 0);
	}
	
	@Override
	public List<LivingEntity> getEntitiesHit() {
		List<Entity> ents;
		//ignore height
		if(height == -1){ents = player.getNearbyEntities(radius, 250, radius);}
		//use radius as height
		else if(height == 0){ents = player.getNearbyEntities(radius, radius, radius);}
		//use height as height
		else{ents = player.getNearbyEntities(radius, height, radius);}
		
		List<LivingEntity> returnlist = new ArrayList<LivingEntity>();
		for(Entity e: ents){
			if(e instanceof LivingEntity){
					returnlist.add((LivingEntity)e);
			}
		}
		
		return returnlist;
		
	}

}
