package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import pojo.Employee;
import pojo.Hour;

public class Servante extends UnicastRemoteObject implements RemoteInterface{
	
	protected Servante() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	@Override
	public void addManager(String nome, int age, int idManager, ArrayList<Employee> employees, double lossWithLate)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEmployee(String nome, int age, int idEmployee, Hour arrivalTime, Hour departureTime)
			throws RemoteException {
		// TODO Auto-generated method stub
		
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
}
