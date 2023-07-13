package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class ConcreteActivity implements Activity{
	private List<Member> memberlist = new ArrayList<>();
	private int state;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		notification();
	}

	@Override
	public void attach(Member member) {
		memberlist.add(member);
	}

	@Override
	public void detach(Member member) {
		memberlist.remove(member);
	}

	@Override
	public void notification() {
		for (Member member : memberlist) {
			member.update(this);
		}
	}
	
}
