package mons;

import java.util.ArrayList;
import java.util.HashMap;

public class Mon implements Cloneable {

	private int fDexNumber;
	private String fName;
	private ArrayList<Type> fType = new ArrayList<Type>();
	private ArrayList<Ability> fAbilities = new ArrayList<Ability>();
	private float fWeight; // in kg
	private String fDescription;
	private HashMap<Stat, Integer> fBaseStats = new HashMap<Stat, Integer>(); // stat name, int value
	private ArrayList<Move> fMovePool = new ArrayList<Move>(); // move name, Move

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public void addType(Type type) {
		fType.add(type);
	}

	public void addAbility(Ability ability) {
		fAbilities.add(ability);
	}

	public void addStat(Stat stat, int value) {
		fBaseStats.put(stat, value);
	}

	public void addMove(Move move) {
		fMovePool.add(move);
	}

	public int getDexNumber() {
		return fDexNumber;
	}

	public void setDexNumber(int dexNumber) {
		this.fDexNumber = dexNumber;
	}

	public String getName() {
		return fName;
	}

	public void setName(String name) {
		this.fName = name;
	}

	public ArrayList<Type> getTypeArray() {
		return fType;
	}

	public Type getType(int i) {
		try {
			return fType.get(i);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error occured while fetching " + fName + "type: index out of bounds.");
			return null;
		}
	}

	public void setTypeArray(ArrayList<Type> type) {
		this.fType = type;
	}

	public ArrayList<Ability> getAbilities() {
		return fAbilities;
	}

	public void setAbilbities(ArrayList<Ability> abilbities) {
		this.fAbilities = abilbities;
	}

	public float getWeight() {
		return fWeight;
	}

	public void setWeight(float weight) {
		this.fWeight = weight;
	}

	public String getDescription() {
		return fDescription;
	}

	public void setDescription(String description) {
		this.fDescription = description;
	}

	public HashMap<Stat, Integer> getBaseStats() {
		return fBaseStats;
	}

	public Integer getBaseStat(Stat stat) {
		return fBaseStats.get(stat);
	}

	public void setBaseStats(HashMap<Stat, Integer> stats) {
		this.fBaseStats = stats;
	}

	public ArrayList<Move> getMovePool() {
		return fMovePool;
	}

	public void setMovePool(ArrayList<Move> moves) {
		this.fMovePool = moves;
	}
}
