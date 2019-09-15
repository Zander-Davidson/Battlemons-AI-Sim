package tools;

import mons.Category;
import mons.Dex;
import mons.Move;
import mons.Type;

public class MovedexXmlParser extends AbstractXmlParser {

	private Move fMove = null;

	public MovedexXmlParser(String filePath) {
		super(filePath);
		parse();
	}

	@Override
	protected void handleElement(String key, String value) {
		if (key.equals("move")) {
			fMove = new Move();
		} else if (key.equals("name")) {
			fMove.setName(value);
		} else if (key.equals("type")) {
			fMove.setType(Type.valueOf(value));
		} else if (key.equals("category")) {
			fMove.setCategory(value.contentEquals("") ? Category.Other : Category.valueOf(value));
		} else if (key.equals("pp")) {
			try {
				fMove.setPp(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				fMove.setPp(1000);
			}
		} else if (key.equals("maxpp")) {
			fMove.setMaxpp(Integer.parseInt(value));
		} else if (key.equals("power")) {
			try {
				fMove.setPower(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				fMove.setPower(0);
			}
		} else if (key.equals("accuracy")) {
			try {
				fMove.setAccuracy(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				fMove.setAccuracy(101);
			}
		} else if (key.equals("description")) {
			fMove.setDescription(value);
			Dex.MOVES.put(fMove.getName(), fMove);
		}
	}
}
