package drinks;

/**
 * Klasse für die eisförmige Zutaten
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: Eis.java 205 2010-06-26 21:53:01Z m $
 */
public class Eis extends Zutat {
	private float temp;

	/**
	 * Einfacher Kontstruktor
	 */
	public Eis() {

	}

	/**
	 * Konstruktor für eine ergänzende Zutat
	 * 
	 * @param nummer
	 * @param name
	 * 
	 * @param preis
	 */
	public Eis(int nummer, String name, float temp, float preis) {
		super(nummer, name, preis);
		this.temp = temp;
	}

	/**
	 * Erstellt die Kopie einer Zutat
	 * 
	 * @return neuerstellte Kopie
	 */
	public Eis clone() {
		return new Eis(this.nummer, this.name, this.temp, this.preis);
	}

	/**
	 * Gibt die Beschreibung einer Zutat als String zurück
	 * 
	 * @return info
	 */
	public String toString() {
		return this.nummer + ": " + this.name + " bei " + this.temp
				+ " °C Grad - " + (int) this.preis + " BAC";
	}

	/**
	 * Gibt die Temperatur zurück
	 * 
	 * @return Temperatur
	 */
	public float getTemp() {
		return temp;
	}

	/**
	 * Setzt die Temperatur
	 * 
	 * @param temp
	 */
	public void setTemp(float temp) {
		this.temp = temp;
	}

	/**
	 * Setzt die Temperatur
	 * 
	 * @param temp
	 */
	public void setTemp(String temp) {
		setTemp(Float.parseFloat(temp));
	}
}