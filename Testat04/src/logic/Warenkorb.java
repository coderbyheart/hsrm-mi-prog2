package logic;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * Einfacher Warenkorb
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: Warenkorb.java 211 2010-06-30 12:58:16Z m $
 * @param <T>
 */
public class Warenkorb<T> implements Iterable<WarenkorbEintrag<T>> {

	private LinkedList<WarenkorbEintrag<T>> items;
	private WarenkorbEintrag<T> last = null;

	/**
	 * Konstruktor
	 */
	public Warenkorb() {
		clear();
	}

	/**
	 * Itertiert über die Einträge des Warenkorbes
	 */
	public Iterator<WarenkorbEintrag<T>> iterator() {
		return items.iterator();
	}

	/**
	 * Fügt dem Warenkorb einen Eintrag hinzu
	 * 
	 * @param item
	 */
	public void add(T item) {
		for (WarenkorbEintrag<T> currItem : items) {
			if (currItem.getItem().equals(item)) {
				currItem.increaseAmount();
				last = currItem;
				return;
			}
		}
		WarenkorbEintrag<T> newItem = new WarenkorbEintrag<T>(item);
		last = newItem;
		items.add(newItem);
	}

	/**
	 * Entfernt das zuletzt hinzugefügte Item aus dem Warenkorb, bzw. verringert
	 * dessen Anzahl
	 */
	public void removeLastItem() {
		if (last == null)
			return;
		if (last.getAmount() > 1) {
			last.decreaseAmount();
			return;
		}
		items.removeLast();
		last = items.size() > 0 ? items.getLast() : null;
	}

	/**
	 * Prüft, ob der Warenkorb leer ist
	 * 
	 * @return Ob der Warenkorb leer ist
	 */
	public boolean isEmpty() {
		return items.size() <= 0;
	}
	
	/**
	 * Leert den Warenkorb
	 */
	public void clear()
	{
		items = new LinkedList<WarenkorbEintrag<T>>(); 
	}
}
