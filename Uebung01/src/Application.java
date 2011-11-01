public class Application 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		DogControllerText myDogController = new DogControllerText();
		myDogController.addDog(new Dog("Hasso"));
		myDogController.addDog(new Dog("Bolt"));
		try {
			myDogController.command("Hasso platz");
			myDogController.command("Hasso gibLaut");
			myDogController.command("Bolt gibLaut");
			myDogController.command("Hasso lauf 10 15");
			myDogController.command("Hasso hier");
			myDogController.command("Hasso ok");
			myDogController.command("Hasso zeige");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}