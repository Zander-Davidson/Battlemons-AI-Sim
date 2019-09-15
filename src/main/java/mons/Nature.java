package mons;

import java.util.HashMap;

public enum Nature {
	Hardy, Lonely, Adamant, Naughty, Brave, Bold, Docile, Impish, Lax, Relaxed, Modest, Mild, Bashful, Rash, Quiet,
	Calm, Gentle, Careful, Quirky, Sassy, Timid, Hasty, Jolly, Naive, Serious;

	// HashMap<Name of Nature, {stat increased, stat decreased}>
	public HashMap<Nature, Stat[]> Effect = new HashMap<Nature, Stat[]>() {
		{
			int i = 0;
			Nature[] n = Nature.values();
			for (Stat sPlus : Stat.values()) {
				if (!sPlus.equals(Stat.HP)) {
					for (Stat sMinus : Stat.values()) {
						if (!sMinus.equals(Stat.HP)) {
							Effect.put(n[i], new Stat[] { sPlus, sMinus });
							i++;
						}
					}
				}
			}
		}
	};
}
