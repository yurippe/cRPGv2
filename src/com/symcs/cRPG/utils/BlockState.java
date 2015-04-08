package com.symcs.cRPG.utils;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import com.symcs.cRPG.cRPG;

public class BlockState {
		
		private Block block;
		private Material type;
		private Biome biome;
		private byte data;
		private boolean wasSkillBlock;
		private cRPG plugin;
		
		@SuppressWarnings("deprecation")
		public BlockState(Block block, cRPG plugin){
			this.plugin = plugin;
			this.block = block;
			this.type = block.getType();
			this.data = block.getData();
			this.biome = block.getBiome();
			this.wasSkillBlock = block.hasMetadata("SkillBlock");
		}
		
		public Chunk getChunk(){
			return this.block.getChunk();
		}
		
		public Material getType(){
			return this.type;
		}
		public Block getBlock(){
			return this.block;
		}

		@SuppressWarnings("deprecation")
		public void update(){
			this.block.setType(this.type);
			this.block.setData(this.data);
			if(!(this.block.getBiome() == this.biome)){
				this.block.setBiome(this.biome);
			}
			if(!(this.wasSkillBlock)){
				if(this.block.hasMetadata("SkillBlock")){
					this.block.removeMetadata("SkillBlock", this.plugin);
				}
			}
		}
}
