package com.symcs.cRPG.utils.AnimationEffects;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.utils.HelperFunctions;

public class EffectInferno extends Effect{
	

	public EffectInferno(cRPG plugin, int Ticks, Location loc) {
		super(plugin, Ticks);
		setLocation(loc);
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public void effect(int tick) {
		
		byte nb = 0;
		List<Block> bs = HelperFunctions.getCircleBlocks(getLocation(), 4, true, true);
		for(int i=0; i < bs.size(); i++){
			Block b = bs.get(i);
			for(Player p: plugin.getServer().getOnlinePlayers()){
				if(b.getType() == Material.AIR && ((tick % 2) == 0)){
					p.sendBlockChange(b.getLocation(), Material.FIRE.getId(), nb);
				}
				else{p.sendBlockChange(b.getLocation(), b.getType().getId(), nb);}
			}
		}

		Block b = this.getLocation().getBlock();
		b.setType(Material.DIAMOND_BLOCK);
		
		plugin.getLogger().info("Infenro effect ticks " + Integer.toString(tick));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onFade() {
		byte nb = 0;
		for(Block b : HelperFunctions.getCircleBlocks(getLocation(), 4, true, true)){
			for(Player p: plugin.getServer().getOnlinePlayers()){
					p.sendBlockChange(b.getLocation(), b.getType().getId(), nb);
			}
		}
		
		plugin.getLogger().info("Infenro effect fades");
	}

}
