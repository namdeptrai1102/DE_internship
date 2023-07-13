package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class ConcreteActivity implements Activity{
	private List<Doing> doinglist = new ArrayList<>();
	private int state;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		notifycation();
	}

	@Override
	public void attach(Doing doing) {
		doinglist.add(doing);
	}

	@Override
	public void detach(Doing doing) {
		doinglist.remove(doing);
	}

	@Override
	public void notifycation() {
		for (Doing doing : doinglist) {
			doing.update(this);
		}
	}
	
}
