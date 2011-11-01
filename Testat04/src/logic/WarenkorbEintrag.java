package logic;

/**
 * Eintrag im Warenkorb
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: WarenkorbEintrag.java 211 2010-06-30 12:58:16Z m $
 * @param <T>
 */
public class WarenkorbEintrag<T> {
	private T item;
	private int amount;

	/**
	 * Konstruktor
	 * 
	 * @param Das
	 *            Datenelement
	 */
	public WarenkorbEintrag(T item) {
		this.item = item;
		amount = 1;
	}

	/**
	 * Anzahl des Elements erhöhen
	 */
	public void increaseAmount() {
		amount++;

	}

	/**
	 * Anzahl des Elements zurückgeben
	 * 
	 * @return Anzahl
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Gibt das Datenelement zurück
	 * 
	 * @return Datenelement
	 */
	public T getItem() {
		return item;
	}

	/**
	 * Anzahl des Elements verringern
	 */
	public void decreaseAmount() {
		amount--;
	}
}