package pojo;

import java.util.ArrayList;

public class Manager extends Person{
	int idManager;
	ArrayList<Employee> Employees;
	double lossWithLate;
	
	public Manager(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}
	
	public double calcLoss(ArrayList<Employee> employees) {
		double lossTotal = this.lossWithLate;
		for (Employee e : employees) {
			if(e.late == true) {
				lossTotal += 21.50;
			}
		}
		
		return lossTotal;
	}
	
	

}
