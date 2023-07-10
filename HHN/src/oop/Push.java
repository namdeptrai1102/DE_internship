package oop;

public class Push extends Exercise {
	//Inheritance parameters
	public Push(String name, String bodypart, String category) {
		this.name = name;
		this.bodypart = bodypart;
		this.category = category;
	}
	// Static Polymorphism
	public Push(String name) {
		this.name = name;
	}
	
	public String getName() {
		return "Push exercice name:" +  name;
	}
	public String getCategory() {
		return "Push exercise category:" + category;
	}
	public void practice() {
		System.out.println("Practice push exercise");
	};
	
}
