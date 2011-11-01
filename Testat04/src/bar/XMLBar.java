package bar;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import drinks.Drink;
import drinks.Zutat;

/**
 * Verwaltet die Zutaten und Drinks anhand von XML-Files
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: XMLBar.java 205 2010-06-26 21:53:01Z m $
 */
public class XMLBar extends DieBar {

	/**
	 * Konstruktor
	 * 
	 * @throws XMLBarException
	 */
	public XMLBar() throws XMLBarException {
		super();
		if (zutatenListe.size() == 0)
			throw new XMLBarException("Keine Zutaten geladen.");
	}

	/**
	 * Erstellt die Zutatenliste
	 * 
	 * Diese werden aus der XML-Datei eingelesen
	 */
	protected void initZutaten() {
		zutatenListe = new LinkedList<Zutat>();
		try {
			Document zutatenXML = parseDocument("data/Zutaten.xml");
			NodeList zutaten = zutatenXML.getDocumentElement().getChildNodes();
			for (int i = 0; i < zutaten.getLength(); i++) {
				Node zutatData = zutaten.item(i);
				Zutat zutat = Zutat.factory(zutatData.getAttributes()
						.getNamedItem("art").getNodeValue());
				fillData(zutat, zutatData.getChildNodes());
				zutatenListe.add(zutat);
			}
		} catch (ParserConfigurationException e) {
			System.out.println("Fehler in der Parser-Konfiguration.");
		} catch (SAXException e) {
			System.out.println("Fehler im SAX-Parser.");
		} catch (IOException e) {
			System.out.println("Fehler beim Lesen der Datei.");
		}
		System.out.println(zutatenListe.size() + " Zutaten eingelesen");
	}

	/**
	 * Befüllt das Objekt el mit den Daten aus childNodes mittels dessen Setter
	 * 
	 * <name>value</name> wird zu el.setName("value");
	 * 
	 * @param el
	 * @param childNodes
	 */
	private void fillData(Object el, NodeList childNodes) {
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node n = childNodes.item(i);
			String nValue = n.getTextContent();
			if (nValue.length() == 0)
				continue;
			Method setter = null;
			Class<?> zClass = el.getClass();
			String methodName = "set" + ucFirst(n.getNodeName());
			try {
				setter = zClass.getMethod(methodName, String.class);
				setter.invoke(el, nValue);
			} catch (SecurityException e1) {
				System.out.println("Kann auf " + zClass.getName() + "."
						+ methodName + " nicht zugreifen.");
			} catch (NoSuchMethodException e1) {
				System.out.println("Methode fehlt: " + zClass.getName() + "."
						+ methodName);
			} catch (IllegalArgumentException e) {
				System.out.println("Ungültige Argumente für: "
						+ zClass.getName() + "." + methodName);
			} catch (IllegalAccessException e) {
				System.out.println("Ungültige Argumente für: "
						+ zClass.getName() + "." + methodName);
			} catch (InvocationTargetException e) {
				System.out.println("Kann Methode nicht aufrufen: "
						+ zClass.getName() + "." + methodName);
			}
		}
	}

	/**
	 * Macht das erste Zeichen eines Strings GROß.
	 * 
	 * @param string
	 * @return String
	 */
	private String ucFirst(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * Erstellt die Drinkliste
	 * 
	 * Diese werden aus der XML-Datei eingelesen
	 */
	protected void initDrinks() {
		drinkList = new LinkedList<Drink>();

		try {
			Document drinksXML = parseDocument("data/Drinks.xml");
			NodeList drinks = drinksXML.getDocumentElement().getChildNodes();
			for (int i = 0; i < drinks.getLength(); i++) {
				Node drinkData = drinks.item(i);
				Drink drink = new Drink();
				NodeList drinkInfo = drinkData.getChildNodes();
				fillData(drink, drinkInfo);
				// Sonder-Daten
				Node customAttr = drinkData.getAttributes().getNamedItem(
						"custom");
				if (customAttr != null)
					drink.isCustom(customAttr.getNodeValue().equals("true"));
				// Zutaten
				fillZutaten(drink, drinkInfo);
				drinkList.add(drink);
			}
		} catch (ParserConfigurationException e) {
			System.out.println("Fehler in der Parser-Konfiguration.");
		} catch (SAXException e) {
			System.out.println("Fehler im SAX-Parser.");
		} catch (IOException e) {
			System.out.println("Fehler beim Lesen der Datei.");
		}
		System.out.println(drinkList.size() + " Drinks eingelesen");

	}

	/**
	 * Befüllt den Drink mit Zutaten aus den childNodes
	 * 
	 * @param drink
	 * @param childNodes
	 */
	private void fillZutaten(Drink drink, NodeList childNodes) {
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node n = childNodes.item(i);
			if (!n.getNodeName().equals("zutat"))
				continue;
			int nummer = Integer.parseInt(n.getAttributes().getNamedItem(
					"nummer").getNodeValue());
			int menge = Integer.parseInt(n.getAttributes()
					.getNamedItem("menge").getNodeValue());
			for (int j = 0; j < menge; j++) {
				drink.addZutat(getZutatByNumber(nummer));
			}
		}
	}

	/**
	 * Entfernt whitespace aus dem Document
	 * 
	 * @see http://forums.java.net/jive/thread.jspa?messageID=345459
	 * @param e
	 */
	private static void removeWhitespaceNodes(Element e) {
		NodeList children = e.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--) {
			Node child = children.item(i);
			if (child instanceof Text
					&& ((Text) child).getData().trim().length() == 0) {
				e.removeChild(child);
			} else if (child instanceof Element) {
				removeWhitespaceNodes((Element) child);
			}
		}
	}

	/**
	 * Parst ein XML-File
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @return Document
	 */
	private Document parseDocument(String xmlFile)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory domBuilderFactory = DocumentBuilderFactory
				.newInstance();
		domBuilderFactory.setIgnoringElementContentWhitespace(true);
		domBuilderFactory.setIgnoringComments(true);
		DocumentBuilder domBuilder = domBuilderFactory.newDocumentBuilder();
		Document xmlDom = domBuilder.parse(xmlFile);
		removeWhitespaceNodes(xmlDom.getDocumentElement());
		return xmlDom;
	}
}
