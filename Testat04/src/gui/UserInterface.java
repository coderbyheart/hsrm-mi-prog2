package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import logic.Warenkorb;
import logic.WarenkorbEintrag;


import bar.XMLBar;
import drinks.Drink;
import drinks.Zutat;

/**
 * GUI für die Bar
 * 
 * @author Markus Tacker <m@tacker.org>
 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
 * @version $Id: UserInterface.java 213 2010-06-30 13:39:50Z m $
 */
public class UserInterface {

	private XMLBar bar;
	private JTextArea warenkorbPanel;
	private boolean orderView = true;
	private JPanel moo;
	private JFrame frame;
	private JScrollPane scrollPane;
	private Warenkorb<Drink> warenkorb;
	private Drink currentDrink;
	private JButton undoOrder;

	/**
	 * Erzeugt das GUI
	 */
	public UserInterface(XMLBar bar) {
		this.bar = bar;
		warenkorb = new Warenkorb<Drink>();

		frame = new JFrame("MOO - Milliway's Online Order");
		frame.setSize(600, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		moo = new JPanel();
		moo.setLayout(new BoxLayout(moo, BoxLayout.Y_AXIS));

		JPanel head = new JPanel();
		head.setSize(new Dimension(moo.getWidth(), 50));
		JLabel headline = new JLabel(
				"Willkommen bei MOO - Milliway's Online Order!");
		head.add(headline);
		moo.add(head);

		scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(moo.getWidth(), 200));
		moo.add(scrollPane);
		showDrinkView();

		JPanel warenPanel = new JPanel(new BorderLayout());
		warenkorbPanel = new JTextArea(10, 1);
		warenPanel.add(warenkorbPanel);
		moo.add(warenPanel);
		updateOrderPanel();

		// Buttons dazu
		JPanel buttonPanel = new JPanel();
		JButton finishOrder = new JButton("Finish!");
		finishOrder.addActionListener(new SubmitAction());
		undoOrder = new JButton("Undo!");
		undoOrder.addActionListener(new UndoAction());
		JButton cancelOrder = new JButton("Cancel!");
		cancelOrder.addActionListener(new CancelAction());
		buttonPanel.add(finishOrder);
		buttonPanel.add(undoOrder);
		buttonPanel.add(cancelOrder);
		moo.add(buttonPanel);

		frame.add(moo);
		frame.setVisible(true);
	}

	/**
	 * Zeigt das die Getränkekarte an
	 */
	private void showDrinkView() {
		orderView = true;
		JPanel karte = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.NORTH;
		if (undoOrder != null)
			undoOrder.setVisible(true);

		int startStLine = 0;
		int startNdLine = 1;

		for (Drink drink : bar.getDrinks()) {

			JLabel drinkLabel = new JLabel(drink.getNummer() + " - "
					+ drink.getName());
			constraints.gridx = 0;
			constraints.weightx = 4;
			constraints.gridy = startStLine;
			constraints.anchor = GridBagConstraints.WEST;
			karte.add(drinkLabel, constraints);

			JLabel preis = new JLabel(drink.getPreisAsString());
			constraints.gridx = 5;
			constraints.weightx = 1;
			constraints.gridy = startStLine;
			constraints.anchor = GridBagConstraints.NORTH;
			karte.add(preis, constraints);

			JTextArea text = new JTextArea(drink.getBeschreibung());
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			text.setBackground(null);
			text.setEditable(false);
			text.setSize(480, 50);
			constraints.gridx = 0;
			constraints.weightx = 4;
			constraints.gridy = startNdLine;
			constraints.anchor = GridBagConstraints.WEST;
			karte.add(text, constraints);

			addDrinkOrderButton("Order", karte, constraints, startNdLine, drink);

			startStLine += 2;
			startNdLine += 2;
		}

		scrollPane.getViewport().setView(karte);
	}

