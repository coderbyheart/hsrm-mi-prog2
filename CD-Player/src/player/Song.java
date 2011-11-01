package player;

/**
 * Ein Titel auf einer Audio-CD
 * 
 * @author Markus Tacker <m@tacker.org>
 */
public class Song extends Media 
{
	private int nr;
	
	/**
	 * Setzt Nummer des Tracks auf der CD
	 * 
	 * @param Nummer
	 * @return this
	 */
	public Media setNr(int nr)
	{
		this.nr = nr;
		return this;
	}
	
	/**
	 * Gibt die Nummer des Tracks auf der CD zurück
	 * 
	 * @return Nummer
	 */
	public int getNr()
	{
		return this.nr;
	}
}
