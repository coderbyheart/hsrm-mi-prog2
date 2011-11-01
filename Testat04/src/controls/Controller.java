package controls;

import gui.UserInterface;
import bar.XMLBar;
import bar.XMLBarException;

/**
 * Der GUI Controller
 * 
 * Instanziert die Bar auf XML-Basis und startet des GUI
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: Controller.java 205 2010-06-26 21:53:01Z m $
 */
public class Controller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			XMLBar bar = new XMLBar();
			new UserInterface(bar);
		} catch (XMLBarException e) {
			System.out.println("Fehler beim öffnen der Bar.");
			System.out.println(e.getMessage());
		}
	}

}
