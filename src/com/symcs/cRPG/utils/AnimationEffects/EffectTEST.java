package com.symcs.cRPG.utils.AnimationEffects;


import com.symcs.cRPG.cRPG;

public class EffectTEST extends Effect{

	public EffectTEST(cRPG plugin) {
		super(plugin, 20);
	}

	@Override
	public void effect(int tick) {
		
		this.plugin.getLogger().info(Integer.toString(tick) + " tick of Test effect");
		
	}

	@Override
	public void onFade() {
		
	}

}
