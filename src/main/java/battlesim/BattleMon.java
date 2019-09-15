package battlesim;

import java.util.ArrayList;
import java.util.HashMap;

import mons.Ability;
import mons.Mon;
import mons.Move;
import mons.Nature;
import mons.Stat;

public class BattleMon extends Mon {

	private ArrayList<Move> fMoveSet = new ArrayList<Move>();
	private final Ability fAbility;
	private final Nature fNature;

	private HashMap<Stat, Integer> fStatusStatModifier = new HashMap<Stat, Integer>();
	private HashMap<Stat, Integer> fItemStatModifier = new HashMap<Stat, Integer>();

	private HashMap<Stat, Integer> fEVs = new HashMap<Stat, Integer>();
	private HashMap<Stat, Integer> fIVs = new HashMap<Stat, Integer>();
	private HashMap<Stat, Float> fNatureModifier = new HashMap<Stat, Float>();

	private HashMap<Stat, Integer> fEffectiveStats = new HashMap<Stat, Integer>();

	public BattleMon(Ability ability, Nature nature) {
		fAbility = ability;
		fNature = nature;

		initStatModifiers();
		calcPersonalStats();
	}

	private void initStatModifiers() {
		for (Stat s : Stat.values()) {
			fStatusStatModifier.put(s, 1);
			fItemStatModifier.put(s, 1);
		}
	}

	private void setEVs() {
		for (Stat s : Stat.values()) {
			fEVs.put(s, 0);
		}
	}

	private void setIVs() {
		for (Stat s : Stat.values()) {
			fIVs.put(s, 0);
		}
	}

	// calculate stats as they are at the beginning of a battle,
	// before modifiers, item effects, abilities, etc.
	private void calcPersonalStats() {

		// TODO: need a better way to assign EV/IV spreads when new BattleMon
		// constructed
		for (Stat s : Stat.values()) {
			fEVs.put(s, 0);
		}

		for (Stat s : Stat.values()) {
			fEVs.put(s, 0);
		}

		// set nature stat buffs/debuffs
		for (Stat s : Stat.values()) {
			if (fNature.Effect.get(fNature)[0] == s) {
				fNatureModifier.put(s, (float) 1.1);
			} else if (fNature.Effect.get(fNature)[1] == s) {
				fNatureModifier.put(s, (float) 0.9);
			} else {
				fNatureModifier.put(s, (float) 1.0);
			}
		}

		// calculate stat as it is at very beginning of battle
		for (HashMap.Entry<Stat, Integer> s : getBaseStats().entrySet()) {
			Stat stat = s.getKey();
			fEffectiveStats.put(stat,
					(int) Math.round(s.getValue() + (fEVs.get(stat) / 4) * fNatureModifier.get(stat)));
		}
	}

	public int getStatVal(Stat stat) {
		return fEffectiveStats.get(stat) * fStatusStatModifier.get(stat) * fItemStatModifier.get(stat);
	}
}
