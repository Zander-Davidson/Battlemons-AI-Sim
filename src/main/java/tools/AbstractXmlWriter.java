package tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public abstract class AbstractXmlWriter {

	private final String fFileName;
	private final String fFilePath;

	private XMLOutputFactory fXFactory;
	protected XMLStreamWriter xtw;

	public AbstractXmlWriter(String filePath, String fileName) throws XMLStreamException, IOException {
		fFilePath = filePath;
		fFileName = fileName;

		initWriter();
		writeXml();
	}

	protected void writeXml() throws XMLStreamException {
	}

	// write a single-line element of the form "[\n * numNewline][\t *
	// numTab]<elemName>elemVal</elemName>", then moves cursor to the next line
	protected void writeStandaloneElement(int numNewline, int numTab, String elemName, String elemVal)
			throws XMLStreamException {
		for (int i = numNewline; i > 0; i--) {
			xtw.writeCharacters("\n");
		}
		for (int i = numTab; i > 0; i--) {
			xtw.writeCharacters("\t");
		}
		xtw.writeStartElement(elemName);
		xtw.writeCharacters(elemVal);
		xtw.writeEndElement();
	}

	private void initWriter() throws XMLStreamException, IOException {
		fXFactory = XMLOutputFactory.newInstance();
		xtw = fXFactory.createXMLStreamWriter(new FileWriter(new File(fFilePath, fFileName)));
	}
}
