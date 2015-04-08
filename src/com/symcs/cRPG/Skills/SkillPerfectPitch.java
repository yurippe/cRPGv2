package com.symcs.cRPG.Skills;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.Tasks.Task;
import com.symcs.cRPG.utils.NMSFunctions;

public class SkillPerfectPitch extends Skill{

	public SkillPerfectPitch(cRPG plugin) {
		super(plugin);

		setSkillItem(new ItemStack(Material.JUKEBOX, 1));
		setSkillName("Perfect Pitch");
		setSkillDescription(Arrays.asList("Put out a music totem", "Periodicly heals allies"));
		setSkillCooldown(10);
		setSkillType(Skill.Type.TOTEM);
		
	}
	
	@Override
	public void Cast(){
		
		Location loc = this.player.getLocation();
		World w = this.player.getWorld();
		Block b = w.getBlockAt(loc);
		while(b.getType() == Material.AIR){
			b = b.getRelative(0, -1, 0);
		}
		

		plugin.getBlockManager().saveBlockState(b);
		b.setType(Material.JUKEBOX);
		setSkillBlock(b);
		
		List<Player> players = plugin.getPartyManager().getPartyMembersOrPlayer(this.player);
		
		new Task(this.plugin){
			
			private Block block;
			private int TickCount;
			private int MaxTickCount;
			private List<Player> playersAffected;
			private double radius;
			private double healAmount;
			
			public Task addArgs(int MaxTickCount, double radius, double healAmount, List<Player> affectedPlayers, Block block){
				this.block = block;
				this.MaxTickCount = MaxTickCount;
				this.TickCount = 0;
				this.radius = radius; //ignores height
				this.healAmount = healAmount;
				this.playersAffected = affectedPlayers;
				return this;
			}
			
			@Override
			public void cancelEarly(){
				this.TickCount = this.MaxTickCount;
			}
  			
  			@Override
  			public void run(){
  				
  				try
  				{
	  				if(TickCount <= MaxTickCount){
	  					//Also include the last tick
	  					if(!(playersAffected.isEmpty())){
		  					for(Player p: playersAffected){
		  						
		  		                double blockX = block.getLocation().getX();
		  		                double playerX = p.getLocation().getX();
		  		                double blockZ = block.getLocation().getZ();
		  		                double playerZ = p.getLocation().getZ();
		  		                
		  		                if(Math.abs(Math.abs(blockX) - Math.abs(playerX)) <= radius){
		  		                    if(Math.abs(Math.abs(blockZ) - Math.abs(playerZ)) <= radius) {
		  		                    		
		  		                    		PlayerClass c = plugin.getPlayerManager().getPlayer(p).getPlayerClass();
		  		                        	c.healPlayer(healAmount);
		  		                        	c.player.setFoodLevel(c.player.getFoodLevel() + 1);
		  		                        	NMSFunctions.sendIngameEffect(block.getLocation(), 2001, 133, p);
		
		  		                    	
		  		                     }
		  		                 }
		  						
		  						
		  					}
	  					}
	  					TickCount ++;
	  				}
  				}catch(Exception e){plugin.getBlockManager().restoreBlock(block);this.cancel();}
  				
  				if(TickCount >= MaxTickCount){
  				plugin.getBlockManager().restoreBlock(block);
  				this.cancel();
  				}
  			}
		}.addArgs(10, 5.0, 2.0, players, b).runTaskTimer(this.plugin, 0, 20);
		//Heals 2 hp every 20 ticks 10 times for every player in the players list within 5 blocks radius -phew-

		
		
	}

}
