package drinks;

/**
 * Klasse für eine nicht-alkoholische Zutat
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: NichtAlkoholischeZutat.java 205 2010-06-26 21:53:01Z m $
 */
public class NichtAlkoholischeZutat extends Zutat {
	private String farbe;

	/**
	 * Einfacher Kontstruktur
	 */
	public NichtAlkoholischeZutat() {

	}

	/**
	 * Konstruktor für eine nichtalkoholische Zutat
	 * 
	 * @param nummer
	 * @param name
	 * @param farbe
	 * @param preis
	 */
	public NichtAlkoholischeZutat(int nummer, String name, String farbe,
			float preis) {
		super(nummer, name, preis);
		this.farbe = farbe;

	}

	/**
	 * Erstellt die Kopie einer Zutat
	 * 
	 * @return neuerstellte Kopie
	 */
	public NichtAlkoholischeZutat clone() {
		return new NichtAlkoholischeZutat(this.nummer, this.name, this.farbe,
				this.preis);
	}

	/**
	 * Gibt die Beschreibung einer Zutat als String zurück
	 * 
	 * @return info
	 */
	public String toString() {
		return this.nummer + ": " + this.name + " - Farbe: " + this.farbe
				+ " - " + (int) this.preis + " BAC";
	}

	/**
	 * Gibt die Farbe zurück
	 * 
	 * @return Farbe
	 */
	public String getFarbe() {
		return farbe;
	}

	/**
	 * Setzt die Farbe
	 * 
	 * @param Farbe
	 */
	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}
}