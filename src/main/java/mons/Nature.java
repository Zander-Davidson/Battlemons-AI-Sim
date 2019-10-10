package mons;

public enum Nature {
	Hardy(Stat.ATK, Stat.ATK), Lonely(Stat.ATK, Stat.DEF), Adamant(Stat.ATK, Stat.SPATK), Naughty(Stat.ATK, Stat.SPDEF),
	Brave(Stat.ATK, Stat.SPD), Bold(Stat.DEF, Stat.ATK), Docile(Stat.DEF, Stat.DEF), Impish(Stat.DEF, Stat.SPATK),
	Lax(Stat.DEF, Stat.SPDEF), Relaxed(Stat.DEF, Stat.SPD), Modest(Stat.SPATK, Stat.ATK), Mild(Stat.SPATK, Stat.DEF),
	Bashful(Stat.SPATK, Stat.SPATK), Rash(Stat.SPATK, Stat.SPDEF), Quiet(Stat.SPATK, Stat.SPD),
	Calm(Stat.SPDEF, Stat.ATK), Gentle(Stat.SPDEF, Stat.DEF), Careful(Stat.SPDEF, Stat.SPATK),
	Quirky(Stat.SPDEF, Stat.SPDEF), Sassy(Stat.SPDEF, Stat.SPD), Timid(Stat.SPD, Stat.ATK), Hasty(Stat.SPD, Stat.DEF),
	Jolly(Stat.SPD, Stat.SPATK), Naive(Stat.SPD, Stat.SPDEF), Serious(Stat.SPD, Stat.SPD);

	public Stat fBuff; // stat to increase by 10%
	public Stat fDebuff; // stat to decrease by 10%

	Nature(Stat buff, Stat debuff) {
		fBuff = buff;
		fDebuff = debuff;
	}

	public Stat getBuff() {
		return fBuff;
	}

	public Stat getDebuff() {
		return fDebuff;
	}
}
