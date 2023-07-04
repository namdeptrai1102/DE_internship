package oop;

public class Push extends Exercise{
	//Inheritance
	public Push(String name, String part, String category) {
		super(name, part, category);
		// TODO Auto-generated constructor stub
	}
	public Push(String name) {
		super(name);
		this.name = name;
	}
	public String getName() {
		return "Push exercice name:" +  name;
	}
	public String getCategory() {
		return "Push exercise category:" + category;
	}
	void practice() {
		System.out.println("Practice push exercise");
	};
	
}
