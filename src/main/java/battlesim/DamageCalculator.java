package battlesim;

import java.util.Date;
import java.util.Random;

import battlesim.Field.Weather;
import mons.BattleMon;
import mons.Category;
import mons.Move;
import mons.Stat;
import mons.Status;
import mons.Type;

/**
 * Battle calculator, used to calculate the damage on a BattleMon for a single
 * move in a single turn. Should be instantiated each time a new move is used.
 * Uses attributes of BattleMons passed in to calculate damage on the defender.
 * 
 * @author Zander Davidson
 *
 */

public class DamageCalculator {

	private int fLevel;

	private BattleMon fAttacker; // attacking BattleMon
	private Move fMove; // move of the attacker
	private BattleMon fDefender; // defending BattleMon

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
	private double fType = 1.0; // type effectiveness of the attacking move on the defender's type(s): 0
								// (ineffective); 0.25, 0.5 (not very effective); 1 (normally effective); 2, 4
								// (super effective), depending on both the move's
								// and target's types
	private double fBurn = 1.0; // 0.5 if attacker is burned, its Ability is not Guts, and the move is a
								// physical move (other than Facade); 1 otherwise
	private double fOther = 1.0; // 1 in most cases, and a different multiplier when specific interactions of
									// moves, Abilities, or items take effect. if multiple effects influence fOther,
									// their effects are multiplicative

	private Random rand = new Random(new Date().getTime());

	public DamageCalculator(BattleMon attacker, Move move, BattleMon defender) {
		fAttacker = attacker;
		fMove = move;
		fDefender = defender;

		initModifiers();
	};

	private void initModifiers() {
		fLevel = fAttacker.getLevel();

		// TODO: multiple targets not accounted for

		// setting fWeather according to current Field weather
		if (Field.getWeather().equals(Weather.RAIN) || Field.getWeather().equals(Weather.HEAVYRAIN)) {
			if (fMove.getType().equals(Type.Water)) {
				fWeather = 1.5;
			} else if (fMove.getType().equals(Type.Fire)) {
				fWeather = 0.5;
			}
		} else if (Field.getWeather().equals(Weather.HARSHSUNLIGHT)
				|| Field.getWeather().equals(Weather.EXTREMELYHARSHSUNLIGHT)) {
			if (fMove.getType().equals(Type.Fire)) {
				fWeather = 1.5;
			} else if (fMove.getType().equals(Type.Water)) {
				fWeather = 0.5;
			}
		}

		// TODO: figure out crit mechanics

		fRandom = (rand.nextInt(16) + 85) / 100.0;

		// setting STAB for attacker
		// does not account for ability Adaptibility
		if (fMove.getType().equals(fAttacker.getType(0)) || fMove.getType().equals(fAttacker.getType(1))) {
			fSTAB = 1.5;
		}

		// setting type effectiveness
		fType = fDefender.getType(0).Matchup.get(fMove.getType().toString())
				* fDefender.getType(1).Matchup.get(fMove.getType().toString());

		// accounting for burned defender
		// does not account for ability Guts
		if (fAttacker.getStatus().equals(Status.BURN) && fMove.getCategory().equals(Category.Physical)) {
			fBurn = 0.5;
		}

		// TODO: fOther not accounted for
	}

	public void calcDamage() {

		double modA = 0.0; // modified atk/spatk stat of fAttacker
		double modD = 0.0; // modified def/spdef stat of fDefender

		if (fMove.getCategory().equals(Category.Physical)) {
			modA = fAttacker.getEffectiveStat(Stat.ATK);
			modD = fDefender.getEffectiveStat(Stat.DEF);
		} else if (fMove.getCategory().equals(Category.Special)) {
			modA = fAttacker.getEffectiveStat(Stat.SPATK);
			modD = fDefender.getEffectiveStat(Stat.SPDEF);
		}

		double modifier = fTargets * fWeather * fCritical * fSTAB * fType * fBurn * fOther * fRandom;

		int damage = (int) (((int) ((((int) ((2.0 * fLevel) / 5) + 2) * fMove.getPower() * (modA / modD)) / 50.0) + 2)
				* modifier);

		if (damage > fDefender.getHP()) {
			fDefender.setHP(0);
		} else {
			fDefender.setHP(fDefender.getHP() - damage);
		}
	}

}
