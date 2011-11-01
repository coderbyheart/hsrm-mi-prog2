package drinks;

/**
 * Basis-Klasse für alle Zutaten
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: Zutat.java 205 2010-06-26 21:53:01Z m $
 */
public class Zutat {

	protected int nummer;
	protected String name;
	protected float preis;

	/**
	 * Basiskonstruktor für Zutaten
	 * 
	 * @param nummer
	 * @param name
	 * @param preis
	 */
	public Zutat(int nummer, String name, float preis) {
		this.nummer = nummer;
		this.preis = preis;
		this.name = name;

	}

	/**
	 * Einfacher Konstruktor
	 */
	public Zutat() {
	}

	/**
	 * Überprüft die Zutat die Nummer nr hat
	 * 
	 * @return boolean
	 */
	public boolean hatNummer(int nr) {
		return getNummer() == nr;
	}

	/**
	 * Gibt die Beschreibung einer Zutat als String zurück
	 * 
	 * @return Beschreibung
	 */
	public String toString() {
		return this.nummer + ": " + this.name + " - " + (int) this.preis
				+ " BAC";
	}

	/**
	 * Gibt den Namen einer Zutat zurück
	 * 
	 * @return Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gibt die Bestellnummer einer Zutat zurück
	 * 
	 * @return Nummer
	 */
	public int getNummer() {
		return this.nummer;
	}

	/**
	 * Erstellt eine Kopie eines Zutatenobjekts
	 */
	public Zutat clone() {
		return new Zutat(this.nummer, this.name, this.preis);
	}

	/**
	 * Factory für eine Zutat
	 * 
	 * @param Typ
	 *            der Zutat (Klassenname)
	 */
	@SuppressWarnings("unchecked")
	public static Zutat factory(String type) {
		Zutat zutat = null;
		String className = "drinks." + type;
		try {
			Class<Zutat> zClass;
			zClass = (Class<Zutat>) Class.forName(className);
			try {
				zutat = zClass.newInstance();
			} catch (InstantiationException e) {
				System.out.println("Kann Klasse nicht instanzieren: "
						+ className);
			} catch (IllegalAccessException e) {
				System.out.println("Kann auf Klasse nicht zugreifen: "
						+ className);
			}
		} catch (ClassNotFoundException e1) {
			System.out.println("Klasse existiert nicht: " + className);
		}
		if (zutat == null) {
			zutat = new Zutat();
		}
		return zutat;
	}

	/**
	 * Gibt den Preis zurück
	 * 
	 * @return Preis
	 */
	public float getPreis() {
		return preis;
	}

	/**
	 * Gibt den Preis als String zurück
	 * 
	 * @return
	 */
	public String getPreisAsString() {
		return Drink.formatPrice(getPreis());
	}

	/**
	 * Setzt den Preis
	 * 
	 * @param Preis
	 */
	public void setPreis(float preis) {
		this.preis = preis;
	}

	/**
	 * Setzt den Preis
	 * 
	 * @param Preis
	 */
	public void setPreis(String preis) {
		setPreis(Float.parseFloat(preis));
	}

	/**
	 * Setzt die Nummer
	 * 
	 * @param Preis
	 */
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	/**
	 * Setzt die Nummer
	 * 
	 * @param Preis
	 */
	public void setNummer(String nummer) {
		setNummer(Integer.parseInt(nummer));
	}

	/**
	 * Setzt den Namen
	 * 
	 * @param Name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
