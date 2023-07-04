package oop;

public class Exercise extends Practice{
	//Encapsulation
	protected String name;
	protected String bodypart;
	protected String category;
	public int kg;
	public int reps;
	// Constructor	
	public Exercise(String name, String bodypart, String category) {
		super();
		this.name = name;
		this.bodypart = bodypart;
		this.category = category;
	}
	// Static Polymorphism
	public Exercise(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return "Exercise name:" +  name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBodypart() {
		return "Body part:" + bodypart;
	}
	public void setBodypart(String bodypart) {
		this.bodypart = bodypart;
	}
	public String getCategory() {
		return "Exercise category" + category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getKg() {
		return kg;
	}
	public void setKg(int kg) {
		this.kg = kg;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	void practice() {
		System.out.println("Practice exercise");
	};
}