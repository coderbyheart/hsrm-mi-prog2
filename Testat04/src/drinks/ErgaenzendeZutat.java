package drinks;

/**
 * Klasse für eine ergänzende Zutat
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: ErgaenzendeZutat.java 205 2010-06-26 21:53:01Z m $
 */
public class ErgaenzendeZutat extends Zutat {

	private String herkunft;
	private String einheit;

	/**
	 * Einfacher Kontstruktor
	 */
	public ErgaenzendeZutat() {

	}

	/**
	 * Konstruktor für eine ergänzende Zutat
	 * 
	 * @param nummer
	 * @param name
	 * @param herstellungsLand
	 * @param einheit
	 * @param preis
	 */
	public ErgaenzendeZutat(int nummer, String name, String herstellungsLand,
			String einheit, float preis) {
		super(nummer, name, preis);
		this.herkunft = herstellungsLand;
		this.einheit = einheit;
	}

	/**
	 * Erstellt die Kopie einer Zutat
	 * 
	 * @return neuerstellte Kopie
	 */
	public ErgaenzendeZutat clone() {
		return new ErgaenzendeZutat(this.nummer, this.name, this.herkunft,
				this.einheit, this.preis);
	}

	/**
	 * Gibt die Beschreibung einer Zutat als String zurück
	 * 
	 * @return info
	 */
	public String toString() {
		return this.nummer + ": " + this.name + " - " + this.herkunft
				+ " - Einheit: " + this.einheit + " - " + (int) this.preis
				+ " BAC";
	}

	/**
	 * Gibt die Herkunft zurück
	 * 
	 * @return Herkunft
	 */
	public String getHerkunft() {
		return herkunft;
	}

	/**
	 * Setzt die Herkunft
	 * 
	 * @param herkunft
	 */
	public void setHerkunft(String herkunft) {
		this.herkunft = herkunft;
	}

	/**
	 * Gibt die Einheit zurück
	 * 
	 * @return Einheit
	 */
	public String getEinheit() {
		return einheit;
	}

	/**
	 * Setzt die Einheit
	 * 
	 * @param einheit
	 */
	public void setEinheit(String einheit) {
		this.einheit = einheit;
	}
}