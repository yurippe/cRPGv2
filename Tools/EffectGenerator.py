def generateNewEffect(name):
    x="""
// Put this in its own class and call it 'Effect%s'
package com.symcs.cRPG.utils.AnimationEffects;


import org.bukkit.Location;

import com.symcs.cRPG.cRPG;

public class Effect%s extends Effect{

	public Effect%s(cRPG plugin, int Ticks, Location loc) {
		super(plugin, 20);
		setLocation(loc);
	}

	@Override
	public void effect(int tick) {
		
		
		
	}

	@Override
	public void onFade() {
		
	}

}"""%(name, name, name)
    return x

nam = raw_input("Name:")
data = generateNewEffect(nam)
print data
with open("Effect" + nam + ".java", "w") as f:
    f.write(data)

raw_input("Press enter to exit...")
