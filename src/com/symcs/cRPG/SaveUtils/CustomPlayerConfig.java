package com.symcs.cRPG.SaveUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.BaseClasses.PlayerClass;
import com.symcs.cRPG.Data.PlayerData;

public class CustomPlayerConfig {
	
	private cRPG plugin;
	private File playerYmlFile;
	private FileConfiguration playerConfig;
	private Player player;
	
	public CustomPlayerConfig(cRPG plugin, Player player){
		
		File dirCheck = new File(plugin.getDataFolder() + File.separator +  "playerdata");
		if(!(dirCheck.isDirectory())){
			dirCheck.mkdirs();
		}
		
		this.playerYmlFile = new File(plugin.getDataFolder() + 
				File.separator +  "playerdata" + File.separator + player.getUniqueId().toString() + ".yml");
		
		try {this.playerYmlFile.createNewFile();} catch (IOException e) {e.printStackTrace();}
		
		this.playerConfig = YamlConfiguration.loadConfiguration(this.playerYmlFile);
	}
	
	public void save(){
		try
		{
			this.playerConfig.save(this.playerYmlFile);
			
		}catch(IOException e){e.printStackTrace();}
		
	}
	
	public void load(){
		try {
			this.playerConfig.load(this.playerYmlFile);
		} catch (FileNotFoundException e) {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			plugin.getLogger().warning("Invalid file configuration for player: " + player.getUniqueId().toString());
		}
	}
	
	public FileConfiguration getConfig(){
		return this.playerConfig;
	}
	
	
	//////////////////////////////////////////////
	////////SAVE//////////////
	/////////////////////////////////////////////
	public void savePlayerData(PlayerData playerdat){
		getConfig().set("SelectedClass", playerdat.getPlayerClass().getClassName());
		
		save();
	}
	public void saveClass(PlayerClass aclass){
		getConfig().set(aclass.getClassName() + ".level", aclass.getLevel());
		getConfig().set(aclass.getClassName() + ".weapon.tier", aclass.getWeaponTier());
		
		save();
	}
	
	
	////////////////////////////////////////////////
	////////LOAD//////////////
	/////////////////////////////////////////////	
	public void loadPlayerData(PlayerData playerdat){
		playerdat.setPlayerClass(getConfig().getString("SelectedClass", "Mage"));
	}
	public void loadClass(PlayerClass aclass){
		aclass.setLevel(getConfig().getInt(aclass.getClassName() + ".level", 1));
		aclass.setWeaponTier(getConfig().getInt(aclass.getClassName() + ".weapon.tier", 1));
	}

}
