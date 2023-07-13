package ObserverPattern;

public interface Activity {
	void attach(Doing doing);
	void detach(Doing doing);
	void notifycation();
	int getState();
}
