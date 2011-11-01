package drinks;

/**
 * Klasse für eine alkoholische Zutat
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: AlkoholischeZutat.java 205 2010-06-26 21:53:01Z m $
 */
public class AlkoholischeZutat extends Zutat {
	private int vol;
	private String farbe;

	/**
	 * Einfacher Konstruktor
	 */
	public AlkoholischeZutat() {

	}

	/**
	 * Konstruktor für eine alkoholische Zutat
	 * 
	 * @param nummer
	 * @param name
	 * @param vol
	 * @param farbe
	 * @param preis
	 */
	public AlkoholischeZutat(int nummer, String name, int vol, String farbe,
			float preis) {
		super(nummer, name, preis);
		this.vol = vol;
		this.farbe = farbe;
	}

	/**
	 * Erstellt die Kopie einer Zutat
	 * 
	 * @return neuerstellte Kopie
	 */
	public AlkoholischeZutat clone() {
		return new AlkoholischeZutat(this.nummer, this.name, this.vol,
				this.farbe, this.preis);
	}

	/**
	 * Gibt die Beschreibung einer Zutat als String zurück
	 * 
	 * @return info
	 */
	public String toString() {
		String info = this.nummer + ": " + this.name + " - " + this.vol
				+ "% Vol. - Farbe: " + this.farbe + " - " + (int) this.preis
				+ " BAC";
		return info;
	}

	/**
	 * Alkoholgehalt zurückgeben
	 * 
	 * @return Alkoholgehalt
	 */
	public int getVol() {
		return vol;
	}

	/**
	 * Alkoholgehalt setzen
	 * 
	 * @param Alkoholgehalt
	 */
	public void setVol(int vol) {
		this.vol = vol;
	}

	/**
	 * Alkoholgehalt setzen
	 * 
	 * @param Alkoholgehalt
	 */
	public void setVol(String vol) {
		setVol(Integer.parseInt(vol));
	}

	/**
	 * Farbe zurückgeben
	 * 
	 * @return Farbe
	 */
	public String getFarbe() {
		return farbe;
	}

	/**
	 * Farbe setzen
	 * 
	 * @param Farbe
	 */
	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}
}