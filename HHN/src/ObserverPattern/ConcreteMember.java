package ObserverPattern;

public class ConcreteMember implements Member {
	private int state;
	private String doingName;
	
	public ConcreteMember(String name) {
		this.doingName= name;
	}
	
	@Override
	public void update(Activity activity) {
		this.state = activity.getState();
		System.out.println(doingName + " doing " + state + " exercises");
	}

}
