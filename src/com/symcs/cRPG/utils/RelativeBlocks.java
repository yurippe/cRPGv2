package com.symcs.cRPG.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


public class RelativeBlocks {
	
	public RelativeBlocks(){
		
	}
	
	//Gets the block in front of a player at FEET level
	public static Block frontOfPlayer(Player p){return frontOfPlayer(p, 1);}
	public static Block frontOfPlayer(Player p, double offset){
		
		Vector dir = p.getLocation().getDirection().setY(0).normalize();
		Vector loc = p.getLocation().toVector().toBlockVector();
		
		Location bloc = loc.add(dir.multiply(offset)).toLocation(p.getWorld());
		return bloc.getBlock();
	}
	
	//Gets the block in North of another block where North is always the direction a player is facing
	public static Block northOfBlock(Player relativetoplayer, Block relativetoblock){return northOfBlock(relativetoplayer, relativetoblock, 1);}
	public static Block northOfBlock(Player relativetoplayer, Block relativetoblock, int offset){
		BlockFace f = getCardinalDirection(relativetoplayer, 0);
		return relativetoblock.getRelative(f, offset);
	}
	
	//Gets the block in South of another block where North is always the direction a player is facing
	public static Block southOfBlock(Player relativetoplayer, Block relativetoblock){return southOfBlock(relativetoplayer, relativetoblock, 1);}
	public static Block southOfBlock(Player relativetoplayer, Block relativetoblock, int offset){
		BlockFace f = getCardinalDirection(relativetoplayer, 180);
		return relativetoblock.getRelative(f, offset);
	}

	//Gets the block in West of another block where North is always the direction a player is facing
	public static Block westOfBlock(Player relativetoplayer, Block relativetoblock){return westOfBlock(relativetoplayer, relativetoblock, 1);}
	public static Block westOfBlock(Player relativetoplayer, Block relativetoblock, int offset){
		BlockFace f = getCardinalDirection(relativetoplayer, 90);
		return relativetoblock.getRelative(f, offset);
	}
	
	//Gets the block in East of another block where North is always the direction a player is facing
	public static Block eastOfBlock(Player relativetoplayer, Block relativetoblock){return eastOfBlock(relativetoplayer, relativetoblock, 1);}
	public static Block eastOfBlock(Player relativetoplayer, Block relativetoblock, int offset){
		BlockFace f = getCardinalDirection(relativetoplayer, 270);
		return relativetoblock.getRelative(f, offset);
	}
	
	public static BlockFace getCardinalDirection(Player player, double offset) {
		double rotation = (player.getLocation().getYaw() - (90+offset)) % 360;
		if (rotation < 0) {
		rotation += 360.0;
		}
		if (0 <= rotation && rotation < 22.5) {
		return BlockFace.WEST;//N
		} else if (22.5 <= rotation && rotation < 67.5) {
		return BlockFace.NORTH_WEST;//NE
		} else if (67.5 <= rotation && rotation < 112.5) {
		return BlockFace.NORTH;//E
		} else if (112.5 <= rotation && rotation < 157.5) {
		return BlockFace.NORTH_EAST;//SE
		} else if (157.5 <= rotation && rotation < 202.5) {
		return BlockFace.EAST;//S
		} else if (202.5 <= rotation && rotation < 247.5) {
		return BlockFace.SOUTH_EAST;//SW
		} else if (247.5 <= rotation && rotation < 292.5) {
		return BlockFace.SOUTH;//W
		} else if (292.5 <= rotation && rotation < 337.5) {
		return BlockFace.SOUTH_WEST;//NW
		} else if (337.5 <= rotation && rotation < 360.0) {
		return BlockFace.WEST;//N
		} else {
		return null;
		}
	}
	

}
