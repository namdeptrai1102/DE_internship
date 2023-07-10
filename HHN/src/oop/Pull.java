package oop;

public class Pull extends Exercise {
	//Inheritance parameters
	public Pull(String name, String bodypart, String category) {
		this.name = name;
		this.bodypart = bodypart;
		this.category = category;
	}
	// Static Polymorphism
	public Pull(String name) {
		this.name = name;
	}

	public String getName() {
		return "Pull exercice name:" +  name;
	}
	public String getCategory() {
		return "Pull exercise category:" + category;
	}
	
	public void practice() {
		System.out.println("Practice pull exercise");
	};
}
