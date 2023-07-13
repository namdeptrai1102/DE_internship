package ObserverPattern;

public class ConcreteDoing implements Doing {
	private int state;
	private String doingName;
	
	public ConcreteDoing(String name) {
		this.doingName= name;
	}
	
	@Override
	public void update(Activity activity) {
		this.state = activity.getState();
		System.out.println(doingName + " doing " + state + " exercises");
	}

}
