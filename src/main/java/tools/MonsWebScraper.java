package tools;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.select.Elements;

public class MonsWebScraper extends AbstractWebScraper {

	private Elements fMons; // an ArrayList of Elements of pokemon (will really only be used to get names of
							// mons to generate new pages)
	private HashMap<String, Object> fMonElems = new HashMap<String, Object>();
	// dexno, name, typing > type, abilities > (ability), weight, stats > (hp, atk,
	// def, spatk, spdef, spd), movepool > (move), description;

	public MonsWebScraper() throws IOException {
		super("chrome/75.0.3770.142", "https://pokemondb.net/pokedex/all");

		fMons = getDocument().select("#pokedex > tbody > tr");
	}

	public boolean parseMon() {
		// TODO Auto-generated method stub
		return false;
	}

	public HashMap<String, Object> getMonElems() {
		return fMonElems;
	}

}
