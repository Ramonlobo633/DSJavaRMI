package pojo;

import java.util.ArrayList;

public class Manager extends Person{
	int idManager;
	ArrayList<Employee> Employees;
	
	
	public Manager(String name, int age, int idManager, ArrayList<Employee> employees) {
		super(name, age);
		this.idManager = idManager;
		Employees = employees;
	}


	public Manager(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}


	public int getIdManager() {
		return idManager;
	}


	public void setIdManager(int idManager) {
		this.idManager = idManager;
	}


	public ArrayList<Employee> getEmployees() {
		return Employees;
	}


	public void setEmployees(ArrayList<Employee> employees) {
		Employees = employees;
	}
	
	
	
	

}
