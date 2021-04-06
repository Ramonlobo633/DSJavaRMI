package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import pojo.Employee;
import pojo.Hour;
import pojo.Manager;

public class Servante extends UnicastRemoteObject implements RemoteInterface{
	
	protected Servante() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	@Override
	public Manager addManager(String nome, int age, int idManager, ArrayList<Employee> employees)
			throws RemoteException {
		// TODO Auto-generated method stub
		Manager manager = new Manager(nome, age, idManager, employees);
		return manager;
		
	}

	@Override
	public Employee addEmployee(String nome, int age, int idEmployee, Hour arrivalTime, Hour departureTime)
			throws RemoteException {
		// TODO Auto-generated method stub
		Employee employee = new Employee(nome, age, idEmployee, arrivalTime, departureTime);
		return employee;
		
	}


	@Override
	public Employee winningEmployee(ArrayList<Employee> employees) throws RemoteException {
	
			Employee winner = employees.get(0);
			double winTimeWorked = employees.get(0).getHoursWorked();
			for (Employee e : employees) {
				if(winTimeWorked < e.getHoursWorked()) {
					winTimeWorked = e.getHoursWorked();
					winner = e;
				}
			}	
			
			return winner;
		}




	@Override
	public void lateEmployees(Manager manager) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<Employee> employees = manager.getEmployees();
		System.out.println("Late employees: ");
		for (Employee e : employees) {
			
			if(e.isLate()) {
				e.toString();
			}
		}
		
	}




	@Override
	public double calcLossWithLate(Manager manager) throws RemoteException {
			ArrayList<Employee> employees = manager.getEmployees();
			double lossTotal = 0;
			for (Employee e : employees) {
				if(e.isLate() == true) {
					//considering that each hour not worked costs 21.50 R$
					lossTotal += e.getTimeLate()*21.50;
				}
			}
			
			return lossTotal;
	}
}
