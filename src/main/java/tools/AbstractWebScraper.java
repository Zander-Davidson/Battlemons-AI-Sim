package tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class AbstractWebScraper {

	private final String fBrowserVersion;
	private String fUrl;
	private final Document fDocument; // Document object from html

	public AbstractWebScraper(String browserVersion, String url) throws IOException {
		fBrowserVersion = browserVersion;
		fUrl = url;
		fDocument = Jsoup.connect(fUrl).userAgent(fBrowserVersion).get();
	}

	protected Document getDocument() {
		return fDocument;
	}

	public void writeHtml(String htmlDestination, String htmlName) {
		FileWriter htmlFileWriter;
		try {
			// create new file in specified directory
			htmlFileWriter = new FileWriter(new File(htmlDestination, htmlName));

			// create PrintWriter and print html to the newly-created file
			PrintWriter htmlPrintWriter = new PrintWriter(htmlFileWriter);
			htmlPrintWriter.print(fDocument.html());

			// clean-up
			htmlPrintWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
