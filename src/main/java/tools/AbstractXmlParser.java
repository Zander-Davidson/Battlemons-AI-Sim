package tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class AbstractXmlParser extends DefaultHandler {

	private String fFilePath;

	private HashMap<String, Boolean> fFields = new HashMap<String, Boolean>();

	public AbstractXmlParser(String filePath) {
		fFilePath = filePath;
	}

	/**
	 * defines action for processing element values. all children should override
	 * this method. will be called by characters() during parse.
	 * 
	 * @param key   an element of an .xml file
	 * @param value value of said element
	 */
	protected void handleElement(String key, String value) {
	}

	protected void parse() {
		try {
			File file = new File(fFilePath);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(file, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		for (Entry<String, Boolean> entry : fFields.entrySet()) {
			if (qName.equalsIgnoreCase(entry.getKey())) {
				entry.setValue(true);
				return;
			}
		}
		fFields.put(qName, true);
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		for (Entry<String, Boolean> entry : fFields.entrySet()) {
			if (entry.getValue()) {
				handleElement(entry.getKey(), new String(ch, start, length).trim());
				entry.setValue(false);
				return;
			}
		}
	}
}
