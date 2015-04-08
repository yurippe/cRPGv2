package com.symcs.cRPG.utils.Hitboxes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.symcs.cRPG.cRPG;

public class HitboxSphereBlock extends Hitbox{

	private Block block;
	private int radius;
	private int height;

	public HitboxSphereBlock(cRPG plugin, Block block, int radius, int height) {
		super(plugin);
		this.block = block;
		this.radius = radius;
		this.height = height; //not working atm
	}
	
	public HitboxSphereBlock(cRPG plugin, Block block, int radius){this(plugin,block,radius,0);}

	@Override
	public List<LivingEntity> getEntitiesHit() {
		List<LivingEntity> retlist = new ArrayList<LivingEntity>();
		for(Entity e : getNearbyEntities(this.block.getLocation(), this.radius, this.height)){
			if(e instanceof LivingEntity){
				
				//can do checking for height here if needed at some point
				
				retlist.add((LivingEntity)e);
			}
		}
		
		return retlist;
	}
	
	private List<Entity>  getNearbyEntities(Location l, int radius, int height){
        int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16))/16;
        ArrayList<Entity> radiusEntities = new ArrayList<Entity>();
            for (int chX = 0 -chunkRadius; chX <= chunkRadius; chX ++){
                for (int chZ = 0 -chunkRadius; chZ <= chunkRadius; chZ++){
                    int x=(int) l.getX(),y=(int) l.getY(),z=(int) l.getZ();
                    for (Entity e : new Location(l.getWorld(),x+(chX*16),y,z+(chZ*16)).getChunk().getEntities()){
                    	//if (e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock()) radiusEntities.add(e);
                        if (e.getLocation().distance(l) <= radius) radiusEntities.add(e);
                    }
                }
            }
        return radiusEntities;
    }

}
