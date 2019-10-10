package battlesim;

public final class Field {

	public static enum Weather {
		NONE, HARSHSUNLIGHT, EXTREMELYHARSHSUNLIGHT, RAIN, HEAVYRAIN, HAIL, SANDSTORM, STRONGWINDS, FOG, SHADOWSKY
	};

	public static enum Terrain {
		NONE, ELECTRIC, PSYCHIC, GRASSY, MISTY
	};

	private static Weather fWeather = Weather.NONE;
	private static Terrain fTerrain = Terrain.NONE;

	public static Weather getWeather() {
		return fWeather;
	}

	public static Terrain getTerrain() {
		return fTerrain;
	}

	public static void setWeather(Weather w) {
		fWeather = w;
	}

	public static void setTerrain(Terrain t) {
		fTerrain = t;
	}
}
