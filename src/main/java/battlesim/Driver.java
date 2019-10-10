package battlesim;

import mons.Dex;
import mons.Type;
import tools.MondexXmlParser;
import tools.MonsXmlWriter;
import tools.MovedexXmlParser;
import tools.MovesXmlWriter;

public class Driver {

	public static void main(String[] args) {

		Dex dex = new Dex();

		MovedexXmlParser moveParser = new MovedexXmlParser("src/main/resources/movedex.xml");

//		System.out.println("Moves:\n");
//		for (HashMap.Entry<String, Move> m : Dex.MOVES.entrySet()) {
//			System.out.println("Name: " + m.getValue().getName());
//			System.out.println("Type: " + m.getValue().getType().toString());
//			System.out.println("Category: " + m.getValue().getCategory().toString());
//			System.out.println("PP: " + m.getValue().getPp());
//			System.out.println("Max PP: " + m.getValue().getMaxpp());
//			System.out.println("Power: " + m.getValue().getPower());
//			System.out.println("Accuracy: " + m.getValue().getAccuracy());
//			System.out.println("Description: " + m.getValue().getDescription() + "\n");
//		}

		MondexXmlParser monParser = new MondexXmlParser("src/main/resources/mondex.xml");

//		System.out.println("Mons:\n");
//		for (HashMap.Entry<String, Mon> m : Dex.MONS.entrySet()) {
//			System.out.println("Dex Number: " + m.getValue().getDexNumber());
//			System.out.println("Name: " + m.getValue().getName());
//			for (Type t : m.getValue().getTypeArray()) {
//				System.out.println("Type: " + t.toString());
//			}
//			for (Ability a : m.getValue().getAbilities()) {
//				System.out.println("Ability: " + a.getName());
//			}
//			System.out.println("Weight: " + m.getValue().getWeight());
//			System.out.println("HP: " + m.getValue().getBaseStat(Stat.HP));
//			System.out.println("Attack: " + m.getValue().getBaseStat(Stat.ATK));
//			System.out.println("Defense: " + m.getValue().getBaseStat(Stat.DEF));
//			System.out.println("Sp. Attack: " + m.getValue().getBaseStat(Stat.SPATK));
//			System.out.println("Sp. Defense: " + m.getValue().getBaseStat(Stat.SPDEF));
//			System.out.println("Speed: " + m.getValue().getBaseStat(Stat.SPD));
//			for (Move mv : m.getValue().getMovePool()) {
//				System.out.println("Move: " + mv.getName());
//			}
//			System.out.println("Description: " + m.getValue().getDescription());
//			System.out.println("");
//		}

//		testMovesWebScraper();
		// testMonsWebScraper();
//		testType();

		Battle b = new Battle();

	}

	public static void testMonsWebScraper() {
		try {
			MonsXmlWriter monsWriter = new MonsXmlWriter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void testMovesWebScraper() {
		try {
			MovesXmlWriter movesWriter = new MovesXmlWriter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testType() {
		for (Type t : Type.values()) {
			System.out.println(t.Matchup.toString());
		}
	}
}
