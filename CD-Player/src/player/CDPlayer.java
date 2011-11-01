package player;

public class CDPlayer 
{
	private CD aktuelleCD;
	private int songNr;
	private boolean playState = false;

	public void insert(CD aktCD)
	{
		this.aktuelleCD = aktCD;
		this.play();
		this.songNr = 1;
	}
	
	public void play()
	{
		this.playState = true;
		this.showInfo();
	}
	
	/**
	 * Zum nächsten Titel springen
	 */
	public void skip()
	{
		this.songNr++;
		this.play();
	}
	
	/**
	 * Interne Funktion (und daher "private"), die aktuelle Informationen über CD und Song wiedergibt
	 */
	private void showInfo()
	{
		Song currentSong = this.getCD().getSong(this.songNr);
		// Korrigieren songNr bei falschem Index
		this.songNr = currentSong.getNr();
		System.out.println(
			(this.playState ? "Spiele: " : "Pause: ")
			+ " Track " + currentSong.getNr()
			+ " " + currentSong.getTitel()
		);
	}
	
	/**
	 * Zum vorigen Titel springen
	 */
	public void prev()
	{
		this.songNr--;
		this.play();
	}
	
	/**
	 * Gibt die aktuell abgespielte CD zurück
	 * 
	 * @return CD
	 */
	public CD getCD()
	{
		return this.aktuelleCD;
	}
}