	/**
	 * Zeigt die Zutatenauswahl für Custom-Drinks an
	 */
	private void showZutatenView() {
		orderView = false;
		JPanel karte = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.NORTH;
		if (undoOrder != null)
			undoOrder.setVisible(false);

		int startStLine = 0;
		for (Zutat zutat : bar.getZutaten()) {

			addZutatenOrderButton("Rein da!", karte, constraints, startStLine,
					zutat);

			JLabel text = new JLabel(zutat.toString());
			constraints.gridx = 1;
			constraints.weightx = 4;
			constraints.gridy = startStLine;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.insets = new Insets(0, 40, 0, 0);
			karte.add(text, constraints);

			startStLine += 1;
		}

		scrollPane.getViewport().setView(karte);
	}

	/**
	 * Erzeugt einen Button zum Bestellen von Zutaten
	 * 
	 * @param label
	 * @param karte
	 * @param constraints
	 * @param startNdLine
	 * @param zutat
	 */
	private void addZutatenOrderButton(String label, JPanel karte,
			GridBagConstraints constraints, int startNdLine, Zutat zutat) {
		ZutatenAction buttonAction = new ZutatenAction(zutat);
		JButton button = new JButton(label);
		button.setSize(100, 20);
		constraints.gridx = 0;
		constraints.weightx = 1;
		constraints.gridy = startNdLine;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(0, 0, 0, 0);
		button.addActionListener(buttonAction);
		karte.add(button, constraints);

	}

	/**
	 * Erzeugt einen Button zum Bestellen von Drinks
	 * 
	 * @param label
	 * @param karte
	 * @param constraints
	 * @param startNdLine
	 * @param drink
	 */
	private void addDrinkOrderButton(String label, Container karte,
			GridBagConstraints constraints, int startNdLine, Drink drink) {
		OrderAction buttonAction = new OrderAction(drink);
		JButton button = new JButton(label);
		button.setSize(100, 20);
		constraints.gridx = 5;
		constraints.weightx = 1;
		constraints.gridy = startNdLine;
		constraints.anchor = GridBagConstraints.NORTH;
		button.addActionListener(buttonAction);
		karte.add(button, constraints);
	}

	/**
	 * Fügt dem Warenkorb eine Getränk hinzu
	 * 
	 * @param drink
	 */
	private void orderDrink(Drink drink) {
		warenkorb.add(drink);
		updateOrderPanel();
	}

	/**
	 * Fügt dem aktuellen Custom-Drink einen Zutat hinzu
	 * 
	 * @param zutat
	 */
	public void addZutat(Zutat zutat) {
		currentDrink.addZutat(zutat.clone());
		updateZutatenPanel();
	}

	/**
	 * Aktualisiert die Bestell-Anzeige
	 */
	private void updateOrderPanel() {
		String orderSummary = "Ihre aktuelle Bestellung:\n\n";
		float orderPrice = 0;

		if (warenkorb.isEmpty()) {
			orderSummary += "Keine Getränke bestellt.\n";
		} else {
			for (WarenkorbEintrag<Drink> entry : warenkorb) {
				Drink drink = entry.getItem();
				orderSummary += entry.getAmount() + "x " + drink.getName()
						+ " - " + drink.getZustandAsString() + " - " + drink.getPreisAsString() + "\n";
				orderPrice += drink.getPreis() * entry.getAmount();
			}

		}

		orderSummary += "\nSumme: " + Drink.formatPrice(orderPrice);

		warenkorbPanel.setText(orderSummary);

	}

	/**
	 * Aktualisiert die Custom-Drink-Anzeige
	 */
	private void updateZutatenPanel() {
		String orderSummary = "Ihre aktueller Drink:\n";

		if (currentDrink.getZutaten().isEmpty()) {
			orderSummary += "Keine Zutaten hinzugefügt.\n";
		} else {
			for (Zutat zutat : currentDrink.getZutaten().values()) {
				orderSummary += "\n" + zutat.toString();

			}

		}
		warenkorbPanel.setText(orderSummary);
	}

	/**
	 * Zeigt die Zutatenkonfiguration für einen neuen Drink an
	 * 
	 * @param drink
	 */
	private void showDrinkConfigurator(Drink drink) {
		currentDrink = drink.clone();
		showZutatenView();
	}

	/**
	 * Macht die letzte Bestellung rückgängig
	 */
	private void undoOrder() {
		warenkorb.removeLastItem();
		updateOrderPanel();
	}

