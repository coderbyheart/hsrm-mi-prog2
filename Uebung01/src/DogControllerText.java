import java.util.LinkedList;

public class DogControllerText extends DogController
{
	public DogController addDog(Dog dog)
	{
		super.addDog(dog);
		System.out.println("New Dog: " + dog.getState());
		return this;
	}
	
	protected void showDogInfo(Dog dog)
	{
		System.out.println(dog.getState());
	}
	
	public DogController command(String command) throws Exception
	{
		String[] splitCommand = command.split(" ");
		String dogName = splitCommand[0];
		Dog currentDog = null;
		for(int i = 0; i < this.dogs.size(); i++) {
			if (this.dogs.get(i).getName().equals(dogName)) {
				currentDog = this.dogs.get(i);
			}
		}
		if (currentDog == null) throw new Exception("Unknown dog: " + dogName);
		this.dogCommand(currentDog, splitCommand);
		return this;
	}
	
	protected void dogCommand(Dog dog, String[] splitCommand)
	{
		if (splitCommand[1].equals("platz")) {
			dog.lay();
			this.showDogInfo(dog);
		} else if (splitCommand[1].equals("gibLaut")) {
			dog.bark();
			this.showDogInfo(dog);
		} else if (splitCommand[1].equals("lauf")) {
			dog.go(Integer.parseInt(splitCommand[2]), Integer.parseInt(splitCommand[3]));
			this.showDogInfo(dog);
		} else if (splitCommand[1].equals("hier")) {
			dog.go(0, 0);
			this.showDogInfo(dog);
		} else if (splitCommand[1].equals("ok")) {
			dog.stand();
			this.showDogInfo(dog);
		} else if (splitCommand[1].equals("zeige")) {
			this.showDogInfo(dog);
		}
	}
}
