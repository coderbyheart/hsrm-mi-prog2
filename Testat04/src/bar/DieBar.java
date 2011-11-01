package bar;
import java.util.LinkedList;
import drinks.*;

/**
 * Stell die Drinks und Zutaten zur Verfügung
 */
public class DieBar 
{	
	protected LinkedList<Zutat> zutatenListe;
	protected LinkedList<Drink> drinkList;
	
	/**
	 * Konstruktor für das Objekt "DieBar"
	 */
	public DieBar()
	{
		 initZutaten();
		 initDrinks();
	}
	
	/**
	 * Erstellt die Zutatenliste
	 */
	protected void initZutaten()
	{
		zutatenListe = new LinkedList<Zutat>();
		zutatenListe.add(new AlkoholischeZutat(10, "Janx-Geist", 75, "violett", 67.0f));
		zutatenListe.add(new AlkoholischeZutat(11, "Schwarzes Loch", 45, "schwarz", 45.0f));
		zutatenListe.add(new AlkoholischeZutat(12, "Rotwein", 12, "rot", 17.0f));
		zutatenListe.add(new AlkoholischeZutat(13, "Froschstern Champagner", 17, "laubgrün", 450.0f));
		zutatenListe.add(new NichtAlkoholischeZutat(20, "Wasser aus den Meeren von Santraginus V", "durchsichtig", 5.0f));
		zutatenListe.add(new NichtAlkoholischeZutat(21, "Milch der freien galaktischen Hochlandkühe", "weiß", 12.0f));
		zutatenListe.add(new NichtAlkoholischeZutat(22, "Zamphuor", "gelb", 8.0f));
		zutatenListe.add(new ErgaenzendeZutat(30, "Fallianisches Sumpfgas", "Fallia", "Liter", 5.0f));
		zutatenListe.add(new ErgaenzendeZutat(31, "Qualaktinischen Hyperminz-Extrakt", "Qualakt", "Esslöffel", 3.0f));
		zutatenListe.add(new ErgaenzendeZutat(32, "Würfel arkturanischen Mega-Gin", "Arktura", "Würfel", 1.0f));
		zutatenListe.add(new ErgaenzendeZutat(33, "Zahn eines algolianischen Sonnentigers", "Algolia", "Zahn", 87.0f));
		zutatenListe.add(new ErgaenzendeZutat(34, "Olive des Olymp", "Erde", "Stück", 9.0f));
		zutatenListe.add(new ErgaenzendeZutat(35, "Great English Breakfast Tea", "Erde", "Beutel", 1.0f));
		zutatenListe.add(new ErgaenzendeZutat(36, "Honig des blauen Planet", "Erde", "Esslöffel", 5.0f));
		zutatenListe.add(new Eis(40, "Zitroneneis", -5.7f, 2.0f));
		zutatenListe.add(new Eis(41, "Bananeneis", -2.3f, 12.0f));
		zutatenListe.add(new Eis(42, "Nusseis", -10.2f, 43.0f));
	}
	
	/**
	 * Erstellt die Drinkliste
	 */
	protected void initDrinks()
	{
		drinkList = new LinkedList<Drink>();
		// Galaktika
		Drink d0 = new Drink(100, "Galaktika", "     Für den Galaktika können Sie Ihre Zutaten selbst bestimmen.\n");
		d0.isCustom(true);
		drinkList.add(d0);
		
		// Pangalaktischer Donnergurgler
		Drink d1 = new Drink(101, "Pangalaktischer Donnergurgler", "     Der Pangalaktische Donnergurgler ist der angeblich stärkste Drink der Galaxis.\n     Die Wirkung eines Pangalaktischen Donnergurglers ist in etwa so,\n     als ob man mit einem Goldbarren, der in Zitronenscheiben gehüllt ist,\n     das Gehirn aus dem Kopf gedroschen bekommt.\n");
		d1.addZutat(getZutatByNumber(10));
		d1.addZutat(getZutatByNumber(20));
		for (int i = 0; i < 4; i++) d1.addZutat(getZutatByNumber(32));
		for (int i = 0; i < 5; i++) d1.addZutat(getZutatByNumber(30));
		d1.addZutat(getZutatByNumber(31));
		d1.addZutat(getZutatByNumber(33));
		d1.addZutat(getZutatByNumber(22));
		d1.addZutat(getZutatByNumber(34));
		drinkList.add(d1);
		
		// Romulanisches Ale
		Drink d2 = new Drink(102, "Romulanisches Ale", "     Dieses Getränk verkauft sich so gut, weil es überall verboten ist.\n     Seine Wirkung ist eigentlich in etwa dieselbe wie die von stinknormalem Rotwein,\n     allerdings glauben alle, die es trinken, etwas unglaublich gefährliches und heldenhaftes zu tun.\n");
		d2.addZutat(getZutatByNumber(12));
		drinkList.add(d2);
		
		// Oma's Gesundheit
		Drink d3 = new Drink(103, "Oma's Gesundheit", "     Dieses Getränk schmeckt eigentlich keinem aus dem Universum.\n     Aber alle Oma's empfehlen dieses Getränk Ihren Enkeln, so dass es eine\n     sehr große Verbreitung im Universum genießt und in keinem Restaurant fehlen darf.\n");
		d3.addZutat(getZutatByNumber(21));
		d3.addZutat(getZutatByNumber(36));
		drinkList.add(d3);
		
		// Syntherol
		Drink d4 = new Drink(104, "Syntherol", "     Eigentlich nur ein futuristischer Name für dreckiges Wasser.\n     In guten Restaurants wird das Wasser aus den Meeren von Santraginus V angeboten.\n ");
		d4.addZutat(getZutatByNumber(20));
		drinkList.add(d4);
	}

	/**
	 * Überprüft für die eingegebene Bestellung eine Bestellnummer vorliegt
	 * Gibt anschliessend einen Klon des gefundenen Objekts zurück
	 * 
	 * @param number
	 * @return Drink
	 */
	public Drink getDrinkByNumber(int number) 
	{
		Drink foundDrink = null;
		for (int i = 0; i < drinkList.size(); i++){
			Drink currentDrink = drinkList.get(i);
			if (currentDrink.hatNummer(number)){
				foundDrink = currentDrink;
				break;
			}
		}
		return foundDrink.clone();
	}
	
	/**
	 * Überprüft für die eingegebene Bestellung eine Bestellnummer vorliegt
	 * Gibt anschliessend einen Klon des gefundenen Objekts zurück
	 * 
	 * @param number
	 * @return Zutat
	 */
	public Zutat getZutatByNumber(int number)
	{
		Zutat foundZutat = null;
		for (int i = 0; i < zutatenListe.size(); i++){
			Zutat currentZutat = zutatenListe.get(i);
			if (currentZutat.hatNummer(number)){
				foundZutat = currentZutat;
				break;
			}
		}
		return foundZutat.clone();
	}	
	
	/**
	 * Gibt die Liste der erstellten Drinks zurück
	 * 
	 * @return Liste mit Drinks
	 */	
	public LinkedList<Drink> getDrinks()
	{
		return drinkList;
	}
	
	/**
	 * Gibt die Liste der erstellten Zutaten zurück
	 * 
	 * @return Liste mit Zutaten
	 */	
	public LinkedList<Zutat> getZutaten()
	{
		return zutatenListe;
	}
}