	/**
	 * Bricht die Bestellung ab
	 */
	private void cancelOrder() {
		warenkorb.clear();
		updateOrderPanel();
	}

	/**
	 * Bestimmt den Zustand des bestellten Drinks
	 */

	private void checkZustand(Drink drink) {
		Object[] options = { "Geschüttelt", "Gerührt", "Gefroren" };
		int statusNummer = JOptionPane.showOptionDialog(frame,
				"Welcher Zustand darf's denn sein?", "Extended Drink Options",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[2]);
		if (statusNummer == JOptionPane.CLOSED_OPTION) {
			System.out.println("nothingdone");
			return;
		} else {
			switch (statusNummer) {
			case 0:
				drink.setZustand(drinks.Zustaende.GESCHUETTELT);
				break;
			case 1:
				drink.setZustand(drinks.Zustaende.GERUEHRT);
				break;
			case 2:
				drink.setZustand(drinks.Zustaende.GEFROREN);
				break;
			}

			orderDrink(drink);

		}
	}

	/**
	 * Bricht die Bestellung ab
	 */
	private void placeOrder() {
		JOptionPane
				.showMessageDialog(frame, "Vielen Dank für Ihre Bestellung.");
		warenkorb.clear();
		updateOrderPanel();
	}

	/**
	 * Bestellt einen Drink
	 * 
	 * @author Markus Tacker <m@tacker.org>
	 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
	 * @version $Id: UserInterface.java 213 2010-06-30 13:39:50Z m $
	 */
	private class OrderAction implements ActionListener {
		private Drink drink;

		/**
		 * Konstruktor
		 * 
		 * @param drink
		 */
		private OrderAction(Drink drink) {
			this.drink = drink;
		}

		/**
		 * Handler für AWT Events
		 */
		public void actionPerformed(ActionEvent event) {
			if (drink.isCustom()) {
				showDrinkConfigurator(drink);
			} else {
				checkZustand(drink.clone());
			}
		}
	}

	/**
	 * Führt die Bestellung aus
	 * 
	 * @author Markus Tacker <m@tacker.org>
	 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
	 * @version $Id: Warenkorb.java 206 2010-06-26 21:54:21Z m
	 */
	private class SubmitAction implements ActionListener {

		/**
		 * Handler für AWT Events
		 */
		public void actionPerformed(ActionEvent event) {
			if (orderView) {
				placeOrder();
			} else {
				checkZustand(currentDrink);
				currentDrink = null;
				showDrinkView();
				updateOrderPanel();
			}
		}

	}

	/**
	 * Fügt Zutaten zum Drink hinzu
	 * 
	 * @author Markus Tacker <m@tacker.org>
	 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
	 * @version $Id: Warenkorb.java 206 2010-06-26 21:54:21Z m
	 */
	private class ZutatenAction implements ActionListener {

		private Zutat zutat;

		/**
		 * Konstruktor
		 * 
		 * @param zutat
		 */
		private ZutatenAction(Zutat zutat) {
			this.zutat = zutat;
		}

		/**
		 * Handler für AWT Events
		 */
		public void actionPerformed(ActionEvent event) {
			addZutat(zutat);
		}

	}

	/**
	 * Macht die letzte Aktion rückgängi
	 * 
	 * @author Markus Tacker <m@tacker.org>
	 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
	 * @version $Id: Warenkorb.java 206 2010-06-26 21:54:21Z m
	 */
	private class UndoAction implements ActionListener {

		/**
		 * Handler für AWT Events
		 */
		public void actionPerformed(ActionEvent event) {
			undoOrder();
		}

	}

	/**
	 * Bricht die Bestellung ab
	 * 
	 * @author Markus Tacker <m@tacker.org>
	 * @author Philipp Siegmund <philipp.siegmund@googlemail.com>
	 * @version $Id: Warenkorb.java 206 2010-06-26 21:54:21Z m
	 */
	private class CancelAction implements ActionListener {

		/**
		 * Handler für AWT Events
		 */
		public void actionPerformed(ActionEvent event) {
			if (orderView) {
				cancelOrder();
			} else {
				showDrinkView();
				updateOrderPanel();
			}
		}

	}

}
