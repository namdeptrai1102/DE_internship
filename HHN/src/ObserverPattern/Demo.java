package ObserverPattern;

public class Demo {
	public static void main(String[] args) {
	ConcreteActivity activity = new ConcreteActivity();
	ConcreteDoing doing1 = new ConcreteDoing("Memmber 1");
	ConcreteDoing doing2 = new ConcreteDoing("Memmber 2");
	
	activity.attach(doing1);
	activity.attach(doing2);
	
	activity.setState(10);
	activity.setState(20);
	
	activity.detach(doing2);
	
	activity.setState(30);
	}
}
