package com.symcs.cRPG.utils.AnimationEffects;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.utils.NMSFunctions;

public class EffectBeam extends Effect{


	private Player player;

	public EffectBeam(cRPG plugin, int Ticks, Player p) {
		super(plugin, 20);
		this.player = p;
	}

	@Override
	public void effect(int tick) {
		
        Location location = player.getLocation().add(new Vector(0,1,0));
        BlockIterator blocksToAdd = new BlockIterator(location, 1D, 10);
 
        while (blocksToAdd.hasNext()) {
 
            for(Player p: plugin.getServer().getOnlinePlayers()){
            	NMSFunctions.sendIngameEffect(blocksToAdd.next().getLocation(), Effect.EffectType.BLOCK_BREAK.getEffectID(), 152, p);
            }
        }
		
		
		
	}

	@Override
	public void onFade() {
		
	}

}