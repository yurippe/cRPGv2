package com.symcs.cRPG.Menus;

import ninja.amp.ampmenus.items.CloseItem;
import ninja.amp.ampmenus.menus.ItemMenu;

import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.MenuItems.PartyPlayerInfoItem;
import com.symcs.cRPG.MenuItems.PartyPlayerKickItem;
import com.symcs.cRPG.MenuItems.PartyPlayerLeaveItem;

public class PartyMenu extends ItemMenu{

	public PartyMenu(cRPG plugin) {
		super("Party management", Size.SIX_LINE, plugin);
		//0-8
		//9-17
		//18-26
		//27-35
		//36-44
		//45-53
		setItem(0, new PartyPlayerInfoItem(plugin, 0));
		setItem(1, new PartyPlayerKickItem(plugin, 0));
		
		setItem(9, new PartyPlayerInfoItem(plugin, 1));
		setItem(10, new PartyPlayerKickItem(plugin, 1));
		
		setItem(18, new PartyPlayerInfoItem(plugin, 2));
		setItem(19, new PartyPlayerKickItem(plugin, 2));
		
		setItem(27, new PartyPlayerInfoItem(plugin, 3));
		setItem(28, new PartyPlayerKickItem(plugin, 3));
		
		setItem(36, new PartyPlayerInfoItem(plugin, 4));
		setItem(37, new PartyPlayerKickItem(plugin, 4));
		
		setItem(45, new PartyPlayerLeaveItem(plugin));
		setItem(53, new CloseItem());
	}

}
