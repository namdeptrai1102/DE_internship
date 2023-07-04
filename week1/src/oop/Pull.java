package oop;

public class Pull extends Exercise{
	//Inheritance
	public Pull(String name, String part, String category) {
		super(name, part, category);
		// TODO Auto-generated constructor stub
	}
	public Pull(String name) {
		super(name);
		this.name = name;
	}

	public String getName() {
		return "Pull exercice name:" +  name;
	}
	public String getCategory() {
		return "Pull exercise category:" + category;
	}
	
	void practice() {
		System.out.println("Practice pull exercise");
	};
}
