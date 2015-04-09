package com.symcs.cRPG;

import ninja.amp.ampmenus.MenuListener;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.symcs.cRPG.Commands.*;
import com.symcs.cRPG.Data.PlayerData;
import com.symcs.cRPG.Managers.*;
import com.symcs.cRPG.listeners.*;


public final class cRPG extends JavaPlugin {
	private PartyManager partyManager;
	private BlockManager blockManager;
	private ProjectileManager projectileManager;
	private TaskManager taskManager;
	private PlayerManager playerManager;
	private MenuManager menuManager;
	private ListenerManager listenerManager;
	private DamageManager damageManager;
	private PremiumManager premiumManager;
	
	@Override
    public void onEnable() {
		setUpCommandExecutors();
		setUpManagers();
		setUpListeners();
		new MenuListener(this);
		
    	for(Player p: getServer().getOnlinePlayers()){
    	PlayerData pdat = new PlayerData(p, this);
    	this.playerManager.addPlayer(p, pdat);
        pdat.onEnable();
    	}
	}
	
	 @Override
	 public void onDisable() {
		 
	    	this.taskManager.stopAllTasks();
	    	this.blockManager.restoreAllBlocks();
	    	for(PlayerData dat: this.playerManager.getPlayers().values()){
	    		dat.onDisable();
	    	}
	    	
	 }
	 
	 private void setUpCommandExecutors(){
	    	this.getCommand("partyinvite").setExecutor(new CommandPartyInvite(this));
	    	this.getCommand("partyinviteaccept").setExecutor(new CommandPartyInviteAccept(this));
	    	this.getCommand("partyinvitedecline").setExecutor(new CommandPartyInviteDecline(this));
	    }
	   
	 private void setUpListeners(){
	    	listenerManager.addListener("PlayerJoin", new PlayerJoinEventListener(this));
	    	listenerManager.addListener("PlayerQuit", new PlayerQuitEventListener(this));
	    	listenerManager.addListener("PlayerItemHeld", new PlayerItemHeldEventListener(this));
	    	listenerManager.addListener("PlayerInteract", new PlayerInteractEventListener(this));
	    	listenerManager.addListener("PlayerDropItem", new PlayerDropItemEventListener(this));
	    	listenerManager.addListener("PlayerPickupItem", new PlayerPickupItemEventListener(this));
	    	
	    	listenerManager.addListener("PlayerRespawn", new PlayerRespawnEventListener(this));
	    	listenerManager.addListener("PlayerMove", new PlayerMoveEventListener(this));
	    	
	    	listenerManager.addListener("InventoryClick", new InventoryClickEventListener(this));
	    	
	    	listenerManager.addListener("EntityDamageByEntity", new EntityDamageByEntityEventListener(this));
	    	listenerManager.addListener("EntityDamage", new EntityDamageEventListener(this));
	    	listenerManager.addListener("EntityExplode", new EntityExplodeEventListener(this));
	    	listenerManager.addListener("EntityDeath", new EntityDeathEventListener(this));
	    	
	    	listenerManager.addListener("ProjectileHit", new ProjectileHitEventListener(this));
	    	
	    	listenerManager.addListener("CustomDamage", new CustomDamageEventListener(this));
	    }
	 
	 private void setUpManagers(){
		 this.partyManager = new PartyManager(this);
		 this.blockManager = new BlockManager(this);
		 this.projectileManager = new ProjectileManager(this);
		 this.taskManager = new TaskManager(this);
		 this.playerManager = new PlayerManager(this);
		 this.menuManager = new MenuManager(this);
		 this.damageManager = new DamageManager(this);
		 this.listenerManager = new ListenerManager(this);
		 this.premiumManager = new PremiumManager(this);
	 }
	 
	 public PartyManager getPartyManager(){return this.partyManager;}
	 public BlockManager getBlockManager(){return this.blockManager;}
	 public ProjectileManager getProjectileManager(){return this.projectileManager;}
	 public TaskManager getTaskManager(){return this.taskManager;}
	 public PlayerManager getPlayerManager(){return this.playerManager;}
	 public MenuManager getMenuManager(){return this.menuManager;}
	 public ListenerManager getListenerManager(){return this.listenerManager;}
	 public DamageManager getDamageManager(){return this.damageManager;}
	 public PremiumManager getPremiumManager(){return this.premiumManager;}

}
