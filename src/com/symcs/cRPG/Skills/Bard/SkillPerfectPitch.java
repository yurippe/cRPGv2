package com.symcs.cRPG.Skills.Bard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.BaseClasses.Skill;
import com.symcs.cRPG.Tasks.TaskHitboxRegister;
import com.symcs.cRPG.utils.NMSFunctions;
import com.symcs.cRPG.utils.Hitboxes.HitboxSphereBlock;

public class SkillPerfectPitch extends Skill{
	
	private Block block;

	public SkillPerfectPitch(cRPG plugin) {
		super(plugin);

		setSkillItem(new ItemStack(Material.JUKEBOX, 1));
		setSkillName("Perfect Pitch");
		setSkillDescription(Arrays.asList("Put out a music totem", "Periodicly heals allies"));
		setSkillCooldown(10);
		setSkillType(Skill.Type.TOTEM);
		
	}
	
	@Override
	public void onSkillHitPlayer(Player p){
  		PlayerClass c = plugin.getPlayerManager().getPlayer(p).getPlayerClass();
      	c.healPlayer(3);
      	c.getPlayer().setFoodLevel(c.player.getFoodLevel() + 1);
      	NMSFunctions.sendIngameEffect(block.getLocation(), 2001, 133, p);
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
		this.block = b;
		
		List<Player> players = plugin.getPartyManager().getPartyMembersOrPlayer(this.player);
		List<LivingEntity> li = new ArrayList<LivingEntity>();
		for(Player p:players){
			li.add((LivingEntity) p);
		}
		HitboxSphereBlock hitbox = new HitboxSphereBlock(plugin, b, 5);
		hitbox.limitSet(li);
		
		TaskHitboxRegister onhit = new TaskHitboxRegister(plugin, 10, hitbox, this, b);
		onhit.runTaskTimer(this.plugin, 0, 20);

		
		
	}

}
