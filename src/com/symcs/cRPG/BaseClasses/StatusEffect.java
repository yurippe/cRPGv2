package com.symcs.cRPG.BaseClasses;

import org.bukkit.entity.Player;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.Data.PlayerData;


public class StatusEffect{
	
		protected int duration;
		protected int timelimit;
		public enum Type {TIMED, CHARGE, CHARGE_LIMITED, CANCEL_ON_DAMAGE}
		public enum FadeCause{TIMEOUT, CHARGES_SPENT, DAMAGE_TAKEN, UNKNOWN}
		
		private Type type;
		protected Player player;
		protected cRPG plugin;
		protected PlayerData playerdata;
		
		public StatusEffect(int duration, Type type){
			this.duration = duration;
			this.type = type;
			if(type == Type.CHARGE_LIMITED){this.timelimit = 1;}
		}
		public StatusEffect(int duration){this(duration, Type.TIMED);}
		
		public StatusEffect(int charges, int timelimit){
			this.duration = charges;
			this.timelimit = timelimit;
			this.type = Type.CHARGE_LIMITED;
		}
		
		public int getDuration(){return this.duration;}
		public boolean tickDuration(){if(this.duration <= 1){this.duration = 0; return true;}else{this.duration--; return false;}}
		public boolean tickTimeLimit()
		{
			if(this.type == Type.CHARGE_LIMITED){
				if(this.timelimit <= 1){this.timelimit = 0; return true;}else{this.timelimit--; return false;}
			}else{if(this.duration <= 1){this.duration = 0; return true;}else{this.duration--; return false;}}
		}
		public void setDuration(int duration){this.duration = duration;}
		public int getTimeLimit(){if(this.type == Type.CHARGE_LIMITED){return this.timelimit;}else{return this.duration;}}
		public void setTimeLimit(int timelimit){if(this.type == Type.CHARGE_LIMITED){this.timelimit=timelimit;}else{this.duration=timelimit;}}
		public Type getType(){return this.type;}
		public boolean cancelOnDamage(){if(this.type == Type.CANCEL_ON_DAMAGE){return true;}else{return false;}}
		
		public boolean canMove(){return true;}
		public boolean canCast(){return true;}
		public boolean takeFallDamage(){return true;}
		public boolean canDie(){return true;}
		public double damageModifier(){return 1.0;}
		public double defenseModifier(){return 1.0;}
		
		public void onCast(){}
		public void onCast(PlayerData playerdata, cRPG plugin){this.playerdata=playerdata;this.player=playerdata.player;this.plugin=plugin;onCast();}
		public void onFade(){}
		public void onFade(FadeCause cause){onFade();}
		
		
}

