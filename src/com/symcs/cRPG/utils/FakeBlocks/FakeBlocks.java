package com.symcs.cRPG.utils.FakeBlocks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.block.Block;

import com.symcs.cRPG.cRPG;

public class FakeBlocks {
	
	//The idea was to save chunks and update chunks with player.sendChunkUpdate but no idea how to get the bytedata buffer for this to work
	
	@SuppressWarnings("unused")
	private cRPG plugin;
	@SuppressWarnings("unused")
	private List<Block> blocks = new ArrayList<Block>();
	@SuppressWarnings("unused")
	private List<Chunk> chunks = new ArrayList<Chunk>();

	public FakeBlocks(cRPG plugin){
		this.plugin = plugin;
	}
	
	public void addBlock(Block block){

	}
	
	public void WorldToChunkLoc(Block w){}

}
