package battlesim;

import mons.BattleMon;
import mons.Dex;
import mons.Mon;

public class Battle {

	public Battle() {

		BattleMon Archer = (BattleMon) new Mon();
		BattleMon Mamoswine;
		try {
			Archer = (BattleMon) Dex.MONS.get("Garchomp").clone();
			Mamoswine = (BattleMon) Dex.MONS.get("Mamoswine").clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}