package collections;

import java.util.Objects;

public class Exercise {
	private String name;
	private int sec;
	
	public Exercise(String name, int sec) {
		this.name = name;
		this.sec = sec;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, sec);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exercise other = (Exercise) obj;
		return Objects.equals(name, other.name) && sec == other.sec;
	}

	@Override
	public String toString() {
		return "Exercise [name=" + name + ", sec=" + sec + "]";
	}
	
	
}
