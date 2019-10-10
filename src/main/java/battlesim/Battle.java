package battlesim;

import mons.BattleMon;
import mons.Nature;
import mons.Stat;

public class Battle {

	public Battle() {

		Field field = new Field();

		BattleMon Archer = new BattleMon("Garchomp", "Archer", 100, "Rough Skin", Nature.Adamant,
				new String[] { "Earthquake", "Dragon Claw", "Fire Fang", "Blizzard" },
				new int[] { 31, 31, 31, 0, 31, 31 }, new int[] { 0, 252, 0, 0, 0, 252 });
		System.out.println("Archer stats: " + Archer.getEffectiveStats());

		BattleMon Spyro = new BattleMon("Tapu Koko", "Spyro", 100, "Electric Surge", Nature.Timid,
				new String[] { "Volt Switch", "Dazzling Gleam", "U-turn", "Defog" },
				new int[] { 31, 0, 31, 31, 31, 31 }, new int[] { 20, 0, 0, 252, 20, 216 });
		System.out.println("Spyro stats: " + Spyro.getEffectiveStats() + "\n");

		System.out.println("Archer HP: " + Archer.getHP() + "/" + Archer.getEffectiveStat(Stat.HP) + " = "
				+ (100.0 * (Archer.getHP() * 1.0 / Archer.getMaxHP())) + "%");
		System.out.println("Spyro HP: " + Spyro.getHP() + "/" + Spyro.getEffectiveStat(Stat.HP) + " = "
				+ (100.0 * (Spyro.getHP() * 1.0 / Spyro.getMaxHP())) + "%");

		System.out.println("\nArcher used Fire Fang!\n");
		new DamageCalculator(Archer, Archer.getMove("Fire Fang"), Spyro).calcDamage();

		System.out.println("Archer HP: " + Archer.getHP() + "/" + Archer.getEffectiveStat(Stat.HP) + " = "
				+ (100.0 * (Archer.getHP() * 1.0 / Archer.getMaxHP())) + "%");
		System.out.println("Spyro HP: " + Spyro.getHP() + "/" + Spyro.getEffectiveStat(Stat.HP) + " = "
				+ (100.0 * (Spyro.getHP() * 1.0 / Spyro.getMaxHP())) + "%");

		System.out.println("\nSpyro used U-turn!\n");
		new DamageCalculator(Spyro, Spyro.getMove("U-turn"), Archer).calcDamage();

		System.out.println("Archer HP: " + Archer.getHP() + "/" + Archer.getEffectiveStat(Stat.HP) + " = "
				+ (100.0 * (Archer.getHP() * 1.0 / Archer.getMaxHP())) + "%");
		System.out.println("Spyro HP: " + Spyro.getHP() + "/" + Spyro.getEffectiveStat(Stat.HP) + " = "
				+ (100.0 * (Spyro.getHP() * 1.0 / Spyro.getMaxHP())) + "%");

		System.out.println("\nArcher used Dragon Claw!\n");
		new DamageCalculator(Archer, Archer.getMove("Dragon Claw"), Spyro).calcDamage();

		System.out.println("Archer HP: " + Archer.getHP() + "/" + Archer.getEffectiveStat(Stat.HP) + " = "
				+ (100.0 * (Archer.getHP() * 1.0 / Archer.getMaxHP())) + "%");
		System.out.println("Spyro HP: " + Spyro.getHP() + "/" + Spyro.getEffectiveStat(Stat.HP) + " = "
				+ (100.0 * (Spyro.getHP() * 1.0 / Spyro.getMaxHP())) + "%");

	}
}