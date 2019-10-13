package tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MonsWebScraper extends AbstractWebScraper {

	int counter = 1;

	private ArrayList<String> fMonNames = new ArrayList<String>(); // an ArrayList of Elements of pokemon (will really
																	// only
																	// be used to get names of
	// mons to generate new pages)
	private HashMap<String, Object> fMonElems = new HashMap<String, Object>();
	// dexno, name, typing > type, abilities > (ability), weight, stats > (hp, atk,
	// def, spatk, spdef, spd), movepool > (move), description;

	public MonsWebScraper() throws IOException {
		super("chrome/75.0.3770.142", "https://pokemondb.net/pokedex/all");

		Elements monNames = getDocument().getElementsByClass("cell-name");
		while (!monNames.isEmpty()) {
			Element name = monNames.remove(0);
			if (!fMonNames.contains(name.select("td > a").attr("href"))) {
				fMonNames.add(name.select("td > a").attr("href"));
			}
		}
	}

	public boolean parseMon() {
		if (fMonNames.isEmpty()) {
			return false;
		}
		fMonElems.clear();

		try {
			fMonElems = new AbstractWebScraper("chrome/75.0.3770.142", "https://pokemondb.net" + fMonNames.remove(0)) {
				private HashMap<String, Object> fMonElems = new HashMap<String, Object>();

				public HashMap<String, Object> parseMon() {
					fMonElems.clear();
					Document document = getDocument();

					// *[@id="tab-basic-1"]/div[1]/div[2]/table/tbody
					Elements data = document
							.select("#tab-basic-" + counter + " > div > div:eq(1) > table > tbody > tr");

					// *[@id="tab-basic-1"]/div[1]/div[2]/table/tbody/tr[1]/td/strong
					fMonElems.put("dexno", data.get(0).select("td > strong").get(0).text());

					// *[@id="tab-basic-1"]/div[1]/div[2]/table/tbody/tr[2]/td/a[1]
					// *[@id="tab-basic-1"]/div[1]/div[2]/table/tbody/tr[2]/td/a[2]
					Elements typing = data.get(1).select("td > a");
					ArrayList<String> typingArr = new ArrayList<String>();
					for (int i = 0; i < typing.size(); i++) {
						typingArr.add(typing.get(i).text());
					}
					fMonElems.put("typing", typingArr);

					String height = data.get(3).select("td").get(0).text();
					height = height.substring(0, height.indexOf(' '));
					fMonElems.put("height", height);

					String weight = data.get(4).select("td").get(0).text();
					weight = weight.substring(0, weight.indexOf(' '));
					fMonElems.put("weight", weight);

					// *[@id="tab-basic-1"]/div[1]/div[2]/table/tbody/tr[6]/td/span/a
					// *[@id="tab-basic-350"]/div[1]/div[2]/table/tbody/tr[6]/td/span[2]/a
					// *[@id="tab-basic-1"]/div[1]/div[2]/table/tbody/tr[6]/td/small/a
					// *[@id="tab-basic-1"]/div[1]/div[2]/table/tbody/tr[6]/td/small[2]/a
					Elements abilities = data.get(5).children().get(1).children();
					ArrayList<String> abilitiesArr = new ArrayList<String>();
					for (int i = 0; i < abilities.size(); i = i + 2) {
						abilitiesArr.add(abilities.get(i).select("a").get(0).text());
					}
					fMonElems.put("abilities", abilitiesArr);

					// *[@id="tab-basic-1"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[1]
					data = document.select("#tab-basic-" + counter + " > div:eq(1) > div > div > table > tbody > tr");
					ArrayList<String> statsArr = new ArrayList<String>();
					for (int i = 0; i < 6; i++) {
						statsArr.add(data.get(i).select("td").get(0).text());
					}
					fMonElems.put("stats", statsArr);

					HashSet<String> movepoolHash = new HashSet<String>();
					// *[@id="tab-moves-16"]/div[1]/div[1]
					data = document.select("#tab-moves-16");
					while (!data.isEmpty()) {
						Elements moveList = data.remove(0).getElementsByClass("cell-name");
						while (!moveList.isEmpty()) {
							Element move = moveList.remove(0);
							movepoolHash.add(move.select("a").get(0).text());
						}
					}

					fMonElems.put("movepool", new ArrayList<String>(movepoolHash));

					fMonElems.put("name", document.select("html > body > main > h1").get(0).text());

					// *[@id="tab-basic-710"]/div[1]/div[1]/p[1]/img
					// *[@id="tab-basic-487"]/div[1]/div[1]/p[1]/a/img
					Response resultImageResponse = null;
					// Open a URL Stream
					try {
						resultImageResponse = Jsoup
								.connect(document.select("#tab-basic-" + counter).get(0).selectFirst("img").attr("src"))
								.ignoreContentType(true).execute();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// output here
					FileOutputStream out = null;
					try {
						out = (new FileOutputStream(
								new java.io.File("src/main/resources/sprites/" + fMonElems.get("name") + ".jpg")));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(fMonElems.get("name"));

					try {
						out.write(resultImageResponse.bodyAsBytes());
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // resultImageResponse.body() is where the image's contents are.

					// description

					// TODO: fix
					counter++;

					return fMonElems;
				}
			}.parseMon();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public HashMap<String, Object> getMonElems() {
		return fMonElems;
	}
}
