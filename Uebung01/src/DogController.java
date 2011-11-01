import java.util.LinkedList;

abstract public class DogController 
{
	protected LinkedList<Dog> dogs = new LinkedList<Dog>();
	
	public DogController addDog(Dog dog)
	{
		this.dogs.add(dog);
		return this;
	}
	
	public LinkedList<Dog> getDogs()
	{
		return this.dogs;
	}
}
