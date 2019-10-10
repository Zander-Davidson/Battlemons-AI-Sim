package tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.XMLStreamException;

public class MonsXmlWriter extends AbstractXmlWriter {

	public MonsXmlWriter() throws XMLStreamException, IOException {
		super("src/main/resources", "mondex.xml");
	}

	@Override
	protected void writeXml() throws XMLStreamException {
		xtw.writeStartDocument("utf-8", "1.0");

		xtw.writeCharacters("\n\n");
		xtw.writeStartElement("monlist");
		xtw.writeCharacters("\n\n");

		MonsWebScraper fWebScraper = null;
		try {
			fWebScraper = new MonsWebScraper();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (((MonsWebScraper) fWebScraper).parseMon()) {
			HashMap<String, Object> elems = ((MonsWebScraper) fWebScraper).getMonElems();

			xtw.writeCharacters("\t");
			xtw.writeStartElement("mon");
			xtw.writeCharacters("\n");
			String[] elemOrder = { "dexno", "name", "typing", "type", "abilities", "ability", "height", "weight",
					"stats", "hp", "atk", "def", "spatk", "spdef", "spd", "movepool", "move", "description" };
			for (int i = 0; i < elemOrder.length; i++) {
				xtw.writeCharacters("\t\t");
				String elemName = elemOrder[i];
				xtw.writeStartElement(elemName);

				if (elems.get(elemName) instanceof ArrayList<?>) {
					if (elemName.equals("typing") || elemName.equals("abilities") || elemName.equals("movepool")) {
						i++;
						for (String elemVal : ((ArrayList<String>) elems.get(elemName))) {
							writeStandaloneElement(1, 3, elemOrder[i], elemVal);
						}
					} else if (elemName.equals("stats")) {
						for (String elemVal : ((ArrayList<String>) elems.get(elemName))) {
							i++;
							writeStandaloneElement(1, 3, elemOrder[i], elemVal);
						}
					} else if (elemName.equals("movepool")) {
						for (String elemVal : ((ArrayList<String>) elems.get(elemName))) {
							writeStandaloneElement(1, 3, elemOrder[i], elemVal);
						}
					}
					xtw.writeCharacters("\n\t\t");
				} else {
					xtw.writeCharacters((String) elems.get(elemName));
				}
				xtw.writeEndElement();
				xtw.writeCharacters("\n");
			}
			xtw.writeCharacters("\t");
			xtw.writeEndElement();
			xtw.writeCharacters("\n\n");
		}
		xtw.writeEndElement();

		xtw.writeEndDocument();
		xtw.flush();
		xtw.close();

		System.out.println("Done writing mondex.xml");
	}
}
