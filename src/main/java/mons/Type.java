package mons;

import java.util.HashMap;

public enum Type {
	Normal("Normal",
			"Normal:1 Fighting:2 Flying:1 Poison:1 Ground:1 Rock:1 Bug:1 Ghost:0 Steel:1 Fire:1 Water:1 Grass:1 Electric:1 Psychic:1 Ice:1 Dragon:1 Dark:1 Fairy:1"),
	Fire("Fire",
			"Normal:1 Fighting:1 Flying:1 Poison:1 Ground:2 Rock:2 Bug:0.5 Ghost:1 Steel:0.5 Fire:0.5 Water:2 Grass:0.5 Electric:1 Psychic:1 Ice:0.5 Dragon:1 Dark:1 Fairy:0.5"),
	Water("Water",
			"Normal:1 Fighting:1 Flying:1 Poison:1 Ground:1 Rock:1 Bug:1 Ghost:1 Steel:0.5 Fire:0.5 Water:0.5 Grass:2 Electric:2 Psychic:1 Ice:0.5 Dragon:1 Dark:1 Fairy:1"),
	Electric("Electric",
			"Normal:1 Fighting:1 Flying:0.5 Poison:1 Ground:2 Rock:1 Bug:1 Ghost:1 Steel:0.5 Fire:1 Water:1 Grass:1 Electric:0.5 Psychic:1 Ice:1 Dragon:1 Dark:1 Fairy:1"),
	Grass("Grass",
			"Normal:1 Fighting:1 Flying:2 Poison:2 Ground:0.5 Rock:1 Bug:2 Ghost:1 Steel:1 Fire:2 Water:0.5 Grass:0.5 Electric:0.5 Psychic:1 Ice:2 Dragon:1 Dark:1 Fairy:1"),
	Ice("Ice",
			"Normal:1 Fighting:2 Flying:1 Poison:1 Ground:1 Rock:2 Bug:1 Ghost:1 Steel:2 Fire:2 Water:1 Grass:1 Electric:1 Psychic:1 Ice:0.5 Dragon:1 Dark:1 Fairy:1"),
	Fighting("Fighting",
			"Normal:1 Fighting:1 Flying:2 Poison:1 Ground:1 Rock:0.5 Bug:0.5 Ghost:1 Steel:1 Fire:1 Water:1 Grass:1 Electric:1 Psychic:2 Ice:1 Dragon:1 Dark:0.5 Fairy:2"),
	Poison("Poison",
			"Normal:1 Fighting:0.5 Flying:1 Poison:0.5 Ground:2 Rock:1 Bug:0.5 Ghost:1 Steel:1 Fire:1 Water:1 Grass:0.5 Electric:1 Psychic:2 Ice:1 Dragon:1 Dark:1 Fairy:0.5"),
	Ground("Ground",
			"Normal:1 Fighting:1 Flying:1 Poison:0.5 Ground:1 Rock:0.5 Bug:1 Ghost:1 Steel:1 Fire:1 Water:2 Grass:2 Electric:0 Psychic:1 Ice:2 Dragon:1 Dark:1 Fairy:1"),
	Flying("Flying",
			"Normal:1 Fighting:0.5 Flying:1 Poison:1 Ground:0 Rock:2 Bug:0.5 Ghost:1 Steel:1 Fire:1 Water:1 Grass:0.5 Electric:2 Psychic:1 Ice:2 Dragon:1 Dark:1 Fairy:1"),
	Psychic("Psychic",
			"Normal:1 Fighting:0.5 Flying:1 Poison:1 Ground:1 Rock:1 Bug:2 Ghost:2 Steel:1 Fire:1 Water:1 Grass:1 Electric:1 Psychic:0.5 Ice:1 Dragon:1 Dark:2 Fairy:1"),
	Bug("Bug",
			"Normal:1 Fighting:0.5 Flying:2 Poison:1 Ground:0.5 Rock:2 Bug:1 Ghost:1 Steel:1 Fire:2 Water:1 Grass:0.5 Electric:1 Psychic:1 Ice:1 Dragon:1 Dark:1 Fairy:1"),
	Rock("Rock",
			"Normal:0.5 Fighting:2 Flying:0.5 Poison:0.5 Ground:2 Rock:1 Bug:1 Ghost:1 Steel:2 Fire:0.5 Water:2 Grass:0.5 Electric:1 Psychic:1 Ice:1 Dragon:1 Dark:1 Fairy:1"),
	Ghost("Ghost",
			"Normal:0 Fighting:0 Flying:1 Poison:0.5 Ground:1 Rock:1 Bug:0.5 Ghost:2 Steel:1 Fire:1 Water:1 Grass:1 Electric:1 Psychic:1 Ice:1 Dragon:1 Dark:2 Fairy:1"),
	Dragon("Dragon",
			"Normal:1 Fighting:1 Flying:1 Poison:1 Ground:1 Rock:1 Bug:1 Ghost:1 Steel:1 Fire:0.5 Water:0.5 Grass:0.5 Electric:0.5 Psychic:1 Ice:2 Dragon:2 Dark:1 Fairy:2"),
	Dark("Dark",
			"Normal:1 Fighting:2 Flying:1 Poison:1 Ground:1 Rock:1 Bug:2 Ghost:0.5 Steel:1 Fire:1 Water:1 Grass:1 Electric:1 Psychic:0 Ice:1 Dragon:1 Dark:0.5 Fairy:2"),
	Steel("Steel",
			"Normal:0.5 Fighting:2 Flying:0.5 Poison:0 Ground:2 Rock:0.5 Bug:0.5 Ghost:1 Steel:0.5 Fire:2 Water:1 Grass:0.5 Electric:1 Psychic:0.5 Ice:0.5 Dragon:0.5 Dark:1 Fairy:0.5"),
	Fairy("Fairy",
			"Normal:1 Fighting:0.5 Flying:1 Poison:2 Ground:1 Rock:1 Bug:0.5 Ghost:1 Steel:2 Fire:1 Water:1 Grass:1 Electric:1 Psychic:1 Ice:1 Dragon:0 Dark:0.5 Fairy:1");

	public HashMap<String, Double> Matchup = new HashMap<String, Double>();

	Type(String name, String key) {
		String[] type = key.split(" ");
		for (int i = 0; i < type.length; i++) {
			String[] match = type[i].split(":");
			Matchup.put(match[0], Double.valueOf(match[1]));
		}
	}
}
