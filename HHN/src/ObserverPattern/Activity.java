package ObserverPattern;

public interface Activity {
	void attach(Member member);
	void detach(Member member);
	void notification();
	int getState();
}
