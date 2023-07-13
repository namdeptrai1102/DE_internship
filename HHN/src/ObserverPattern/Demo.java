package ObserverPattern;

public class Demo {
	public static void main(String[] args) {
	ConcreteActivity activity = new ConcreteActivity();
	ConcreteMember member1 = new ConcreteMember("Memmber 1");
	ConcreteMember member2 = new ConcreteMember("Memmber 2");
	
	activity.attach(member1);
	activity.attach(member2);
	
	activity.setState(10);
	activity.setState(20);
	
	activity.detach(member2);
	
	activity.setState(30);
	}
}
