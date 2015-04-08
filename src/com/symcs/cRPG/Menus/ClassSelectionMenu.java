package com.symcs.cRPG.Menus;


import com.symcs.cRPG.cRPG;
import com.symcs.cRPG.MenuItems.ChangeClassBardItem;
import com.symcs.cRPG.MenuItems.ChangeClassGuardianItem;
import com.symcs.cRPG.MenuItems.ChangeClassHunterItem;
import com.symcs.cRPG.MenuItems.ChangeClassKnightItem;
import com.symcs.cRPG.MenuItems.ChangeClassMageItem;
import com.symcs.cRPG.MenuItems.ChangeClassRogueItem;
import com.symcs.cRPG.MenuItems.TestItem;

import ninja.amp.ampmenus.menus.ItemMenu;

public class ClassSelectionMenu extends ItemMenu{

	public ClassSelectionMenu(cRPG plugin) {
		super("Class selection", Size.THREE_LINE, plugin);
		//0-8
		//9-18
		//18-27
		setItem(10, new ChangeClassMageItem(plugin));
		setItem(11, new ChangeClassHunterItem(plugin));
		setItem(12, new ChangeClassBardItem(plugin));

		setItem(13, new TestItem(plugin));
		
		setItem(14, new ChangeClassKnightItem(plugin));
		setItem(15, new ChangeClassGuardianItem(plugin));
		setItem(16, new ChangeClassRogueItem(plugin));
	}

}
