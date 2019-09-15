package tools;

import mons.Ability;
import mons.Dex;
import mons.Mon;
import mons.Stat;
import mons.Type;

public class MondexXmlParser extends AbstractXmlParser {

	private Mon fMon = null;

	public MondexXmlParser(String filePath) {
		super(filePath);
		parse();
	}

	protected void handleElement(String key, String value) {
		if (key.equals("mon")) {
			fMon = new Mon();
		} else if (key.equals("dexno")) {
			fMon.setDexNumber(Integer.parseInt(value));
		} else if (key.equals("name")) {
			fMon.setName(value);
		} else if (key.equals("type")) {
			fMon.addType(Type.valueOf(value));
		} else if (key.equals("ability")) {
			fMon.addAbility(new Ability(value));
		} else if (key.equals("weight")) {
			fMon.setWeight(Float.parseFloat(value));
		} else if (key.equals("hp")) {
			fMon.addStat(Stat.HP, Integer.parseInt(value));
		} else if (key.equals("atk")) {
			fMon.addStat(Stat.ATK, Integer.parseInt(value));
		} else if (key.equals("def")) {
			fMon.addStat(Stat.DEF, Integer.parseInt(value));
		} else if (key.equals("spatk")) {
			fMon.addStat(Stat.SPATK, Integer.parseInt(value));
		} else if (key.equals("spdef")) {
			fMon.addStat(Stat.SPDEF, Integer.parseInt(value));
		} else if (key.equals("spd")) {
			fMon.addStat(Stat.SPD, Integer.parseInt(value));
		} else if (key.equals("move")) {
			if (Dex.MOVES.get(value) != null) {
				fMon.addMove(Dex.MOVES.get(value));
			}
		} else if (key.equals("description")) {
			fMon.setDescription(value);
			Dex.MONS.put(fMon.getName(), fMon);
		}
	}
}
