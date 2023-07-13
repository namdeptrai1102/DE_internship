package ObserverPattern;

public class Demo {
	public static void main(String[] args) {
	ConcreteActivity activity = new ConcreteActivity();
	ConcreteMember doing1 = new ConcreteMember("Memmber 1");
	ConcreteMember doing2 = new ConcreteMember("Memmber 2");
	
	activity.attach(doing1);
	activity.attach(doing2);
	
	activity.setState(10);
	activity.setState(20);
	
	activity.detach(doing2);
	
	activity.setState(30);
	}
}
