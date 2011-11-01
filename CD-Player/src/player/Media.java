package player;

/**
 * Allgemeine Klasse für Medien
 * 
 * @author Markus Tacker <m@tacker.org>
 */
abstract class Media 
{
	private String titel;
	private float laenge;
	
	/**
	 * Setzt den Titel des Mediums
	 * 
	 * @param Titel
	 * @return this
	 */
	public Media setTitel(String titel)
	{
		this.titel = titel;
		return this;
	}
	
	/**
	 * Gibt den Titel des Mediums zurück
	 * 
	 * @return Titel
	 */
	public String getTitel()
	{
		return this.titel;
	}
	
	/**
	 * Setzt die Spieldauer (Länge) des Mediums als float
	 * 
	 * @param Spieldauer / Länge
	 * @return this
	 */
	public Media setLaenge(float laenge)
	{
		this.laenge = laenge;
		return this;
	}
	
	/**
	 * Gibt die Spieldauer (Länge) des Mediums als float zurück
	 * 
	 * @return Spieldauer / Länge
	 */
	public float getLaenge()
	{
		return this.laenge;
	}
}
