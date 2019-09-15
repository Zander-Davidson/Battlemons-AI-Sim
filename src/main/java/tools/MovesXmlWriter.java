package tools;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.stream.XMLStreamException;

public class MovesXmlWriter extends AbstractXmlWriter {

	public MovesXmlWriter() throws XMLStreamException, IOException {
		super("src/main/resources", "movedex.xml");
	}

	@Override
	protected void writeXml() throws XMLStreamException {
		xtw.writeStartDocument("utf-8", "1.0");

		xtw.writeCharacters("\n\n");
		xtw.writeStartElement("movelist");
		xtw.writeCharacters("\n\n");

		MovesWebScraper fWebScraper = null;
		try {
			fWebScraper = new MovesWebScraper();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (((MovesWebScraper) fWebScraper).parseMove()) {

			HashMap<String, String> elems = ((MovesWebScraper) fWebScraper).getMoveElems();

			xtw.writeCharacters("\t");
			xtw.writeStartElement("move");
			xtw.writeCharacters("\n\t");
			String[] elemOrder = { "name", "type", "category", "pp", "power", "accuracy", "description" };
			for (String elemName : elemOrder) {
				xtw.writeCharacters("\t");
				xtw.writeStartElement(elemName);
				xtw.writeCharacters(elems.get(elemName));
				xtw.writeEndElement();
				xtw.writeCharacters("\n\t");
			}
			xtw.writeEndElement();
			xtw.writeCharacters("\n\n");
		}
		xtw.writeEndElement();

		xtw.writeEndDocument();
		xtw.flush();
		xtw.close();

		fWebScraper.writeHtml("src/main/resources", "pkmndbmoves.html");
		System.out.println("Done writing movedex.xml");
	}
}
