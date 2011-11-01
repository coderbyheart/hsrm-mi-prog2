package drinks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Klasse für einen Drink
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: Drink.java 213 2010-06-30 13:39:50Z m $
 */
public class Drink {

	private String beschreibung;
	private int nummer;
	private String name;
	private Map<Integer, Zutat> drinkZutaten = new HashMap<Integer, Zutat>();
	private boolean isCustom = false;
	private Zustaende zustand;

	/**
	 * Einfacher Kontstruktur
	 */
	public Drink() {
	}

	/**
	 * Basiskonstruktor für Drinks
	 * 
	 * @param nummer
	 * @param name
	 * @param info
	 */
	public Drink(int nummer, String name, String info) {
		this.beschreibung = info;
		this.nummer = nummer;
		this.name = name;
	}

	/**
	 * Um Zutatenliste erweiterter Konstruktor für Drinks
	 * 
	 * 
	 * @param nummer
	 * @param name
	 * @param info
	 * @param drinkZutaten
	 */
	public Drink(int nummer, String name, String info,
			Map<Integer, Zutat> drinkZutaten) {
		this.beschreibung = info;
		this.nummer = nummer;
		this.name = name;
		this.drinkZutaten = drinkZutaten;
	}

	/**
	 * Um Zutatenliste und Preis erweiterter Konstruktor für Drinks
	 * 
	 * @param nummer
	 * @param name
	 * @param info
	 * @param LinkedList
	 */
	public Drink(int nummer, String name, String info,
			LinkedList<Zutat> LinkedList) {
		this.beschreibung = info;
		this.nummer = nummer;
		this.name = name;
	}

	/**
	 * Hinzufügen einer Zutat zut Zutatenliste
	 * 
	 * @param z
	 */
	public void addZutat(Zutat z) {
		if (!canAddZutat())
			return;
		drinkZutaten.put(z.getNummer(), z);
	}

	/**
	 * Berechnung des Getränkepreises
	 * 
	 * @param LinkedList
	 * @return price Berechneter Preis
	 */
	public float getPreis() {
		float price = 0.0f;

		for (Zutat zutat : this.drinkZutaten.values()) {
			price += zutat.preis;
		}
		return price;

	}

	/**
	 * Rückgabe des Preises als String
	 * 
	 * @return
	 */
	public String getPreisAsString() {
		return Drink.formatPrice(getPreis());
	}

	/**
	 * Formatiert einen Preis
	 * 
	 * @param preis
	 * @return String
	 */
	public static String formatPrice(float price) {
		int bac = (int) price * 100;
		int cent = bac % 100;
		String centString = "";
		if (cent > 0) {
			centString += ",";
			if (cent < 10)
				centString += "0";
			centString += cent;
		}
		return bac / 100 + centString + " BAC";
	}

	/**
	 * Gibt die Beschreibung eines Drinks zurück
	 * 
	 * @return
	 */
	public String toString() {
		return this.nummer + ": " + this.name + " - " + this.getPreisAsString()
				+ "\n" + this.beschreibung;
	}

	/**
	 * Gibt den Namen eines Drinks zurück
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gibt die Bestellnummer einer Drinks zurück
	 * 
	 * @return
	 */
	public int getNummer() {
		int nummer = this.nummer;
		return nummer;
	}

	/**
	 * Erstellt die Kopie eines Drinks
	 */
	public Drink clone() {
		Drink myClone = new Drink(this.nummer, this.name, this.beschreibung);
		myClone.isCustom(isCustom());
		for (Zutat zutat : this.drinkZutaten.values()) {
			myClone.addZutat(zutat);
		}
		return myClone;
	}

	/**
	 * Überprüft ob ein Getränk "Custom" ist
	 * 
	 * @return Boolean
	 */
	public boolean isCustom() {
		return isCustom;
	}

	/**
	 * Gibt an ob ein Getränk "Custom" ist
	 * 
	 * @param boolean
	 */
	public void isCustom(boolean b) {
		isCustom = b;
	}

	/**
	 * Überprüft, ob eine weitere Zutat zur Zutatenliste hinzugefügt werden kann
	 * 
	 * @return Boolean
	 */
	public boolean canAddZutat() {
		if (!isCustom())
			return true;
		return drinkZutaten.size() < getZutatenLimit();
	}

	/**
	 * Gibt das Limit an möglichen Zutaten zurück
	 * 
	 * @return Limit oder Null
	 */
	public int getZutatenLimit() {
		return isCustom() ? 8 : null;
	}

	/**
	 * Übergibt die Zuatenliste eines Customdrinks
	 * 
	 * @return Liste mit Zutaten
	 */
	public Map<Integer, Zutat> getZutaten() {
		return this.drinkZutaten;
	}

	/**
	 * Überprüft ob er Drink die Nummer nr hat
	 * 
	 * @return boolean
	 */
	public boolean hatNummer(int nr) {
		return getNummer() == nr;
	}

	/**
	 * Gibt die Beschreibung des Drinks zurück
	 * 
	 * @return Beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Setzt die Beschreibung des Drinks
	 * 
	 * @param beschreibung
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Setzt die Nummer des Drinks
	 * 
	 * @param nummer
	 */
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	/**
	 * Setzt die Nummer des Drinks
	 * 
	 * @param nummer
	 */
	public void setNummer(String nummer) {
		setNummer(Integer.parseInt(nummer));

	}

	/**
	 * Setzt den Namen des Drinks
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Vergleicht zwei Drinks
	 * 
	 * @param d
	 * @return true, wenn die Drinks gleich sind, ansonsten false
	 */
	public boolean equals(Object d) {
		boolean equal = ((Drink) d).getNummer() == getNummer()
				&& ((Drink) d).getZustand().equals(getZustand());
		return equal;
	}

	public void setZustand(Zustaende status) {
		this.zustand = status;
	}

	public Zustaende getZustand() {
		return zustand;
	}

	public String getZustandAsString() {
		switch (zustand) {
		case GESCHUETTELT:
			return "geschüttelt";
		case GERUEHRT:
			return "gerührt";
		case GEFROREN:
			return "gefroren";
		}
		return "kein Zustand";
	}
}