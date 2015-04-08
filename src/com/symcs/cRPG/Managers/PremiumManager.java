package com.symcs.cRPG.Managers;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.Data.PlayerData;

public class PremiumManager {
	
	@SuppressWarnings("unused")
	private cRPG plugin;

	public PremiumManager(cRPG plugin){
		this.plugin = plugin;
	}

	public boolean isClassUnlocked(PlayerData playerData, PlayerClass aclass) {
		return true;
	}
}
