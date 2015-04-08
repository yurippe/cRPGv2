package com.symcs.cRPG.utils.Hitboxes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.symcs.cRPG.cRPG;

public class HitboxSquareFront extends Hitbox{
	
	private Player player;
	private double sides;
	private double forward;
	private double height;
	private double depth;
	
	public HitboxSquareFront(cRPG plugin, Player p, double left_right, double forward, double height){
		this(plugin, p, left_right, forward, height, 0);
	}
	
	public HitboxSquareFront(cRPG plugin, Player p, double left_right, double forward, double height, double depth){
		super(plugin);
		this.player = p;
		this.sides = left_right;
		this.forward = forward;
		this.height = height;
		this.depth = depth;
	}
	
	public List<LivingEntity> getEntitiesHit(){ //Includes players
		
		Vector direction = player.getLocation().getDirection().setY(0).normalize();
		Vector PlayerPoint = player.getLocation().toVector();
		Vector dirPerp = new Vector(0.0-direction.getZ(),0,direction.getX()).normalize();

		Vector LeftLook = dirPerp.clone().multiply(sides);
		Vector RightLook = dirPerp.clone().multiply(0-sides);
		
		
		Vector p1 = PlayerPoint.clone().add(LeftLook.add(direction.multiply(forward).add(new Vector(0, 0-depth, 0))));
		Vector p2 = PlayerPoint.clone().add(RightLook.add(new Vector(0, height, 0)));
		
		/*
		double x1 = Math.min(p1.getX(), p2.getX());
		double y1 = Math.min(p1.getY(), p2.getY());
		double z1 = Math.min(p1.getZ(), p2.getZ());
		double x2 = Math.max(p1.getX(), p2.getX());
		double y2 = Math.max(p1.getY(), p2.getY());
		double z2 = Math.max(p1.getZ(), p2.getZ());
		*/
		p1 = new Vector( Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()), Math.min(p1.getZ(), p2.getZ()));
		p2 = new Vector( Math.max(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()), Math.max(p1.getZ(), p2.getZ()));
		
		//p1.toLocation(player.getWorld()).getBlock().setType(Material.EMERALD_BLOCK);
		//p2.toLocation(player.getWorld()).getBlock().setType(Material.EMERALD_BLOCK);
		
		
		double r = Math.max(this.sides, this.forward);
		List<Entity> ents = player.getNearbyEntities(r+3, height, r+3);
		
		List<LivingEntity> returnlist = new ArrayList<LivingEntity>();
		for(Entity e: ents){
			if(e instanceof LivingEntity){
				//if(e.getLocation().toVector().isInAABB(p1, p2)){
				if(contains(e.getLocation(), p1, p2)){
					returnlist.add((LivingEntity)e);
				}
			}
		}
		
		
		return returnlist;
	}
	
	/*
	public List<Player> getPlayersHit(){//Strictly get players only
		
		List<LivingEntity> l = getEntitiesHit();
		List<Player> returnlist = new ArrayList<Player>();
		for(LivingEntity e:l){
			if(e instanceof Player){returnlist.add((Player)e);}
		}
		return returnlist;
	}
	*/


}
