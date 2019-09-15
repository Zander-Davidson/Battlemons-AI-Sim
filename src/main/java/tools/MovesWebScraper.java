package tools;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovesWebScraper extends AbstractWebScraper {

	private Elements fMoves; // an ArrayList of Elements of pkmn moves from html
	private HashMap<String, String> fMoveElems = new HashMap<String, String>();
	// name, type, category, pp, maxpp, power, accuracy, description

	public MovesWebScraper() throws IOException {
		super("chrome/75.0.3770.142", "https://pokemondb.net/move/all");

		// a list of moves as <tr></tr> elements (from XPath)
		fMoves = getDocument().select("#moves > tbody > tr");
	}

	public HashMap<String, String> getMoveElems() {
		return fMoveElems;
	}

	interface PutMap {
		void put(Element element, String key, String path);
	}

	private void putMap(String xmlId, Element root, String xPath) {
		fMoveElems.put(xmlId, root.select(xPath).get(0).text());
	}

	private void putMap(String xmlId, Element root, String xPath, String attr) {
		fMoveElems.put(xmlId, root.select(xPath).attr(attr));
	}

	/**
	 * extract data from the first element of the fMoves list, put data into HashMap
	 * that can be accessed by a document-writer or other Java object. Uses XPath to
	 * get the elements (Ex: [@id="moves"]/tbody/tr[1]/td[2]/a can be accessed by
	 * fDocument.select("moves > tbody > tr > td:eq(1) > a") ) pop first element off
	 * list
	 * 
	 * @return false if list is empty, else true
	 */
	public boolean parseMove() {
		Element move = fMoves.first();
		if (fMoves.isEmpty()) {
			return false;
		}
		fMoveElems.clear();

		putMap("name", move, "td > a");
		putMap("type", move, "td:eq(1) > a");
		putMap("category", move, "td:eq(2) > span", "title");
		putMap("power", move, "td:eq(3)");
		putMap("accuracy", move, "td:eq(4)");
		putMap("pp", move, "td:eq(5)");
		putMap("description", move, "td:eq(7)");

		fMoves.remove(0);
		return true;
	}
}
