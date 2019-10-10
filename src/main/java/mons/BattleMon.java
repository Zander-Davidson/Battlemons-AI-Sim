package mons;

import java.util.ArrayList;
import java.util.HashMap;

public class BattleMon extends Mon {

	private final String fSpecies;
	private final String fNickname;
	private final int fLevel;
	private final String fAbility;
	private final Nature fNature;

	private ArrayList<Move> fMoveset = new ArrayList<Move>();

	private Status fStatus = Status.NONE;
	private int fHP;
	private HashMap<Stat, Integer> fStatusStatModifier = new HashMap<Stat, Integer>();

	private HashMap<Stat, Integer> fItemStatModifier = new HashMap<Stat, Integer>();

	private HashMap<Stat, Integer> fEVs = new HashMap<Stat, Integer>();
	private HashMap<Stat, Integer> fIVs = new HashMap<Stat, Integer>();
	private HashMap<Stat, Float> fNatureModifier = new HashMap<Stat, Float>();
	private HashMap<Stat, Integer> fEffectiveStats = new HashMap<Stat, Integer>();

	public BattleMon(String species, String nickname, int level, String ability, Nature nature, String[] moveset,
			int[] ivs, int[] evs) {
		fSpecies = species;
		fNickname = nickname;
		fLevel = level;
		fAbility = ability;
		fNature = nature;

		initTraits(moveset);
		initStatModifiers();
		calcPersonalStats(ivs, evs);

		fHP = fEffectiveStats.get(Stat.HP);
	}

	private void initTraits(String[] moveset) {
		Mon parent = Dex.MONS.get(fSpecies);
		setDexNumber(parent.getDexNumber());
		setName(parent.getName());
		setTypeArray(parent.getTypeArray());
		setWeight(parent.getWeight());
		setBaseStats(parent.getBaseStats());
		for (int i = 0; i < 4; i++) {
			fMoveset.add(Dex.MOVES.get(moveset[i]));
		}
	}

	private void initStatModifiers() {
		for (Stat s : Stat.values()) {
			fStatusStatModifier.put(s, 1);
			fItemStatModifier.put(s, 1);
		}
	}

	// calculate stats as they are at the beginning of a battle,
	// before modifiers, item effects, abilities, etc.
	private void calcPersonalStats(int[] ivs, int[] evs) {

		// TODO: need a better way to assign EV/IV spreads when new BattleMon
		// constructed
		fIVs.put(Stat.HP, ivs[0]);
		fEVs.put(Stat.HP, evs[0]);
		fIVs.put(Stat.ATK, ivs[1]);
		fEVs.put(Stat.ATK, evs[1]);
		fIVs.put(Stat.DEF, ivs[2]);
		fEVs.put(Stat.DEF, evs[2]);
		fIVs.put(Stat.SPATK, ivs[3]);
		fEVs.put(Stat.SPATK, evs[3]);
		fIVs.put(Stat.SPDEF, ivs[4]);
		fEVs.put(Stat.SPDEF, evs[4]);
		fIVs.put(Stat.SPD, ivs[5]);
		fEVs.put(Stat.SPD, evs[5]);

		// set nature stat buffs/debuffs
		for (Stat s : Stat.values()) {
			if (fNature.getBuff().equals(s)) {
				fNatureModifier.put(s, (float) 1.1);
			} else if (fNature.getDebuff().equals(s)) {
				fNatureModifier.put(s, (float) 0.9);
			} else {
				fNatureModifier.put(s, (float) 1.0);
			}
		}

		// calculate stat as it is at very beginning of battle
		for (HashMap.Entry<Stat, Integer> base : getBaseStats().entrySet()) {
			Stat stat = base.getKey();

			int commonForm = (int) ((((2 * base.getValue()) + fIVs.get(stat) + ((int) (fEVs.get(stat) / 4.0))) * fLevel)
					/ 100.0);

			if (stat.equals(Stat.HP)) {
				fEffectiveStats.put(stat, commonForm + fLevel + 10);
			} else {
				fEffectiveStats.put(stat, (int) ((commonForm + 5) * fNatureModifier.get(stat)));
			}
		}
	}

	public int getLevel() {
		return fLevel;
	}

	public int getEffectiveStat(Stat stat) {
		return fEffectiveStats.get(stat) * fStatusStatModifier.get(stat) * fItemStatModifier.get(stat);
	}

	public HashMap<Stat, Integer> getEffectiveStats() {
		return fEffectiveStats;
	}

	public void setHP(int hp) {
		fHP = hp;
	}

	public int getHP() {
		return fHP;
	}

	public int getMaxHP() {
		return fEffectiveStats.get(Stat.HP);
	}

	public void setStatus(Status s) {
		fStatus = s;
	}

	public Status getStatus() {
		return fStatus;
	}

	public Move getMove(String move) {
		if (fMoveset.contains(Dex.MOVES.get(move))) {
			return Dex.MOVES.get(move);
		} else {
			System.out.println(
					"Error while trying to fetch move for " + fSpecies + ": " + fNickname + " does not know " + move);
			return null;
		}
	}
}
