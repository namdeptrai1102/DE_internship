package ObserverPattern;

public class ConcreteMember implements Member {
	private int state;
	private String memberName;
	
	public ConcreteMember(String name) {
		this.memberName= name;
	}
	
	@Override
	public void update(Activity activity) {
		this.state = activity.getState();
		System.out.println(memberName + " doing " + state + " exercises");
	}

}
