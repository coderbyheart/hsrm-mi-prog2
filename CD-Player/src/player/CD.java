package player;

import java.util.LinkedList;

/**
 * Eine Audio-CD
 * 
 * @author Markus Tacker <m@tacker.org>
 */
public class CD 
{
	private LinkedList<Song> songs;
	private int ean;
	private String titel;
	private String interpret;
	private int veroeffentlichung;
	
	/**
	 * Gibt die Anzahl der Songs auf der CD zurück
	 * 
	 * @return Anzahl der Songs
	 */
	public int getSongAnzahl()
	{
		return this.songs.size();
	}
	
	/**
	 * Gibt den Song mit der Nummer nr zurück
	 * 
	 * Wenn es den Song mit der gewünschten Nummer nicht gibt, wird der nächstliegende zurück gegeben
	 * 
	 * @return Song
	 */
	public Song getSong(int nr)
	{
		if (nr < 1) {
			nr = 1;
		} else if(nr > this.songs.size()) {
			nr = this.songs.size();
		}
		return this.songs.get(nr - 1);
	}
	
	/**
	 * Fügt der CD einen Song hinzu
	 * 
	 * @param Song
	 * @return this
	 */
	public Song addSong(Song newSong)
	{
		newSong.setNr(this.songs.size() + 1);
		this.songs.add(newSong);
		return this;
	}
	
	/**
	 * Setzt den Titel der CD
	 * 
	 * @param Titel
	 * @return this
	 */
	public CD setTitel(String titel)
	{
		this.titel = titel;
		return this;
	}
	
	/**
	 * Gibt den Titel der CD zurück
	 * 
	 * @return Titel
	 */
	public String getTitel()
	{
		return this.titel;
	}
}
