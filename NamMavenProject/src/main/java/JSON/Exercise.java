package JSON;

public class Exercise {
	private String Name;
    private int Reps;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getReps() {
		return Reps;
	}

	public void setReps(int reps) {
		Reps = reps;
	}

	@Override
    public String toString() {
        return "Exercise{" +
                "name='" + Name + '\'' +
                ", reps=" + Reps +
                '}';
    }
}
