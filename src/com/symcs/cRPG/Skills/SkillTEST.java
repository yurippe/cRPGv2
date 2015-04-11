package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.utils.RelativeBlocks;


public class SkillTEST extends Skill{

	public SkillTEST(cRPG plugin) {
		super(plugin);
		
		setSkillItem(new ItemStack(Material.GHAST_TEAR));
		setSkillName("Test Skill");
		setSkillDescription(Arrays.asList("Test all the things"));
		setSkillCooldown(0);
	}
	
	public void log(String s){
		plugin.getLogger().info(s);
	}
	
	@Override
	public void Cast(){
		
		Block b = RelativeBlocks.frontOfPlayer(this.player, 1);
		b.setType(Material.GOLD_BLOCK);
		log("BLOCK: " + " X:" + Integer.toString(b.getX()) + " Y:" + Integer.toString(b.getY()) + " Z:" + Integer.toString(b.getZ()));
		Chunk c = b.getChunk();
		log("CHUNK:" + " X:" + Integer.toString(c.getX()) + " Z:" + Integer.toString(c.getZ()));
		
		//int guessedCalcX = ((b.getX() - (b.getX() % 16))/16);
		//int guessedCalcZ = ((b.getZ() - (b.getZ() % 16))/16);
		int guessedCalcX; int guessedCalcZ;
		guessedCalcX = Math.floorDiv(b.getX(), 16);
		guessedCalcZ = Math.floorDiv(b.getZ(), 16);
		
		log("GUESS:" + " X:" + Integer.toString(guessedCalcX) + " Z:" + Integer.toString(guessedCalcZ));
		
		Block gb = c.getBlock(b.getX() % 16, b.getY(), b.getZ()%16);
		
		log("GUESS_BLOCK:" + " X:" + Integer.toString(gb.getX()) + " Y:" + Integer.toString(gb.getY()) + " Z:" + Integer.toString(gb.getZ()));
		/*Block b = RelativeBlocks.frontOfPlayer(this.player,3);
		b.setType(Material.STONE);

		BlockBuilder bb = new BlockBuilder(this.player, b);
		
		bb.goRight();
		bb.goRight();
		bb.goUp();
		bb.goUp();
		bb.goLeft();
		bb.goUp();
		bb.skipUp();
		bb.goUp();
		
		for(Block ab:bb.getBlocks()){
			ab.setType(Material.GOLD_BLOCK);
		}*/
		
	}

	
}