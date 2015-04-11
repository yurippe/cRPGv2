package com.symcs.cRPG.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public class HelperFunctions {
	
	public static Block getBlockInfrontOfPlayer(Player player, double offset){
	
	Location loc = player.getLocation();
	 
	String dir = getCardinalDirection(player);
	 
	//use java 1.7 for this. If you can't then use elseif instead of switch
	switch(dir){
	 
	case "N" : loc = new Location(player.getWorld(),loc.getX(), loc.getY(), loc.getZ() - offset, loc.getYaw(), loc.getPitch()); return player.getWorld().getBlockAt(loc);
	case "NE": loc = new Location(player.getWorld(),loc.getX() + offset, loc.getY(), loc.getZ() - offset, loc.getYaw(), loc.getPitch()); return player.getWorld().getBlockAt(loc);
	case "E" : loc = new Location(player.getWorld(),loc.getX() + offset, loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()); return player.getWorld().getBlockAt(loc);
	case "SE" : loc = new Location(player.getWorld(),loc.getX() + offset, loc.getY(), loc.getZ() + offset, loc.getYaw(), loc.getPitch()); return player.getWorld().getBlockAt(loc);
	case "S" : loc = new Location(player.getWorld(),loc.getX(), loc.getY(), loc.getZ() + offset, loc.getYaw(), loc.getPitch()); return player.getWorld().getBlockAt(loc);
	case "SW" : loc = new Location(player.getWorld(),loc.getX() - offset, loc.getY(), loc.getZ() + offset, loc.getYaw(), loc.getPitch()); return player.getWorld().getBlockAt(loc);
	case "W" : loc = new Location(player.getWorld(),loc.getX() - offset, loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()); return player.getWorld().getBlockAt(loc);
	case "NW" : loc = new Location(player.getWorld(),loc.getX() - offset, loc.getY(), loc.getZ() - offset, loc.getYaw(), loc.getPitch()); return player.getWorld().getBlockAt(loc);
	}
	
	return null;
	
	}
	

	public static String getCardinalDirection(Player player) {
		double rotation = (player.getLocation().getYaw() - 90) % 360;
		if (rotation < 0) {
		rotation += 360.0;
		}
		if (0 <= rotation && rotation < 22.5) {
		return "W";//N
		} else if (22.5 <= rotation && rotation < 67.5) {
		return "NW";//NE
		} else if (67.5 <= rotation && rotation < 112.5) {
		return "N";//E
		} else if (112.5 <= rotation && rotation < 157.5) {
		return "NE";//SE
		} else if (157.5 <= rotation && rotation < 202.5) {
		return "E";//S
		} else if (202.5 <= rotation && rotation < 247.5) {
		return "SE";//SW
		} else if (247.5 <= rotation && rotation < 292.5) {
		return "S";//W
		} else if (292.5 <= rotation && rotation < 337.5) {
		return "SW";//NW
		} else if (337.5 <= rotation && rotation < 360.0) {
		return "W";//N
		} else {
		return null;
		}
	}
	
	public static List<List<Block>> getWallBlocksInfrontOfPlayer(Player player, double offset, int blockRadius, int height, boolean onlyAir){
		
		Block mBlock = getBlockInfrontOfPlayer(player, offset);
		String dir = getCardinalDirection(player);
		BlockFace bf1, bf2;
		List<List<Block>> finalList = new ArrayList<List<Block>>();
		
		if(dir == "N"){bf1 = BlockFace.EAST; bf2 = BlockFace.WEST;}
		else if(dir == "E"){bf1 = BlockFace.NORTH; bf2 = BlockFace.SOUTH;}
		else if(dir == "S"){bf1 = BlockFace.EAST; bf2 = BlockFace.WEST;}
		else if(dir == "W"){bf1 = BlockFace.NORTH; bf2 = BlockFace.SOUTH;}
		else if(dir == "NE"){bf1 = BlockFace.NORTH_WEST; bf2 = BlockFace.SOUTH_EAST;}
		else if(dir == "NW"){bf1 = BlockFace.NORTH_EAST; bf2 = BlockFace.SOUTH_WEST;}
		else if(dir == "SE"){bf1 = BlockFace.SOUTH_WEST; bf2 = BlockFace.NORTH_EAST;}//
		else if(dir == "SW"){bf1 = BlockFace.SOUTH_EAST; bf2 = BlockFace.NORTH_WEST;}//
		else{bf1 = null; bf2 = null;return null;}
		
		for(int h = 0; h<height; h++){
			List<Block> tmpList= new ArrayList<Block>();
			for(int r = 1; r<=blockRadius; r++){
				
				Block b1 = mBlock.getRelative(bf1,r);
				Block b2 = mBlock.getRelative(bf2,r);
				
				if(onlyAir && mBlock.getType() == Material.AIR){tmpList.add(mBlock);}
				else if(!(onlyAir)){tmpList.add(mBlock);}
				
				if(onlyAir && b1.getType() == Material.AIR){tmpList.add(b1);}
				else if(!(onlyAir)){tmpList.add(b1);}
				
				if(onlyAir && b2.getType() == Material.AIR){tmpList.add(b2);}
				else if(!(onlyAir)){tmpList.add(b2);}
				
			}
			mBlock = mBlock.getRelative(0, 1, 0); //set 1 up
			finalList.add(tmpList);
		}
		
		
		return finalList;
	}
	
	public static List<Block> getConeBlocksInfrontOfPlayer(Player player, double offset, boolean onlyAir){
		
		Block mBlock = getBlockInfrontOfPlayer(player, offset);
		Block mBlock2 = getBlockInfrontOfPlayer(player, offset+2);
		//Block mBlockM = getBlockInfrontOfPlayer(player, offset+1);
		String dir = getCardinalDirection(player);
		BlockFace bf1, bf2;
		
		if(dir == "N"){bf1 = BlockFace.EAST; bf2 = BlockFace.WEST;}
		else if(dir == "E"){bf1 = BlockFace.NORTH; bf2 = BlockFace.SOUTH;}
		else if(dir == "S"){bf1 = BlockFace.EAST; bf2 = BlockFace.WEST;}
		else if(dir == "W"){bf1 = BlockFace.NORTH; bf2 = BlockFace.SOUTH;}
		else if(dir == "NE"){bf1 = BlockFace.NORTH_WEST; bf2 = BlockFace.SOUTH_EAST;}
		else if(dir == "NW"){bf1 = BlockFace.NORTH_EAST; bf2 = BlockFace.SOUTH_WEST;}
		else if(dir == "SE"){bf1 = BlockFace.SOUTH_WEST; bf2 = BlockFace.NORTH_EAST;}//
		else if(dir == "SW"){bf1 = BlockFace.SOUTH_EAST; bf2 = BlockFace.NORTH_WEST;}//
		else{bf1 = null; bf2 = null;return null;}
		

			List<Block> tmpList= new ArrayList<Block>();
				
			Block b1 = mBlock.getRelative(bf1,1);
			Block b2 = mBlock.getRelative(bf2,1);

			Block b3 = mBlock2.getRelative(bf1, 2);
			Block b4 = mBlock2.getRelative(bf2, 2);

			/*
			Block b5 = mBlockM.getRelative(bf1);
			Block b6 = mBlockM.getRelative(bf2);
			Block b3m = mBlock2.getRelative(bf1, 1);
			Block b4m = mBlock2.getRelative(bf2, 1);
			*/
			
			
				if(onlyAir && mBlock2.getType() == Material.AIR){tmpList.add(mBlock2);}
				else if(!(onlyAir)){tmpList.add(mBlock2);}
				
				if(onlyAir && b1.getType() == Material.AIR){tmpList.add(b1);}
				else if(!(onlyAir)){tmpList.add(b1);}
				
				if(onlyAir && b2.getType() == Material.AIR){tmpList.add(b2);}
				else if(!(onlyAir)){tmpList.add(b2);}
				
				if(onlyAir && b3.getType() == Material.AIR){tmpList.add(b3);}
				else if(!(onlyAir)){tmpList.add(b3);}
				
				if(onlyAir && b4.getType() == Material.AIR){tmpList.add(b4);}
				else if(!(onlyAir)){tmpList.add(b4);}
				/*
				if(onlyAir && mBlockM.getType() == Material.AIR){tmpList.add(mBlockM);}
				else if(!(onlyAir)){tmpList.add(mBlockM);}
				
				if(onlyAir && b5.getType() == Material.AIR){tmpList.add(b5);}
				else if(!(onlyAir)){tmpList.add(b5);}
				
				if(onlyAir && b6.getType() == Material.AIR){tmpList.add(b6);}
				else if(!(onlyAir)){tmpList.add(b6);}
				
				if(onlyAir && b3m.getType() == Material.AIR){tmpList.add(b3m);}
				else if(!(onlyAir)){tmpList.add(b3m);}
				
				if(onlyAir && b4m.getType() == Material.AIR){tmpList.add(b4m);}
				else if(!(onlyAir)){tmpList.add(b4m);}
				 */
		
		return tmpList;
	}
	
	
    public static LivingEntity getTarget(Player player) {
	    int range = 60;
	    List<Entity> nearbyE = player.getNearbyEntities(range, range, range);
	    ArrayList<LivingEntity> livingE = new ArrayList<LivingEntity>();
	     
	    for (Entity e : nearbyE) {
	    	if (e instanceof LivingEntity) {
	    		livingE.add((LivingEntity) e);
	    	}
	    }
	     
	    LivingEntity target = null;
	    BlockIterator bItr = new BlockIterator(player, range);
	    Block block;
	    Location loc;
	    int bx, by, bz;
	    double ex, ey, ez;
	    // loop through player's line of sight
	    while (bItr.hasNext()) {
	    	block = bItr.next();
	    	bx = block.getX();
	    	by = block.getY();
	    	bz = block.getZ();
	    // check for entities near this block in the line of sight
	    	for (LivingEntity e : livingE) {
	    		loc = e.getLocation();
	    		ex = loc.getX();
	    		ey = loc.getY();
	    		ez = loc.getZ();
	    		if ((bx - .75 <= ex && ex <= bx + 1.75)
	    				&& (bz - .75 <= ez && ez <= bz + 1.75)
	    				&& (by - 1 <= ey && ey <= by + 2.5)) {
	    			// entity is close enough, set target and stop
	    			target = e;
	    			break;
	    		}
	    	}
	    }
	    return target;
     
    }
    
    public static List<Location> getCircle(Location loc, int radius, int height,
            boolean hollow, boolean sphere, int plusY) {
        List<Location> circleblocks = new ArrayList<Location>();
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
 
        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int z = cz - radius; z <= cz + radius; z++) {
                for (int y = (sphere ? cy - radius : cy); y < (sphere ? cy
                        + radius : cy + height); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z)
                            + (sphere ? (cy - y) * (cy - y) : 0);
 
                    if (dist < radius * radius
                            && !(hollow && dist < (radius - 1) * (radius - 1))) {
                        Location l = new Location(loc.getWorld(), x, y + plusY,
                                z);
                        circleblocks.add(l);
                    }
                }
            }
        }
 
        return circleblocks;
    }
    
    public static List<Block> getCircleBlocks(Location center, int radius,
            boolean hollow, boolean sphere) {
        List<Location> locs = getCircle(center, radius, radius, hollow, sphere, 0);
        List<Block> blocks = new ArrayList<Block>();
 
        for (Location loc : locs) {
            blocks.add(loc.getBlock());
        }
 
        return blocks;
    }
}
