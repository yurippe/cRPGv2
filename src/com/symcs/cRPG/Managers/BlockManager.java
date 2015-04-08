package com.symcs.cRPG.Managers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.utils.BlockState;
import com.symcs.cRPG.BaseClasses.Skill;

public class BlockManager {
	
	private HashMap<Block, BlockState> modifiedBlocks = new HashMap<Block, BlockState>(); //<Block, OriginalState>//
	private cRPG plugin;
	
	public BlockManager(cRPG plugin){
		this.plugin = plugin;
		
	}
	
	
	public Block modifyBlock(Block block){
		if(!(modifiedBlocks.containsKey(block))){
		//if it is already here, modifying it further wont change the fact that the original block is saved here, therefore keep the first save!
		modifiedBlocks.put(block, new BlockState(block, plugin));
		}
		return block;
	}
	
	public void forceSaveBlockState(Block block){
		modifiedBlocks.put(block, new BlockState(block, plugin));
	}
	public boolean softSaveBlockState(Block block){
		if(!(modifiedBlocks.containsKey(block))){
			modifiedBlocks.put(block, new BlockState(block, plugin));
			return true;
		}
		return false;
	}
	
	public void saveBlockState(Block block){
		softSaveBlockState(block);
	}
	
	public void restoreBlock(Block block){
		if(modifiedBlocks.containsKey(block)){
			modifiedBlocks.get(block).update();
			modifiedBlocks.remove(block);
		}
	}

	public void restoreAllBlocks() {
		Iterator<Map.Entry<Block,BlockState>> iter = modifiedBlocks.entrySet().iterator();
		while(iter.hasNext()){
			iter.next().getValue().update();
			iter.remove();
		}
		/*
		for(Block b: modifiedBlocks.keySet()){
			modifiedBlocks.get(b).update();
			modifiedBlocks.remove(b);
		}*/
	}
	
	public HashMap<Block, BlockState> getModifiedBlocks(){
		return this.modifiedBlocks;
	}
	
	public void setSkillBlock(Block b, Skill skill){
		b.setMetadata("SkillBlock", new FixedMetadataValue(plugin, skill));
	}

	public void unsetSkillBlock(Block b){
		b.removeMetadata("SkillBlock", plugin);
	}
	

}
