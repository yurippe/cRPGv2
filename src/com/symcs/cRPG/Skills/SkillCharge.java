package com.symcs.cRPG.Skills;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.symcs.cRPG.StatusEffects.StatusEffectFallDamageImmunity;
import com.symcs.cRPG.StatusEffects.StatusEffectStun;
import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.Tasks.Task;
import com.symcs.cRPG.Tasks.TaskOnNext;
import com.symcs.cRPG.utils.ConditionalAction;
import com.symcs.cRPG.utils.HelperFunctions;
import com.symcs.cRPG.utils.Hitboxes.HitboxSquareFront;


public class SkillCharge extends Skill{

	public SkillCharge(cRPG plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
		setSkillItem(new ItemStack(Material.REDSTONE_COMPARATOR));
		setSkillName("Charge");
		setSkillDescription(Arrays.asList("Charge at a location", "and knock up blocks", "when you land"));
		setSkillCooldown(3);
	}
	
	@Override
	public void onSkillHitPlayer(Player p){
		plugin.getPlayerManager().getPlayer(p).getStatusEffectManager().addStatusEffect(new StatusEffectStun(10, p));
		p.sendMessage("You got hit by Charge!");
	}
	
	@Override
	public void Cast(){

		plugin.getPlayerManager().getPlayer(this.player).getStatusEffectManager().addStatusEffect(new StatusEffectFallDamageImmunity(1,5));
		
		
		Vector removedSpeed = new Vector().zero().subtract(this.player.getVelocity());
		Vector playerDirection = this.player.getLocation().getDirection();
		double distance = 3;
		playerDirection.setY(1.0/distance); //have predictable y axis
		
		this.player.setVelocity(removedSpeed.add(playerDirection.multiply(distance)));

		
		new TaskOnNext(plugin, new ConditionalAction(this.plugin, this.player, this){
			

			@SuppressWarnings("deprecation")
			@Override
			public boolean If() {
				if(this.player.isOnGround()){return true;}
					
				return false;
			}

			@Override
			public void Then() {
				
				int wHeight = 3;
				Material wMaterial = Material.DIRT;
				
				List<Block> blocks = HelperFunctions.getConeBlocksInfrontOfPlayer(this.player, 2.0, false);
				
				HitboxSquareFront  Hitbox = new HitboxSquareFront(this.plugin, this.player, 4, 3, 2, 1);
				Hitbox.registerHits(this.skill); //Registers hits with the damage manager

				
				
				
				new Task(this.plugin){
					
					private List<Block> blocks;
					private int curIndex = 0;
					private int height;
					private Material wMat;
					private Skill skill;
					public Task addArgs(List<Block> blocks, int height, Material wMat, Skill skill){
						this.blocks = blocks;
						this.height = height;
						this.curIndex = 0;
						this.wMat = wMat;
						this.skill = skill;
						return this;
					}
					
		  			
		  			@Override
		  			public void run(){
		  				
		  					if(this.curIndex <= height){
		  						if(this.curIndex == 0){
		  						for(Block b: blocks){
		  							plugin.getBlockManager().saveBlockState(b);
		  							plugin.getBlockManager().setSkillBlock(b, skill);
		  							b.setType(this.wMat);
		  							}
		  						}else if(this.curIndex == height){
		  							for(Block b: blocks){
		  							plugin.getBlockManager().restoreBlock((b.getRelative(0, this.curIndex-1, 0)));
		  							}
		  						}
		  						else{
		  							for(Block b: blocks){
		  								plugin.getBlockManager().restoreBlock((b.getRelative(0, this.curIndex-1, 0)));
		  								Block bb = b.getRelative(0, this.curIndex, 0);
		  								plugin.getBlockManager().saveBlockState(bb);
		  								plugin.getBlockManager().setSkillBlock(bb, skill);
			  							bb.setType(this.wMat);
			  							}
		  						}

		  						this.curIndex ++;
		  					}
		  					
		  					else{this.cancel();}
		  					
		  				
		  			}
		  	
			}.addArgs(blocks, wHeight, wMaterial, this.skill).runTaskTimer(this.plugin, 2, 2);
				
				
			}}, 5, 100);
	}

}
