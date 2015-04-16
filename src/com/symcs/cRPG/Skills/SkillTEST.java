package com.symcs.cRPG.Skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.utils.AnimationEffects.EffectBeam;


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
		
		EffectBeam beam = new EffectBeam(plugin, 10, player);
		beam.play(3);
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