package battlesim;

import java.util.Date;
import java.util.Random;

import mons.Category;
import mons.Move;
import mons.Stat;

public class DamageCalculator {

	private final int LEVEL = 100;

	private BattleMon fAttacker;
	private Move fMove;
	private BattleMon fDefender;

	// modifiers (Gen 7 mechanics):
	private double fTargets = 1.0; // N/A for single battles
	private double fWeather = 1.0; // TODO: figure out weather mechanics
									// 1.5 if Water move used in rain or Fire move in harsh sunlight, 0.5 if Water
									// move used in harsh sunlight or Fire move in rain, 1 otherwise
	private double fCritical = 1.0; // TODO: figure out crit mechanics
									// critical hit multiplier (1.5 if crit, else 1)
	private double fRandom = 1.0; // a percentage rand[0.85, 1.0] inlcusive
	private double fSTAB = 1.0; // Same Type Atk Bonus; 1.5 if move type matches user type, 2 if user also has
								// ability Adaptibility, 1 otherwise
	private double fType = 1.0; // type effectiveness: 0 (ineffective); 0.25, 0.5 (not very effective); 1
								// (normally effective); 2, 4 (super effective), depending on both the move's
								// and target's types
	private double fBurn = 1.0; // 0.5 if attacker is burned, its Ability is not Guts, and the move is a
								// physical move (other than Facade); 1 otherwise
	private double fOther = 1.0; // 1 in most cases, and a different multiplier when specific interactions of
									// moves, Abilities, or items take effect. if multiple effects influence fOther,
									// their effects are multiplicative

	private Random rand = new Random(new Date().getTime());

	public int calcDamage() {

		int modA = 0; // modified atk/spatk stat of fAttacker
		int modD = 0; // modified def/spdef stat of fDefender

		if (fMove.getCategory().equals(Category.Physical)) {
			modA = fAttacker.getStatVal(Stat.ATK);
			modD = fDefender.getStatVal(Stat.DEF);
		} else if (fMove.getCategory().equals(Category.Special)) {
			modA = fAttacker.getStatVal(Stat.SPATK);
			modD = fDefender.getStatVal(Stat.SPDEF);
		}

		fRandom = (rand.nextInt(16) + 85) / 100.0;
		double modifier = fTargets * fWeather * fCritical * fSTAB * fType * fBurn * fOther * fRandom;

		int damage = (int) ((((int) ((((int) ((2.0 * LEVEL) / 5)) + 2) * fMove.getPower()
				* ((int) ((double) modA / modD))) / 50.0) + 2) * modifier);

		return damage;
	}

}
