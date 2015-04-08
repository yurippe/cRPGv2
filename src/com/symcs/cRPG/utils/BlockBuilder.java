package com.symcs.cRPG.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class BlockBuilder {
	private Block curblock;
	private Player p;
	private List<Block> blocks = new ArrayList<Block>();
	
	public BlockBuilder(Player p, Block b){
		this.p = p;
		this.curblock = b;
		this.blocks.add(b);
	}
	
	public void goUp(){goUp(1);}
	public void goUp(int offset){
		Block nb = curblock.getRelative(BlockFace.UP, offset);
		blocks.add(nb);
		curblock = nb;
	}
	
	public void goDown(){goDown(1);}
	public void goDown(int offset){
		Block nb = curblock.getRelative(BlockFace.DOWN, offset);
		blocks.add(nb);
		curblock = nb;
	}
	
	public void goLeft(){goLeft(1);}
	public void goLeft(int offset){
		Block nb = westOfBlock(this.p, curblock, offset);
		blocks.add(nb);
		curblock = nb;
	}
	
	public void goRight(){goRight(1);}
	public void goRight(int offset){
		Block nb = eastOfBlock(this.p, curblock, offset);
		blocks.add(nb);
		curblock = nb;
	}
	
	public void goForward(){goForward(1);}
	public void goForward(int offset){
		Block nb = northOfBlock(this.p, curblock, offset);
		blocks.add(nb);
		curblock = nb;
	}
	
	public void goBackwards(){goBackwards(1);}
	public void goBackwards(int offset){
		Block nb = southOfBlock(this.p, curblock, offset);
		blocks.add(nb);
		curblock = nb;
	}
	
	public void skipUp(){skipUp(1);}
	public void skipUp(int offset){
		Block nb = curblock.getRelative(BlockFace.UP, offset);
		curblock = nb;
	}
	
	public void skipDown(){skipDown(1);}
	public void skipDown(int offset){
		Block nb = curblock.getRelative(BlockFace.DOWN, offset);
		curblock = nb;
	}
	
	public void skipLeft(){skipLeft(1);}
	public void skipLeft(int offset){
		Block nb = westOfBlock(this.p, curblock, offset);
		curblock = nb;
	}
	
	public void skipRight(){skipRight(1);}
	public void skipRight(int offset){
		Block nb = eastOfBlock(this.p, curblock, offset);
		curblock = nb;
	}
	
	public void skipForward(){skipForward(1);}
	public void skipForward(int offset){
		Block nb = northOfBlock(this.p, curblock, offset);
		curblock = nb;
	}
	
	public void skipBackwards(){skipBackwards(1);}
	public void skipBackwards(int offset){
		Block nb = southOfBlock(this.p, curblock, offset);
		curblock = nb;
	}
	
	
	public List<Block> getBlocks(){
		return this.blocks;
	}
	
	
	private Block northOfBlock(Player relativetoplayer, Block relativetoblock, int offset){
		BlockFace f = getCardinalDirection(relativetoplayer, 0);
		return relativetoblock.getRelative(f, offset);
	}
	
	private Block southOfBlock(Player relativetoplayer, Block relativetoblock, int offset){
		BlockFace f = getCardinalDirection(relativetoplayer, 180);
		return relativetoblock.getRelative(f, offset);
	}

	private Block westOfBlock(Player relativetoplayer, Block relativetoblock, int offset){
		BlockFace f = getCardinalDirection(relativetoplayer, 90);
		return relativetoblock.getRelative(f, offset);
	}
	
	private Block eastOfBlock(Player relativetoplayer, Block relativetoblock, int offset){
		BlockFace f = getCardinalDirection(relativetoplayer, 270);
		return relativetoblock.getRelative(f, offset);
	}
	
	private BlockFace getCardinalDirection(Player player, double offset) {
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
