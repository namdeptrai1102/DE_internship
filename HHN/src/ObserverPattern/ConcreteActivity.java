package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class ConcreteActivity implements Activity{
	private List<Member> doinglist = new ArrayList<>();
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
		doinglist.add(member);
	}

	@Override
	public void detach(Member member) {
		doinglist.remove(member);
	}

	@Override
	public void notification() {
		for (Member member : doinglist) {
			member.update(this);
		}
	}
	
}
