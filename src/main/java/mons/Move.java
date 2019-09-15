package mons;

public class Move {

	private String fName;
	private Type fType;
	private Category fCategory;
	private int fPp;
	private int fMaxpp;
	private int fPower;
	private int fAccuracy;
	private String fDescription;

	public Move(String name, Type type, Category category, int pp, int maxpp, int power, int accuracy,
			String description) {
		super();
		this.fName = name;
		this.fType = type;
		this.fCategory = category;
		this.fPp = pp;
		this.fMaxpp = maxpp;
		this.fPower = power;
		this.fAccuracy = accuracy;
		this.fDescription = description;
	}

	public Move() {
	}

	public String getName() {
		return fName;
	}

	public void setName(String name) {
		this.fName = name;
	}

	public Type getType() {
		return fType;
	}

	public void setType(Type type) {
		this.fType = type;
	}

	public Category getCategory() {
		return fCategory;
	}

	public void setCategory(Category category) {
		this.fCategory = category;
	}

	public int getPp() {
		return fPp;
	}

	public void setPp(int pp) {
		this.fPp = pp;
	}

	public int getMaxpp() {
		return fMaxpp;
	}

	public void setMaxpp(int maxpp) {
		this.fMaxpp = maxpp;
	}

	public int getPower() {
		return fPower;
	}

	public void setPower(int power) {
		this.fPower = power;
	}

	public int getAccuracy() {
		return fAccuracy;
	}

	public void setAccuracy(int accuracy) {
		this.fAccuracy = accuracy;
	}

	public String getDescription() {
		return fDescription;
	}

	public void setDescription(String description) {
		this.fDescription = description;
	}
}
