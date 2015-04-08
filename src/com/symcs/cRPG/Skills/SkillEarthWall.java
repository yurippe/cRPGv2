package com.symcs.cRPG.Skills;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.Tasks.Task;
import com.symcs.cRPG.utils.HelperFunctions;

public class SkillEarthWall extends Skill{

	public SkillEarthWall(cRPG plugin) {
		super(plugin);
		setSkillItem(new ItemStack(Material.NETHER_BRICK));
		setSkillName("Earth Wall");
		setSkillDescription(Arrays.asList("Protect yourself with", "the element of earth"));
		setSkillCooldown(15);
	}
	
	@Override
	public void Cast(){
		
		int wRadius = 1;
		int wHeight = 3;
		Material wMaterial = Material.DIRT;
		int ticksDuration = 40;
		
		//List<List<Block>> getWallBlocksInfrontOfPlayer(Player player, double offset, int blockRadius, int height, boolean onlyAir){	
		List<List<Block>> blocks = HelperFunctions.getWallBlocksInfrontOfPlayer(this.player, 2, wRadius, wHeight, true);
		
		for(int i=0; i<blocks.size(); i++){
			List<Block> line = blocks.get(i);
			for(Block b: line){
				plugin.getBlockManager().saveBlockState(b);
				b.setType(wMaterial);
				setSkillBlock(b);
				//b.setMetadata("SkillBlock", new FixedMetadataValue(plugin, this));
			}
		}
		
		new Task(this.plugin){
			
			private List<List<Block>> blocks;
			private int curIndex = 0;
			
			public Task addArgs(List<List<Block>> blocks){
				this.blocks = blocks;
				this.curIndex = 0;
				return this;
			}
			
  			
  			@Override
  			public void run(){
  				
  					if(this.curIndex < ((double)this.blocks.size()/2.0)){ //Change to if(this.curIndex < this.blocks.size())
  																		  //if you only want to shave off from the bottom
  						List<Block> line = this.blocks.get(this.curIndex);
  						for(Block b: line){
  							if(b.hasMetadata("SkillBlock")){
  							plugin.getBlockManager().restoreBlock(b); //b.setType(Material.AIR);
  							//b.removeMetadata("SkillBlock", plugin);
  							}
  						}
  						//takes off the top as well
  						List<Block> line2 = this.blocks.get(this.blocks.size()-(this.curIndex + 1));
  						for(Block b2: line2){
  							if(b2.hasMetadata("SkillBlock")){
  							plugin.getBlockManager().restoreBlock(b2);//b2.setType(Material.AIR);
  							//b2.removeMetadata("SkillBlock", plugin);
  							}
  						}
  						this.curIndex ++;
  						
  					
  					}else{this.cancel();}
  					
  				
  			}
  	
	}.addArgs(blocks).runTaskTimer(this.plugin, ticksDuration, 10);
		
	}

}
